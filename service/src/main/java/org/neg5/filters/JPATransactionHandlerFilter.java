package org.neg5.filters;

import com.google.inject.Inject;

import org.neg5.db.PersistenceManager;

import javax.persistence.EntityTransaction;

import static spark.Spark.after;
import static spark.Spark.before;

public class JPATransactionHandlerFilter implements RequestFilter {

    @Inject private PersistenceManager persistenceManager;

    @Override
    public void registerFilter() {
//        before((request, response) -> {
//           persistenceManager.getEntityManager().getTransaction().begin();
//        });
//
//        after((request, response) -> {
//            EntityTransaction transaction = persistenceManager.getEntityManager().getTransaction();
//            if (transaction.isActive()) {
//                transaction.commit();
//            }
//            persistenceManager.close();
//        });
    }
}
