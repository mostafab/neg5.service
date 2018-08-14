package org.neg5.filters;

import com.google.inject.Inject;

import org.neg5.db.PersistenceManager;

import static spark.Spark.afterAfter;

public class PersistenceHandlerFilter implements RequestFilter {

    @Inject private PersistenceManager persistenceManager;

    @Override
    public void registerFilter() {
        afterAfter((request, response) -> {
            persistenceManager.close();
        });
    }
}
