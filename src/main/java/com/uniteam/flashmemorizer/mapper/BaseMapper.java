package com.uniteam.flashmemorizer.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseMapper<ENTITY, DTO> {

    @Autowired
    protected ModelMapper mapper;

    public List<DTO> toDto(List<ENTITY> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public DTO toDto(ENTITY entity) {
        return (entity == null) ? null : mapper.map(entity, getDtoClass());
    }

    public List<ENTITY> toEntity(List<DTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public ENTITY toEntity(DTO dto) {
        return (dto == null) ? null : mapper.map(dto, getEntityClass());
    }

    protected abstract Class<DTO> getDtoClass();

    protected abstract Class<ENTITY> getEntityClass();

    @PostConstruct
    protected abstract void configuration();
}
