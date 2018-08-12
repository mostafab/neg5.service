package org.neg5.managers;

import com.google.inject.persist.Transactional;
import org.neg5.daos.AbstractDAO;

import org.neg5.data.AbstractDataObject;
import org.neg5.mappers.AbstractObjectMapper;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractManager<T extends AbstractDataObject<T>, DTO> {

    protected abstract AbstractDAO<T> getDAO();

    protected abstract AbstractObjectMapper<T, DTO> getMapper();

    @Transactional
    public DTO get(String id) {
        T entity = getDAO().get(id);
        if (entity == null) {
            throw new NoResultException("No result found for entity with id " + id);
        }
        return getMapper().toDTO(entity.copyOf());
    }

    @Transactional
    public List<DTO> findAllByTournamentId(String tournamentId) {
        return getDAO().findAllByTournamentId(tournamentId)
                .stream()
                .map(entity -> getMapper().toDTO(entity))
                .collect(Collectors.toList());
    }
}
