package com.hauxy.wishlist;


import com.hauxy.wishlist.Repository.repository.WishListRepository;
import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.model.Wish;
import com.hauxy.wishlist.model.WishList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
public class WishListRepositoryTest {

    @Autowired
    private WishListRepository rep;



    // Test User
    @Test
    void createNewUser() {
        rep.createNewUser(new User("testMail.com", "testName", "testPassword"));
        var at = rep.getUsers();
        for (User user : at) {
            if (user.getUsername().equals("testName")) {
                assertThat(user).isNotNull();
                assertThat(user.getPassword()).isEqualTo("testPassword");
                assertThat(user.getEmail()).isEqualTo("testMail.com");
            }
        }
    }

    @Test
    void modifyUser() {
        var at = rep.getUserById(1);
        assertThat(at.getUsername()).isEqualTo("admin");

        rep.deleteUserById(1);

        assertThatThrownBy(() -> rep.getUserById(1))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    // Test WishList

    @Test
    void createNewWishlist() {
        rep.createNewWishList(new WishList(1, "testWishlist"));
        var wishLists = rep.getWishLists();

        for (WishList w : wishLists) {
            if (w.getWishlist_name().equals("testWishlist")) {
                assertThat(w).isNotNull();
                assertThat(w.getWichlist_id()).isEqualTo(2);
            }
        }


    }


    @Test
    void modifyWishList() {
        var at = rep.getWishListById(1);
        assertThat(at.getWishlist_name()).isEqualTo("test");

        rep.deleteWishListById(at.getWichlist_id());
        var at2 = rep.getWishLists();
        for (WishList w : at2) {
            assertThat(w.getWichlist_id() != at.getWichlist_id());
        }



    }


    @Test
    void createNewWish() {
        rep.createNewWish(new Wish("testWishName", 200.2, "link.com"));
        var at = rep.getWishes();
        for (Wish w : at) {
            if (w.getWish_name().equals("testWishName")){
                assertThat(w.getWish_link()).isEqualTo("link.com");
            }
        }
    }

    @Test
    void modifyWish() {
        var at = rep.getWishById(1);
        assertThat(at.getWish_name()).isEqualTo("test1");
        rep.updateWish(new Wish("testUpdatedWishName", 200.2, "newLink.com"), at.getWish_id());
        var at2 = rep.getWishById(1);
        assertThat(at2.getWish_name()).isEqualTo("testUpdatedWishName");
        assertThat(at2.getWish_link()).isEqualTo("newLink.com");
        assertThat(at2.getWish_price() == 200.2);


        rep.deleteWishById(at.getWish_id());

        var at3 = rep.getWishes();
        for (Wish wish : at3) {
            assertThat(wish.getWish_price() != 200.2);
        }
    }


    @Test
    void getWishListsWishWishes() {
        var at = rep.getWishLists();
        for (WishList w : at) {
            assertThat(w.getWishes()).isNotNull();
            List<Wish> wishesInWishList = w.getWishes();
            if (w.getWichlist_id() == 1) {
                for (Wish wish : wishesInWishList) {
                    if (wish.getWish_name().equals("test1")) {
                        assertThat(wish.getWish_price() == 192.232);
                    }
                }
            }

        }
    }


    @Test
    void getSpecificWishListWithWishes() {
        var at = rep.getWishListById(1);
        List<Wish> wishesInWishList = at.getWishes();
        for (Wish wish : wishesInWishList) {
            if (wish.getWish_name().equals("test1")){
                assertThat(wish.getWish_price() == 192.232);
            }
        }
    }

    @Test
    void addNewWish() {
        WishList wishList = rep.getWishListById(1);

        Wish wish = new Wish("testWish", 200.1, "link.com");
        wish = rep.insertWish(wish);

        wishList.insertNewWish(wish);

        rep.updateWishList(wishList);

        var at = rep.getWishListById(wishList.getWichlist_id());
        List<Wish> wishes = at.getWishes();
        assertTrue(wishes.stream().anyMatch(w -> w.getWish_name().equals("testWish") && w.getWish_price() == 200.1));
    }

    @Test
    void deleteWishFromSpecificWishlist() {
        WishList wl = rep.getWishListById(1);

        Wish wish = wl.getWishes().get(0);
        rep.deleteWishFromWishlist(wl, wish);
        WishList updated = rep.getWishListById(1);
        boolean stillThere = false;
        for (Wish w : updated.getWishes()) {
            if (w.getWish_id() == wish.getWish_id()) {
                stillThere = true;
            }
        }

        assertFalse(stillThere, "Wish should be removed from this wishlist");
    }

    @Test
    void deleteWishlistAndLinkedWishes() {
        WishList wl = rep.getWishListById(1);

        assertTrue(!wl.getWishes().isEmpty(), "Wishlist should have wishes before deleting");

        rep.deleteWishlistInWishes_per_wishlist(wl);

        var at = rep.getWishLists();
        assertTrue(at.isEmpty());
        for (WishList wishList : at) {
            assertThat(wishList.getWishes().isEmpty());
            assertThat(wishList.getWishlist_name().isEmpty());
        }

        var at2 = rep.getWishesPerWishlist(wl);
        assertTrue(at2.isEmpty());

    }



}
