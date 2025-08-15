package com.example.movieapp_w_compose.di.module

import android.content.Context
import androidx.room.Room
import com.example.movieapp_w_compose.data.local.PicturesDao
import com.example.movieapp_w_compose.data.local.PicturesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent :: class)
object RoomModule {
    @Provides
    @Singleton
    fun providePicturesDatabase(@ApplicationContext context: Context): PicturesDatabase {
        return Room.databaseBuilder(
            context,
            PicturesDatabase::class.java,
            "pictures_db"
        ).build()
    }

    @Provides
    fun providePicturesDao(db: PicturesDatabase): PicturesDao = db.picturesDao
}