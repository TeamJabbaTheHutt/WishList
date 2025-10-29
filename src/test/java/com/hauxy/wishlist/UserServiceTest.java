package com.hauxy.wishlist;


import com.hauxy.wishlist.Repository.DAO.DAO;
import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.aot.hint.TypeReference.listOf;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private DAO dao;

    @InjectMocks
    private UserService userService;

    @Test
    void createNewUserReturnsSuccess() {
        User newUser = new User("testmail@mail.com", "testUserName", "testUserPassword");
        when(dao.createNewUser(newUser)).thenReturn(1);

        String result = userService.createNewUser(newUser);

        assertEquals("Success", result);
    }

    @Test
    void getUsersShouldReturnListOfUsers() {
        List<User> returnResult = new ArrayList<>();
        returnResult.add(new User("mail.com", "testUserName", "testUserPassword"));
        when(dao.getUsers()).thenReturn(returnResult);

        List<User> result = userService.getUsers();
        assertEquals(returnResult, result);
    }


}

