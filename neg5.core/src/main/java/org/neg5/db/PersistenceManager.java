package org.neg5.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public interface PersistenceManager {

    EntityManager getEntityManager();

    EntityTransaction getCurrentTransaction();

    void begin();

    void commit();

    void rollback();

    void close();
}
