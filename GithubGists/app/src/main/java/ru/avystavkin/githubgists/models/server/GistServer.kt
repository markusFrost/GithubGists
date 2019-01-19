package ru.avystavkin.githubgists.models.server

import com.google.gson.annotations.SerializedName

class GistServer {

    @SerializedName("id")
    var id: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("owner")
    var user: UserServer? = null

    @SerializedName("files")
    var files: Map<String, GistFileInfoServer>? = null
}
