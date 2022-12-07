package com.dicoding.gotrip.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tourism")
data class TourismEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val description: String,
    val location: String,
    val photoUrl: String,
    val rating: Double,
    val totalReview: Int,
    var isFavorite: Boolean = false,
)
