package com.hauxy.wishlist;

import com.hauxy.wishlist.controller.WishListController;
import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.service.WishListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishListController.class)
class WishListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishListService wishListService;

    @Test
    void getWishlistsFiltersByUser() throws Exception {
        User user = new User("test", "test@mail.com", "123");
        user.setUser_id(5);

        mockMvc.perform(get("/dashboard").sessionAttr("loggedInUser", user))
                .andExpect(status().isOk());

        verify(wishListService, times(1)).getWishListsForUser(5);
    }

    @Test
    void deleteRequiresLogin() throws Exception {
        mockMvc.perform(get("/dashboard/10/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));

        verify(wishListService, never()).deleteWishListById(anyInt());
    }
}
