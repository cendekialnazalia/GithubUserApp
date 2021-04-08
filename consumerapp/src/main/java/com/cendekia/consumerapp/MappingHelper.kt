package com.cendekia.consumerapp

import android.database.Cursor

object MappingHelper {
    fun mapCursorToArrayList(cursor: Cursor?):ArrayList<User>{
        val list = ArrayList<User>()
        if (cursor != null){
            while (cursor.moveToNext()){
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUsersColumns.ID))
                val username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUsersColumns.USERNAME))
                val avatarUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUsersColumns.AVATAR_URL))
                list.add(
                    User(
                        username,
                        id,
                        avatarUrl
                    )
                )
            }
        }
        return list
    }

}