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


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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

//    @Test
//    void createNewWishlist() {
//        rep.createNewWishList(new WishList(1, "testWishlist"));
//        var at = rep.getWishLists();
//        for (WishList wl : at) {
//            if (wl.getWishlist_name().equals("testWishlist")) {
//                assertThat(wl).isNotNull();
//                assertThat(wl.getWichlist_id() == 2);
//            }
//        }
//    }


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




}
