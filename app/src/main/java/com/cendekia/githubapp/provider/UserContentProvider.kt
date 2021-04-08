package com.cendekia.githubapp.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.cendekia.githubapp.repositories.localdatasource.FavoriteUserDao
import com.cendekia.githubapp.repositories.localdatasource.UserDatabase

class UserContentProvider : ContentProvider() {

    private lateinit var userDao: FavoriteUserDao

    companion object {
        private const val AUTHORITY = "com.cendekia.githubapp"
        private const val TABLE_NAME = "favorite_user"
        const val ID_FAVORITE_USER_DATA = 1
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, TABLE_NAME, ID_FAVORITE_USER_DATA)
        }
    }

    override fun onCreate(): Boolean {
        userDao = context?.let { ctx ->
            UserDatabase.getDatabase(ctx)?.favoriteUserDao()
        }!!
        return false
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val cursor : Cursor?
        when(uriMatcher.match(uri)){
            ID_FAVORITE_USER_DATA -> {
                cursor = userDao.findAll()
                if (context != null){
                    cursor.setNotificationUri(context?.contentResolver, uri)
                }
            }
            else -> {
                cursor = null
            }
        }
        return cursor
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}