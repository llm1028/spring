package com.spring;

import com.llm.service.LlmBeanPostProcessor;
import org.omg.CORBA.CharSeqHelper;

import java.beans.Introspector;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liluming
 * @className: LlmApplicationContext
 * @description:
 * @date 2021/12/9 6:41 下午
 */
public class LlmApplicationContext {

    private Class configClass;
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    private Map<String, Object> singletonObjects = new HashMap<>();
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public LlmApplicationContext(Class configClass) {
        this.configClass = configClass;
        // 扫描包路径
        scan(configClass);

        // 创建单例bean
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            // 找出单例bean,进行创建
            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }


    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getType();
        Object instance = null;
        try {
            // 1.实例化
            instance = clazz.getConstructor().newInstance();
            // 2.依赖注入（这里做展示用，实际spring框架，实在beanPostProcessor里处理的）
            // 检查是否有autowired属性，完成依赖注入
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    // 通过反射方式给属性赋值，根据字段的名字，调用getbean方法
                    field.setAccessible(true);
                    field.set(instance, getBean(field.getName()));
                }
            }
            // 模拟回调方法
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware) instance).setBeanName(beanName);
            }

            // 3.1 初始化前
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            }
            // 3.初始化
            if (instance instanceof InitializingBean) {
                ((InitializingBean)instance).afterPropertiesSet();
            }
            // 4.初始化后
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                // 此时这里是代理对象
                instance = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;

    }

    public Object getBean(String beanName) {
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new NullPointerException();
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition.getScope().equals("singleton")) {
            // 单例bean，直接从单例池里获取
            Object singletonBean = singletonObjects.get(beanName);
            // 如果没找到，比如：A类注入B，获取B的时候，如果查不到，需要先创建
            if (singletonBean == null) {
                singletonBean = createBean(beanName, beanDefinition);
                // 创建完毕，存入单单例池
                singletonObjects.put(beanName, singletonBean);
            }
            return singletonBean;
        } else {
            // 原型bean(多例), 每次都去创建
            Object prototypeBean = createBean(beanName, beanDefinition);
            return prototypeBean;
        }
    }

    /**
     * 扫描bean，设置BeanDefinition
     * @param configClass
     */
    private void scan(Class configClass) {
        // 扫描包路径
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String path = componentScanAnnotation.value();

            // 获取包路径（相对路径）com/llm/service
            path = path.replace(".", "/");
            // System.out.println(path);

            // 获取类加载器
            ClassLoader classLoader = LlmApplicationContext.class.getClassLoader();

            // 通过类加载器加载目录下的文件
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    String absolutePath = f.getAbsolutePath();

                    // 转换路径为类的路径
                    absolutePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                    absolutePath = absolutePath.replace("/",".");
                    // System.out.println(absolutePath);
                    try {
                        Class<?> clazz = classLoader.loadClass(absolutePath);
                        if (clazz.isAnnotationPresent(Component.class)) {
                            // 判断一个类是否是实现BeanPostProcessor的接口
                            if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                                BeanPostProcessor instance = (BeanPostProcessor) clazz.getConstructor().newInstance();
                                beanPostProcessorList.add(instance);
                            } else {
                                Component componentAnnotation = clazz.getAnnotation(Component.class);
                                String beanName = componentAnnotation.value();
                                // 注解里没写bean的名字，需要生成一个默认的beanName
                                if ("".equals(beanName)) {
                                    beanName = Introspector.decapitalize(clazz.getSimpleName());
                                }
                                // 找到添加Componet注解的类，把类信息存储起来，方便后面使用
                                BeanDefinition beanDefinition = new BeanDefinition();
                                beanDefinition.setType(clazz);
                                if (clazz.isAnnotationPresent(Scope.class)) {
                                    Scope scopeAnnotation = clazz.getAnnotation(Scope.class);
                                    String value = scopeAnnotation.value();
                                    beanDefinition.setScope(value);
                                } else {
                                    // 没有scope注解，默认单例
                                    beanDefinition.setScope("singleton");
                                }
                                beanDefinitionMap.put(beanName, beanDefinition);
                            }
                        }
                    } catch (ClassNotFoundException | NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }


        }
    }

}
