package com.pavel.forumapplication.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavel.forumapplication.Dto.UsersDetailDto;
import com.pavel.forumapplication.Dto.UsersDto;
import com.pavel.forumapplication.Service.UsersDetailService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
class UsersDetailControllerTest {

    @Mock
    private UsersDetailService usersDetailService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllUsersDetail() throws Exception {
        List<UsersDetailDto> usersDetailDtos = new ArrayList<>();
        when(usersDetailService.GetAllUsersDetail(2,10)).thenReturn(usersDetailDtos);
        mockMvc.perform(get("/api/v1/user-detail")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(usersDetailDtos)))
                .andExpect(status().isOk());
    }

    @Test
    void getUserDetailById() throws Exception {
        UsersDetailDto usersDetailDto = new UsersDetailDto();
        when(usersDetailService.GetUsersDetailById(any())).thenReturn(usersDetailDto);
        UsersDetailDto getUserById = usersDetailService.GetUsersDetailById(any());
        mockMvc.perform(get("/api/v1/user-detail/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getUserById)))
                .andExpect(status().isOk());
        verify(usersDetailService).GetUsersDetailById(any());
    }

    @Test
    void createUserDetail() throws Exception {
        UsersDetailDto usersDetailDto = new UsersDetailDto();
        when(usersDetailService.CreateUserDetail(any())).thenReturn(usersDetailDto);
        UsersDetailDto createUserTest = usersDetailService.CreateUserDetail(any());
        mockMvc.perform(post("/api/v1/user-detail")
                        .content(objectMapper.writeValueAsString(createUserTest))
                        .contentType("application/json"))
                .andExpect(status().isOk());
        verify(usersDetailService).CreateUserDetail(any());
    }

    @Test
    void editUser() throws Exception {
        UsersDetailDto usersDetailDto = new UsersDetailDto();
        when(usersDetailService.EditUserDetail(any(), any())).thenReturn(usersDetailDto);
        UsersDetailDto editUserTest = usersDetailService.EditUserDetail(any(), any());
        mockMvc.perform(put("/api/v1/user-detail/1")
                        .content(objectMapper.writeValueAsString(editUserTest))
                .contentType("application/json"))
                .andExpect(status().isOk());
        verify(usersDetailService).EditUserDetail(any(), any());
    }

    @Test
    void deleteUser() throws Exception {
        UsersDetailDto usersDetailDto = new UsersDetailDto();
        when(usersDetailService.DeleteUserDetail(any())).thenReturn(usersDetailDto);
        UsersDetailDto deleteUserTest = usersDetailService.DeleteUserDetail(any());
        mockMvc.perform(delete("/api/v1/user-detail/1")
                        .content(objectMapper.writeValueAsString(deleteUserTest))
                .contentType("application/json"))
                .andExpect(status().isOk());
        verify(usersDetailService).DeleteUserDetail(any());
    }
}