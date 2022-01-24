package com.example.springplug;

import com.example.springplug.config.AppConfig;
import com.example.springplug.conversion.StringToUserConverter;
import com.example.springplug.conversion.StringToUserPropertyEditor;
import com.example.springplug.req.Student;
import com.example.springplug.req.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.MutablePropertySources;

import java.util.Map;

// @SpringBootApplication
public class SpringPlugApplication {

    // public static void main(String[] args) {
    //     SpringApplication.run(SpringPlugApplication.class, args);
    // }
    public static void main(String[] args) {
        // 测试beanDefinition读取器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // 第一种：AnnotatedBeanDefinitionReader
        // AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(context);
        // annotatedBeanDefinitionReader.register(User.class);
        // DefaultConversionService conversionService = new DefaultConversionService();
        // conversionService.addConverter(new StringToUserConverter());
        // User value = conversionService.convert("1", User.class);
        // System.out.println(value);
        // 第二种：xmlBeanDefinitionReader
        // XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(context);
        // xmlBeanDefinitionReader.loadBeanDefinitions("spring.xml");
        // System.out.println(context.getBean("order"));

        // 第三种：ClassPathBeanDefinitionScanner
        // context.refresh();
        // ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner(context);
        // classPathBeanDefinitionScanner.scan("com.example.springplug");
        // System.out.println(context.getBean("student"));
        // 获取运行时环境变量
        // Map<String, Object> systemEnvironment = context.getEnvironment().getSystemEnvironment();
        // // 操作系统环境变量
        // System.out.println(systemEnvironment);
        // System.out.println("=======");
        //
        // Map<String, Object> systemProperties = context.getEnvironment().getSystemProperties();
        // // -d命令参数 置顶的k v
        // System.out.println(systemProperties);
        //
        // System.out.println("=======");
        //
        // MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        // System.out.println(propertySources);
        //
        // System.out.println("=======");
        //
        // System.out.println(context.getEnvironment().getProperty("NO_PROXY"));
        // System.out.println(context.getEnvironment().getProperty("sun.jnu.encoding"));
        // System.out.println(context.getEnvironment().getProperty("zhouyu"));

        // 类型转换器
        Student student = (Student) context.getBean("student");
        student.test();

        // StringToUserPropertyEditor propertyEditor = new StringToUserPropertyEditor();
        // propertyEditor.setAsText("1");
        // User value = (User) propertyEditor.getValue();
        // System.out.println(value);
    }
}
