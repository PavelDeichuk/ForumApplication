package com.pavel.forumapplication.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavel.forumapplication.Dto.RulesDto;
import com.pavel.forumapplication.Dto.UsersDto;
import com.pavel.forumapplication.Entity.RulesEntity;
import com.pavel.forumapplication.Service.RulesService;
import org.apache.tomcat.util.digester.Rules;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
class RulesControllerTest {


    @Mock
    private RulesService rulesService;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllRules() throws Exception {
        List<RulesDto> rulesDtos = new ArrayList<>();
        RulesDto rulesDto = RulesDto.builder().id(1L).build();
        rulesDtos.add(rulesDto);
        when(rulesService.GetAllRules(2,10)).thenReturn(rulesDtos);
        List<RulesDto> rulesDtosTest = rulesService.GetAllRules(2,10);
        mockMvc.perform(get("/api/v1/rules")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(rulesDtosTest)))
                .andExpect(status().isOk());
    }

    @Test
    void getRulesById() throws Exception {
        RulesDto rulesDto = new RulesDto();
        when(rulesService.GetRulesById(any())).thenReturn(rulesDto);
        RulesDto rulesDtoTest = rulesService.GetRulesById(any());
        mockMvc.perform(get("/api/v1/rules")
                        .content(objectMapper.writeValueAsString(rulesDtoTest))
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void createRules() {
    }

    @Test
    void editRules() {
    }

    @Test
    void deleteRules() {
    }
}