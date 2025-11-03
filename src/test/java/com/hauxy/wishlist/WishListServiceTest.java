package com.hauxy.wishlist;


import com.hauxy.wishlist.Repository.repository.WishListRepository;
import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.model.Wish;
import com.hauxy.wishlist.model.WishList;
import com.hauxy.wishlist.service.WishListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WishListServiceTest {

    @Mock
    private WishListRepository wishListRepository;

    @InjectMocks
    private WishListService wishListService;

    @Test
    void createNewWishList() {
        WishList wishList = new WishList(1, "wishlistName");
        when(wishListRepository.createNewWishList(wishList)).thenReturn(1);
        String result = wishListService.createNewWishList(wishList);

        assertEquals("Success", result);
        verify(wishListRepository).createNewWishList(wishList);
    }

    @Test
    void getWishListsReturnListOfWishListId() {
        List<WishList> wishLists = new ArrayList<>();
        wishLists.add(new WishList(1, "WishListName"));
        when(wishListRepository.getWishLists()).thenReturn(wishLists);
        List<WishList> result = wishListService.getWishLists();
        assertEquals(wishLists, result);
        verify(wishListRepository).getWishLists();
    }

    @Test
    void getWishListsByIDReturnSingleWishListIdObject() {
        WishList wishList = new WishList(1, "WishListName");
        when(wishListRepository.getWishListById(1)).thenReturn(wishList);
        WishList result = wishListService.getWishListById(1);
        assertEquals(wishList, result);
        verify(wishListRepository).getWishListById(1);
    }
    @Test
    void updateWishListReturnSuccess() {
        WishList wishList = new WishList(1, "WishListName");
        when(wishListRepository.updateWishList(wishList)).thenReturn(1);
        String result = wishListService.updateWishList(wishList);

        assertEquals("Success", result);
        verify(wishListRepository).updateWishList(wishList);
    }

    @Test
    void deleteWishListReturnSuccess() {
        when(wishListRepository.deleteWishListById(1)).thenReturn(1);
        String result = wishListService.deleteWishListById(1);
        assertEquals("Success", result);
        verify(wishListRepository).deleteWishListById(1);
    }

    @Test
    void testGetWishesPerWishlist() {
        WishList wl = new WishList();
        Wish w = new Wish();
        when(wishListRepository.getWishesPerWishlist(wl)).thenReturn(List.of(w));

        List<Wish> result = wishListService.getWishesPerWishlist(wl);

        assertEquals(1, result.size());
        verify(wishListRepository).getWishesPerWishlist(wl);
    }

    @Test
    void testUpdateWishListPerWish() {
        WishList wl = new WishList();

        wishListService.updateWishListPerWish(wl);

        verify(wishListRepository).updateWishListPerWish(wl);
    }

    @Test
    void testDeleteWishFromWishlist() {
        WishList wl = new WishList();
        Wish wish = new Wish();

        wishListService.deleteWishFromWishlist(wl, wish);

        verify(wishListRepository).deleteWishFromWishlist(wl, wish);
    }

    @Test
    void testDeleteWishlistInWishesSuccess() {
        WishList wl = new WishList();
        when(wishListRepository.deleteWishlistInWishes_per_wishlist(wl)).thenReturn(1);

        String result = wishListService.deleteWishlistInWishes_per_wishlist(wl);

        assertEquals("Success", result);
        verify(wishListRepository).deleteWishlistInWishes_per_wishlist(wl);
    }

    @Test
    void testDeleteWishlistInWishesFail() {
        WishList wl = new WishList();
        when(wishListRepository.deleteWishlistInWishes_per_wishlist(wl)).thenReturn(0);

        String result = wishListService.deleteWishlistInWishes_per_wishlist(wl);

        assertEquals("Failed", result);
        verify(wishListRepository).deleteWishlistInWishes_per_wishlist(wl);
    }



}

