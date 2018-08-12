package org.neg5.core;

import com.google.inject.Module;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Neg5ServiceGuiceFactoryBean {

    @Bean
    public List<Module> getObject() {
        return new ArrayList<>();
    }
}
