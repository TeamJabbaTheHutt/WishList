package com.hauxy.wishlist;

import com.hauxy.wishlist.Repository.repository.WishListRepository;

import com.hauxy.wishlist.model.Wish;
import com.hauxy.wishlist.model.WishList;
import com.hauxy.wishlist.service.WishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WishServiceTest {

    @Mock
    private WishListRepository wishListRepository;

    @InjectMocks
    private WishService wishService;

    @Test
    void testCreateNewWishSuccess() {
        Wish w = new Wish();
        WishList wishList = new WishList();
        when(wishListRepository.createNewWish(w)).thenReturn(1);
        wishService.createNewWish(wishList, w);

    }

    @Test
    void testCreateNewWishFail() {

    }

    @Test
    void testGetWishes() {
        Wish w = new Wish();
        when(wishListRepository.getWishes()).thenReturn(List.of(w));
        List<Wish> result = wishService.getWishes();
        assertEquals(1, result.size());
    }

    @Test
    void testGetWishById() {
        Wish w = new Wish();
        when(wishListRepository.getWishById(5)).thenReturn(w);
        Wish result = wishService.getWishById(5);
        assertEquals(w, result);
    }

    @Test
    void testUpdateWishSuccess() {
        Wish w = new Wish();
        when(wishListRepository.updateWish(w, 3)).thenReturn(1);
        String result = wishService.updateWish(w, 3);
        assertEquals("Success", result);
    }

    @Test
    void testUpdateWishFail() {
        Wish w = new Wish();
        when(wishListRepository.updateWish(w, 3)).thenReturn(0);
        String result = wishService.updateWish(w, 3);
        assertEquals("Failed", result);
    }

    @Test
    void testDeleteWishByIdSuccess() {
        when(wishListRepository.deleteUserById(7)).thenReturn(1);
        String result = wishService.deleteWishById(7);
        assertEquals("Success", result);
    }

    @Test
    void testDeleteWishByIdFail() {
        when(wishListRepository.deleteUserById(7)).thenReturn(0);
        String result = wishService.deleteWishById(7);
        assertEquals("Failed", result);
    }
}





