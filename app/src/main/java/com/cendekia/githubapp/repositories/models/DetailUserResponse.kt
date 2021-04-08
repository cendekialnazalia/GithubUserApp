package com.cendekia.githubapp.repositories.models

data class DetailUserResponse(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val followers: Int,
    val following: Int,
    val public_repos: Int,
    val location: String,
    val company: String,
    val blog: String,
    val name:String
)