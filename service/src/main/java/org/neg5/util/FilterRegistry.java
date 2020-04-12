package org.neg5.util;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.filters.RequestFilter;

import java.util.Set;

@Singleton
public class FilterRegistry {

    @Inject
    private Set<RequestFilter> filters;

    public void initFilters() {
        filters.forEach(RequestFilter::registerFilter);
    }
}
