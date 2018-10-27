package org.neg5.core;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class TransactionalModule extends AbstractModule {

    protected void configure() {
        TransactionalInterceptor interceptor = new TransactionalInterceptor();
        requestInjection(interceptor);

        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transactional.class), interceptor);
    }
}