package com.universityteam.flashmemorizer.converter;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseConverter<ENTITY, DTO> {

    @Autowired
    protected ModelMapper mapper;

    public List<DTO> convertEntityToDto(List<ENTITY> entities) {
        return entities.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public DTO convertEntityToDto(ENTITY entity) {
        return (entity == null) ? null : mapper.map(entity, getDtoClass());
    }

    public List<ENTITY> convertDtoToEntity(List<DTO> dtos) {
        return dtos.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }

    public ENTITY convertDtoToEntity(DTO dto) {
        return (dto == null) ? null : mapper.map(dto, getEntityClass());
    }

    protected abstract Class<DTO> getDtoClass();

    protected abstract Class<ENTITY> getEntityClass();

    @PostConstruct
    protected abstract void configuration();
}
