package com.jn.esmvc.dao;

import com.jn.esmvc.model.Knowledge;
import com.jn.esmvc.model.KnowledgeESModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KnowldegeModelMapper {
    KnowldegeModelMapper INSTANCE = Mappers.getMapper(KnowldegeModelMapper.class);

    @Mappings({
            @Mapping(target = "_id", source = "id"),
            @Mapping(target = "content", source = "content"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "keywords", source = "keywords"),
            @Mapping(target = "labels", source = "labels"),
            @Mapping(target = "createInfo", source = "createInfo"),
            @Mapping(target = "lastModifierInfo", source = "lastModifyInfo"),
            @Mapping(target = "highlightFieldMap", source = "highlightFieldMap")
    })
    KnowledgeESModel toESModel(Knowledge f);

    @Mappings({
            @Mapping(target = "id", source = "_id"),
            @Mapping(target = "content", source = "content"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "keywords", source = "keywords"),
            @Mapping(target = "labels", source = "labels"),
            @Mapping(target = "createInfo", source = "createInfo"),
            @Mapping(target = "lastModifyInfo", source = "lastModifierInfo"),
            @Mapping(target = "highlightFieldMap", source = "highlightFieldMap")
    })
    Knowledge fromESModel(KnowledgeESModel f);
}
