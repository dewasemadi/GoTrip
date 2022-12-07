package com.dicoding.gotrip.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TourismDao {
    @Query("SELECT * FROM tourism")
    fun getAllTourism(): Flow<List<TourismEntity>>

    @Query("SELECT * FROM tourism WHERE isFavorite = 1")
    fun getAllFavoriteTourism(): Flow<List<TourismEntity>>

    @Query("SELECT * FROM tourism WHERE id = :id")
    fun getTourism(id: Int): Flow<TourismEntity>

    @Query("SELECT * FROM tourism WHERE name LIKE '%' || :query || '%'")
    fun searchTourism(query: String): Flow<List<TourismEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTourism(tourismList: List<TourismEntity>)

    @Query("UPDATE tourism SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteTourism(id: Int, isFavorite: Boolean)
}