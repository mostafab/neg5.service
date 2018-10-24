package org.neg5.core;

import javax.persistence.EntityManager;

public interface PersistenceManager {

    EntityManager getCurrentEntityManager(boolean readOnly);

    void close(boolean readOnly);
}
