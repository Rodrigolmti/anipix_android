package com.rodrigolmti.anipix.model.`interface`

import com.rodrigolmti.anipix.model.dto.AnimeDTO

/**
 * Created by rodrigolmti on 08/12/17.
 */
interface IFavoriteController {

    fun onAddFavorite(animeDTO: AnimeDTO)
    fun onRemoveFavorite(animeDTO: AnimeDTO)
    fun onSearchFavorites(): List<AnimeDTO>
    fun onCheckIsFavorite(animeDTO: AnimeDTO): Boolean
    fun onClearFavorites()
}