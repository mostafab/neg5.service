package org.neg5.daos;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.hibernate.SessionFactory;
import org.neg5.data.SpecificTournamentEntity;
import org.neg5.db.PersistenceManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> {

    private Class<T> persistentClass;

    private static final String FIND_ALL_BY_TOURNAMENT_ID_QUERY
            = "SELECT ent from %s ent where ent.tournament.id = :tournamentId";

    private static final String TOURNAMENT_ID_PARAM = "tournamentId";

    @PersistenceContext
    private EntityManager entityManager;

    protected AbstractDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public T get(String id) {
        return getEntityManager().find(persistentClass, id);
    }

    public T save(T entity) {
        return getEntityManager().merge(entity);
    }

    public void flush() {
        getEntityManager().flush();
    }

    public List<T> findAllByTournamentId(String tournamentId) {
        validateFindByTournamentId();
        String query = String.format(FIND_ALL_BY_TOURNAMENT_ID_QUERY, persistentClass.getSimpleName());
        return getEntityManager().createQuery(query, persistentClass)
                .setParameter(TOURNAMENT_ID_PARAM, tournamentId)
                .getResultList();
    }

    protected Class<T> getPersistentClass() {
        return persistentClass;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    private void validateFindByTournamentId() {
        if (!SpecificTournamentEntity.class.isAssignableFrom(persistentClass)) {
            throw new IllegalArgumentException("Class "
                    + persistentClass.getName() + " does not implement " + SpecificTournamentEntity.class);
        }
    }
}
