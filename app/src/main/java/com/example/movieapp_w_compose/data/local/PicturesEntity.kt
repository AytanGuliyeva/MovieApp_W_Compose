package com.example.movieapp_w_compose.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pictures")
data class PicturesEntity(
    @PrimaryKey val userId: String,
    val username: String,
    val pictureUri: String
)