package com.hauxy.wishlist;


import com.hauxy.wishlist.Repository.DAO.DAO;
import com.hauxy.wishlist.Repository.repository.WishListRepository;
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
    private WishListRepository wishListRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createNewUserReturnsSuccess() {
        User user = new User("something@email.com", "username", "password");

        when(wishListRepository.createNewUser(user)).thenReturn(1);

        String result = userService.createNewUser(user);

        assertEquals("Success", result);
        verify(wishListRepository).createNewUser(user);
    }
    @Test
    void getUsersReturnsListOfUsers() {
        List<User> users = new  ArrayList<>();
        users.add(new User("something@email.com", "UserName", "password"));
        when(wishListRepository.getUsers()).thenReturn(users);
        List<User> result = userService.getUsers();
        assertEquals(users, result);
        verify(wishListRepository).getUsers();
    }

    @Test
    void getUserByIdReturnsSingleUser() {
        User user = new User("newMail@mail.com", "UserName", "password");
        when(wishListRepository.getUserById(1)).thenReturn(user);
        User result = userService.getUserById(1);
        assertEquals(user, result);
        verify(wishListRepository).getUserById(1);

    }

    @Test
    void deleteUserById() {
        when(wishListRepository.deleteUserById(1)).thenReturn(1);
        String result = userService.deleteUserById(1);

        assertEquals("Success", result);
        verify(wishListRepository).deleteUserById(1);
    }
}

