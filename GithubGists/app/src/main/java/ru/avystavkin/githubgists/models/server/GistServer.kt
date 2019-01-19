package ru.avystavkin.githubgists.models.server

import com.google.gson.annotations.SerializedName

data class GistServer(
        @SerializedName("id") val id: String?,
        @SerializedName("url") val url: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("owner") val user: UserServer?,
        @SerializedName("files") val files: Map<String, GistFileInfoServer>?)