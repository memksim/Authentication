package com.memksim.authentication.viewmodel.repos

import android.app.Application
import com.memksim.authentication.model.User
import com.memksim.authentication.model.database.UserDao
import com.memksim.authentication.model.database.UserDatabase

class DatabaseRepository(appContext: Application) {

    private val database: UserDatabase = UserDatabase.getDataBase(appContext)
    private val dao: UserDao = database.getDao()

    fun saveUser(user: User){
        dao.saveUser(user)
    }

    fun getUsersList(): List<User>{
        return dao.getUsers()
    }

    fun getGoogleUser(googleToken: String): User{
        return dao.getUserGoogle(googleToken)
    }

    fun getGovUser(govToken: String): User{
        return dao.getUserGov(govToken)
    }

    fun getVkUser(vkToken: String): User{
        return dao.getUserVk(vkToken)
    }

    fun getFacebookUser(facebookToken: String): User{
        return dao.getUserFacebook(facebookToken)
    }

}