package com.hauxy.wishlist;

import com.hauxy.wishlist.controller.WishListController;
import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.model.WishList;
import com.hauxy.wishlist.service.WishListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.servlet.http.HttpSession;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishListController.class)
class WishListControllerUITest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishListService wishListService;

    @Test
    void dashboardShowsEmptyState() throws Exception {
        User user = new User("John", "john@mail.com", "123");
        user.setUser_id(1);

        when(wishListService.getWishListsForUser(1)).thenReturn(List.of());

        mockMvc.perform(get("/dashboard").sessionAttr("loggedInUser", user))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andExpect(model().attribute("wishlists", List.of()));
    }

    @Test
    void dashboardShowsMultipleWishlists() throws Exception {
        User user = new User("John", "john@mail.com", "123");
        user.setUser_id(1);

        WishList list1 = new WishList(1, "Birthday");
        list1.setWishListId(1);

        WishList list2 = new WishList(1, "Christmas");
        list2.setWishListId(2);

        List<WishList> lists = List.of(list1, list2);

        when(wishListService.getWishListsForUser(1)).thenReturn(lists);

        mockMvc.perform(get("/dashboard").sessionAttr("loggedInUser", user))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andExpect(model().attribute("wishlists", lists));
    }



    @Test
    void deleteWishListRedirects() throws Exception {
        User user = new User("John", "john@mail.com", "123");
        user.setUser_id(1);

        mockMvc.perform(get("/dashboard/3/delete").sessionAttr("loggedInUser", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));
    }

    @Test
    void createWishlistRedirects() throws Exception {
        User user = new User("John", "john@mail.com", "123");
        user.setUser_id(1);

        when(wishListService.createNewWishList(any(WishList.class))).thenReturn("Success");

        mockMvc.perform(post("/dashboard/create")
                        .param("wishlistName", "My New List")
                        .sessionAttr("loggedInUser", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));
    }

}
