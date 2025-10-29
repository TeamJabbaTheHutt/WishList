package com.hauxy.wishlist;

import com.hauxy.wishlist.Repository.DAO.DAO;
import com.hauxy.wishlist.service.WishListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WishListServiceTest {

    @Mock
    private DAO dao;

    @InjectMocks
    private WishListService wishListService;


}
