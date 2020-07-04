package com.crp.shaadimilan.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.crp.shaadimilan.model.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserList(posts: List<UserModel>)

    @Query("SELECT * FROM usermodel")
    fun getAllUsers(): Flow<List<UserModel>>

    @Update
    fun updateStatus(userModel: UserModel):Int
}
