package org.neg5.core;

import com.google.inject.Inject;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Deprecated
public class TransactionalInterceptor implements MethodInterceptor {

    @Inject private PersistenceManager persistenceManager;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        boolean isReadOnly = !invocation.getMethod().getAnnotation(Transactional.class).readWrite();
        TransactionWrapper transactionWrapper = beginTransaction(isReadOnly);
        boolean openedByUs = transactionWrapper.openendByUs;
        try {
            Object result = invocation.proceed();
            if (openedByUs) {
                transactionWrapper.transaction.commit();
            }
            return result;
        } catch (Exception e) {
            if (openedByUs) {
                transactionWrapper.transaction.rollback();
            }
            throw e;
        } finally {
            if (openedByUs) {
                persistenceManager.close(isReadOnly);
            }
        }
    }

    private TransactionWrapper beginTransaction(boolean readOnly) {
        EntityManager em = persistenceManager.getCurrentEntityManager(readOnly);
        final EntityTransaction transaction = em.getTransaction();

        boolean openedByUs = false;
        if (!transaction.isActive()) {
            transaction.begin();
            if (readOnly) {
//                currentSession.set(true);
            }
            openedByUs = true;
        }
        return new TransactionWrapper(transaction, openedByUs);
    }

    private class TransactionWrapper {

        private EntityTransaction transaction;
        private boolean openendByUs;

        private TransactionWrapper(EntityTransaction transaction, boolean openendByUs) {
            this.transaction = transaction;
            this.openendByUs = openendByUs;
        }
    }
}