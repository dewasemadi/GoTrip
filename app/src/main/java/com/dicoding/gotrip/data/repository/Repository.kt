package com.dicoding.gotrip.data.repository

import com.dicoding.gotrip.data.local.TourismDao
import com.dicoding.gotrip.data.local.TourismEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val tourismDao: TourismDao) {
    fun getAllTourism() = tourismDao.getAllTourism()
    fun getAllFavoriteTourism() = tourismDao.getAllFavoriteTourism()
    fun getTourism(id: Int) = tourismDao.getTourism(id)
    fun searchTourism(query: String) = tourismDao.searchTourism(query)
    suspend fun insertAllTourism(tourism: List<TourismEntity>) = tourismDao.insertAllTourism(tourism)
    suspend fun updateFavoriteTourism(id: Int, isFavorite: Boolean) = tourismDao.updateFavoriteTourism(id, isFavorite)
}