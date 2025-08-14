package com.example.movieapp_w_compose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PicturesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdatePicture(picture: PicturesEntity)

    @Query("SELECT * FROM pictures WHERE userId = :userId LIMIT 1")
    suspend fun getUserPicture(userId: String): PicturesEntity?

}