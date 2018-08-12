package org.neg5.core;

import com.google.inject.Injector;
import com.google.inject.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
public class Neg5ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired private Injector injector;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        ApplicationContext context = e.getApplicationContext();

//        for (Module module : context.getBeansOfType(Module.class).values()) {
//            injector.injectMembers(module);
//        }
    }
}
