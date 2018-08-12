package org.neg5.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Neg5GuiceInjectorFactoryBean {

    @Autowired private List<Module> modules;

    @Bean
    public Injector getInjector() {
        return Guice.createInjector(modules);
    }
}
