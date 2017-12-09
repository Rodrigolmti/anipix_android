package com.rodrigolmti.anipix.model.controller

import android.content.Context
import com.google.gson.Gson
import com.rodrigolmti.anipix.model.`interface`.IFavoriteController
import com.rodrigolmti.anipix.model.dto.AnimeDTO
import com.google.gson.reflect.TypeToken


/**
 * Created by rodrigolmti on 08/12/17.
 */
class FavoriteController {

    companion object : IFavoriteController {

        private var favorites: MutableList<AnimeDTO> = ArrayList()
        private lateinit var context: Context

        override fun onAddFavorite(animeDTO: AnimeDTO) {
            favorites.forEach {
                if (it.id == animeDTO.id) return
            }
            favorites.add(animeDTO)
            setPersistFavorites(false)
        }

        override fun onRemoveFavorite(animeDTO: AnimeDTO) {
            var contain = false
            var index = 0
            for (i in favorites.indices) {
                if (favorites[i].id == animeDTO.id) {
                    contain = true
                    index = i
                }
            }

            if (contain) favorites.removeAt(index)
            setPersistFavorites(false)
        }

        override fun onCheckIsFavorite(animeDTO: AnimeDTO): Boolean {
            return favorites.indices.any { favorites[it].id == animeDTO.id }
        }

        override fun onSearchFavorites(): List<AnimeDTO> {
            return favorites
        }

        override fun onClearFavorites() {
            setPersistFavorites(true)
            favorites = ArrayList()
        }

        private fun configFavorites() {
            try {
                val stringFavorite: String = PreferencesController(context).favorites
                if (stringFavorite.isEmpty()) return
                val listType = object : TypeToken<ArrayList<AnimeDTO>>() {}.type
                favorites = Gson().fromJson(stringFavorite, listType)
            } catch (error: Exception) {
                error.printStackTrace()
            }
        }

        private fun setPersistFavorites(clear: Boolean) {
            try {
                val stringFavorite: String = Gson().toJson(favorites)
                if (stringFavorite.isEmpty()) return
                if (clear) {
                    PreferencesController(context).favorites = ""
                } else {
                    PreferencesController(context).favorites = stringFavorite
                }
            } catch (error: Exception) {
                error.printStackTrace()
            }
        }

        fun init(context: Context) {
            this.context = context
            configFavorites()
        }
    }
}