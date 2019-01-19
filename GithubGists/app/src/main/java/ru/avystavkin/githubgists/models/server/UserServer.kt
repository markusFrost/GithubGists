package ru.avystavkin.githubgists.models.server

import com.google.gson.annotations.SerializedName

class UserServer {

    @SerializedName("id")
    var id: Long = 0

    @SerializedName("login")
    var login: String? = null

    @SerializedName("avatar_url")
    var avatarUrl: String? = null
}
