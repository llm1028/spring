package com.spring;

/**
 * @author liluming
 * @className: BeanDefinition
 * @description:
 * @date 2021/12/9 7:27 下午
 */
public class BeanDefinition {
    // bean类型
    private Class type;
    // bean作用域（单例还是多例）
    private String scope;
    private boolean isLazy;

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isLazy() {
        return isLazy;
    }

    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }
}
