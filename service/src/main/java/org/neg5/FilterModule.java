package org.neg5;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import org.neg5.filters.NoResultHandlerFilter;
import org.neg5.filters.RequestFilter;

public class FilterModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<RequestFilter> multibinder = Multibinder.newSetBinder(binder(), RequestFilter.class);

        multibinder.addBinding().to(NoResultHandlerFilter.class);
    }
}
