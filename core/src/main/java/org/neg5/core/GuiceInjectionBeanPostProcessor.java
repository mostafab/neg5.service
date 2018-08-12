package org.neg5.core;

import com.google.inject.Injector;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GuiceInjectionBeanPostProcessor implements BeanPostProcessor {

    @Autowired private Injector injector;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        injector.injectMembers(bean);
        return bean;
    }
}