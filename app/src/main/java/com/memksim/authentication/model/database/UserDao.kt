package com.memksim.authentication.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.memksim.authentication.model.User

@Dao
interface UserDao {

    @Insert
    fun saveUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM users")
    fun getUsers(): List<User>

    @Query("SELECT * FROM users WHERE _googleToken == :token")
    fun getUserGoogle(token: String): User

    @Query("SELECT * FROM users WHERE _govToken == :token")
    fun getUserGov(token: String): User

    @Query("SELECT * FROM users WHERE _vkToken == :token")
    fun getUserVk(token: String): User

    @Query("SELECT * FROM users WHERE _facebookToken == :token")
    fun getUserFacebook(token: String): User

}