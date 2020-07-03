package com.crp.shaadimilan.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crp.shaadimilan.model.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserList(posts: List<UserModel>)

    @Query("SELECT * FROM usermodel")
    fun getAllUsers(): Flow<List<UserModel>>
}
