package com.microservice.springuserwebservice.controller;

import com.microservice.springuserwebservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(userController).build();
    }

    @Test
    public void retrieveAllUsers() throws Exception {
        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .pathInfo("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void retrieveUser() throws Exception {

        mockMvc.perform(get("/users/{id}"))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteUser() throws Exception {
    }

    @Test
    public void createUser() throws Exception {
    }

}