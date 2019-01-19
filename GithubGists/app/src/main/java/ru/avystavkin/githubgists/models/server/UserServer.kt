package ru.avystavkin.githubgists.models.server

import com.google.gson.annotations.SerializedName

data class UserServer (
        @SerializedName("id") val id: Long,
        @SerializedName("login") val login: String?,
        @SerializedName("avatar_url") var avatarUrl: String?)
