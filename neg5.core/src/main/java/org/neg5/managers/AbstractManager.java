package org.neg5.managers;

import com.google.inject.persist.Transactional;
import org.neg5.daos.AbstractDAO;

import org.neg5.data.AbstractDataObject;
import org.neg5.data.IdDataObject;
import org.neg5.mappers.AbstractObjectMapper;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractManager<T extends AbstractDataObject<T>
        & IdDataObject<PrimaryKeyType>, DTO, PrimaryKeyType> {

    protected abstract AbstractDAO<T, PrimaryKeyType> getDAO();

    protected abstract AbstractObjectMapper<T, DTO> getMapper();

    @Transactional
    public DTO get(PrimaryKeyType id) {
        T entity = getDAO().get(id);
        if (entity == null) {
            String clazzName = getDAO().getPersistentClass().getSimpleName();
            throw new NoResultException("No result found for entity " + clazzName + " with id " + id);
        }
        return getMapper().toDTO(entity.copyOf());
    }

    @Transactional
    public DTO create(DTO dto) {
        T entity = getMapper().mergeToEntity(dto);
        if (entity.getId() != null) {
            throw new IllegalArgumentException("Id on input dto is null.");
        }
        return getMapper().toDTO(getDAO().save(entity));
    }

    @Transactional
    public DTO update(DTO dto) {
        T original = getMapper().mergeToEntity(dto);
        T entity = getMapper().mergeToEntity(dto, getDAO().get(original.getId()));
        return getMapper().toDTO(getDAO().save(entity));
    }

    @Transactional
    public List<DTO> findAllByTournamentId(String tournamentId) {
        return getDAO().findAllByTournamentId(tournamentId)
                .stream()
                .map(entity -> getMapper().toDTO(entity))
                .collect(Collectors.toList());
    }
}
