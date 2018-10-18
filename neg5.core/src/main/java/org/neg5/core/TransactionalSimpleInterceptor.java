package org.neg5.core;

import com.google.inject.Inject;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.neg5.db.PersistenceManager;

import javax.persistence.EntityTransaction;

public class TransactionalSimpleInterceptor implements MethodInterceptor {

    @Inject private PersistenceManager persistenceManager;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        boolean openedByUs = getOpenedByUs();
        try {
            if (openedByUs) {
               persistenceManager.begin();
            }
            Object result = invocation.proceed();
            if (openedByUs) {
                persistenceManager.commit();
            }
            return result;
        } catch (Exception e) {
            if (openedByUs) {
                persistenceManager.rollback();
            }
            throw e;
        }
    }

    private boolean getOpenedByUs() {
        EntityTransaction transaction = persistenceManager.getCurrentTransaction();
        boolean openedByUs = false;
        if (!transaction.isActive()) {
            openedByUs = true;
        }
        return openedByUs;
    }
}
