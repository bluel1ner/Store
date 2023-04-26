package com.example.userservice.service;

import com.example.userservice.dto.request.FavoriteRequest;
import com.example.userservice.dto.response.FavoriteResponse;
import com.example.userservice.entity.Favorite;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/26/2023 9:52 PM
 */
public interface FavoriteService {
    FavoriteResponse addProductToFavorite(FavoriteRequest favoriteRequest);
    List<FavoriteResponse> getAllUserFavorite();
    void deleteFromFavoriteById(Integer favoriteId);
}
