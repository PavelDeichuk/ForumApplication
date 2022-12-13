package com.pavel.forumapplication.Mapper;

import com.pavel.forumapplication.Dto.RulesDto;
import com.pavel.forumapplication.Entity.RulesEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RulesMapperTest {

    @Mock
    private RulesMapper rulesMapper;

    @Test
    void RULES_DTO() {
        RulesEntity rules = RulesEntity.builder().id(1L).build();
        RulesDto rulesDto = rulesMapper.INSTANCE.RULES_DTO(rules);
        assertEquals(rulesDto.getId(), rules.getId());
    }

    @Test
    void RULES_ENTITY() {
        RulesDto rulesDto = RulesDto.builder().id(1L).build();
        RulesEntity rulesEntity = rulesMapper.INSTANCE.RULES_ENTITY(rulesDto);
        assertEquals(rulesEntity.getId(), rulesDto.getId());
    }
}