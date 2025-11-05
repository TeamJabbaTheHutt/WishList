package com.hauxy.wishlist.controller;

import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void registerNewUserRedirectsToDashboard() throws Exception {
        when(userService.checkIfUserExist("test@test.com")).thenReturn(true);
        when(userService.createNewUser(any(User.class))).thenReturn("Success");

        mockMvc.perform(post("/users/register")
                        .param("username", "test")
                        .param("email", "test@test.com")
                        .param("password", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));
    }


    @Test
    void registerExistingUserReturnsError() throws Exception {
        when(userService.checkIfUserExist("taken@test.com")).thenReturn(false);

        mockMvc.perform(post("/users/register")
                        .param("username", "someone")
                        .param("email", "taken@test.com")
                        .param("password", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("message"));
    }
}
