package com.example.movieapp_w_compose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PicturesEntity::class], version = 1)
abstract class PicturesDatabase : RoomDatabase() {
    abstract val picturesDao: PicturesDao
}