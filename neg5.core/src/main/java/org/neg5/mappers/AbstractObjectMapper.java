package org.neg5.mappers;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import org.modelmapper.convention.MatchingStrategies;

public class AbstractObjectMapper<Entity, DTO> {

    private Class<Entity> entityClass;
    private Class<DTO> dtoClass;

    private ModelMapper modelMapper;
    private TypeMap<Entity, DTO> typeMap;

    protected AbstractObjectMapper(Class<Entity> entityClass, Class<DTO> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;

        modelMapper = new ModelMapper();
        typeMap = modelMapper.createTypeMap(entityClass, dtoClass);
        addMappings();
    }

    public DTO toDTO(Entity entity) {
        DTO dto = modelMapper.map(entity, dtoClass);
        enrichDTO(dto, entity);
        return dto;
    }

    protected void enrichDTO(DTO dto, Entity entity) {}

    protected void addMappings() {

    }

    protected ModelMapper getModelMapper() {
        return modelMapper;
    }

    protected TypeMap<Entity, DTO> getTypeMap() {
        return typeMap;
    }
}
