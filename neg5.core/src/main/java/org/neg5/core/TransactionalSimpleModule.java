package org.neg5.core;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class TransactionalSimpleModule extends AbstractModule {

    protected void configure() {
        TransactionalSimpleInterceptor interceptor = new TransactionalSimpleInterceptor();
        requestInjection(interceptor);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(TransactionalSimple.class), interceptor);
    }
}
