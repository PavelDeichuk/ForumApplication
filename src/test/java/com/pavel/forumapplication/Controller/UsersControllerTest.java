package com.pavel.forumapplication.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavel.forumapplication.Dto.UsersDto;
import com.pavel.forumapplication.Service.UsersService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
class UsersControllerTest {

    @Mock
    private UsersService usersService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllUsers() throws Exception {
        List<UsersDto> usersDtos = new ArrayList<>();
        when(usersService.GetAllUsers(2,10)).thenReturn(usersDtos);
        mockMvc.perform(get("/api/v1/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(usersDtos)))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception {
        UsersDto usersDto = new UsersDto();
        when(usersService.GetUserById(any())).thenReturn(usersDto);
        UsersDto getUserById = usersService.GetUserById(any());
        mockMvc.perform(get("/api/v1/users/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(getUserById)))
                .andExpect(status().isOk());
        verify(usersService).GetUserById(any());
    }

    @Test
    void createUser() throws Exception {
    }

    @Test
    void editUser() {
    }

    @Test
    void activateUser() {

    }

    @Test
    void changePassword() {
    }

    @Test
    void resetPassword() {
    }

    @Test
    void deleteUser() throws Exception {
        UsersDto usersDto = new UsersDto();
        when(usersService.DeleteUser(any())).thenReturn(usersDto);
        UsersDto usersDelete = usersService.DeleteUser(any());
        mockMvc.perform(delete("/api/v1/users/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(usersDelete)))
                .andExpect(status().isOk());
        assertEquals(usersDelete, usersDto);
        verify(usersService).DeleteUser(any());
    }
}