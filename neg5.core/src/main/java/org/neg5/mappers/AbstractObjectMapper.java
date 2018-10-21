package org.neg5.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

public class AbstractObjectMapper<Entity, DTO> {

    private final Class<Entity> entityClass;
    private final Class<DTO> dtoClass;

    private final ModelMapper entityToDTOModelMapper;
    private final TypeMap<Entity, DTO> entityToDTOTypeMap;

    private final ModelMapper dtoToEntityModelMapper;
    private final TypeMap<DTO, Entity> dtoToEntityTypeMap;

    protected AbstractObjectMapper(Class<Entity> entityClass, Class<DTO> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;

        entityToDTOModelMapper = new ModelMapper();
        entityToDTOModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        entityToDTOTypeMap = entityToDTOModelMapper.createTypeMap(entityClass, dtoClass);

        dtoToEntityModelMapper = new ModelMapper();
        dtoToEntityModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        dtoToEntityTypeMap = dtoToEntityModelMapper.createTypeMap(dtoClass, entityClass);

        addMappings();
    }

    public DTO toDTO(Entity entity) {
        DTO dto = entityToDTOModelMapper.map(entity, dtoClass);
        enrichDTO(dto, entity);
        return dto;
    }

    public Entity mergeToEntity(DTO dto) {
        try {
            return mergeToEntity(dto, entityClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Unable to map dto to entity.");
        }
    }

    public Entity mergeToEntity(DTO dto, Entity entity) {
        dtoToEntityModelMapper.map(dto, entity);
        return entity;
    }

    protected void enrichDTO(DTO dto, Entity entity) {}

    protected void addMappings() {

    }

    protected ModelMapper getEntityToDTOModelMapper() {
        return entityToDTOModelMapper;
    }

    protected TypeMap<Entity, DTO> getEntityToDTOTypeMap() {
        return entityToDTOTypeMap;
    }

    protected TypeMap<DTO, Entity> getDtoToEntityTypeMap() {
        return dtoToEntityTypeMap;
    }
}
