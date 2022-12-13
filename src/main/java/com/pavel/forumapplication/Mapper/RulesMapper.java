package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.RulesDto;
import com.pavel.forumapplication.Entity.RulesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RulesMapper {
    RulesMapper INSTANCE = Mappers.getMapper(RulesMapper.class);

    RulesDto RULES_DTO(RulesEntity rulesEntity);

    RulesEntity RULES_ENTITY(RulesDto rulesDto);
}
