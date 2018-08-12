package org.neg5.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class Neg5DbConfig {

    private static final String PERSISTENCE_UNIT_NAME = "persistence";

    @Bean
    public EntityManagerFactory getEntityManager() {
//        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
//        factoryBean.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
//
//        return factoryBean;
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
}
