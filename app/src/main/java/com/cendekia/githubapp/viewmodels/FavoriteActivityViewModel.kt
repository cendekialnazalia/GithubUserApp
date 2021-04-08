package com.cendekia.githubapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cendekia.githubapp.repositories.localdatasource.FavoriteUser
import com.cendekia.githubapp.repositories.localdatasource.FavoriteUserDao
import com.cendekia.githubapp.repositories.localdatasource.UserDatabase

class FavoriteActivityViewModel(application: Application): AndroidViewModel(application) {

    private var userDao: FavoriteUserDao?
    private var userDb: UserDatabase? = UserDatabase.getDatabase(application)

    init {
        userDao = userDb?.favoriteUserDao()
    }

    fun getFavoriteUser():LiveData<List<FavoriteUser>>?{
        return userDao?.getFavoriteUser()
    }
}