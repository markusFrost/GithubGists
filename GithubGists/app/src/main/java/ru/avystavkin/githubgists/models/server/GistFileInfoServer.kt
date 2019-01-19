package ru.avystavkin.githubgists.models.server


import com.google.gson.annotations.SerializedName

class GistFileInfoServer {

    @SerializedName("filename")
    var fileName: String? = null

    @SerializedName("content")
    var content: String? = null
}
