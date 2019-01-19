package ru.avystavkin.githubgists.models.server

import com.google.gson.annotations.SerializedName


data class GistFileInfoServer (
        @SerializedName("filename") val fileName: String?,
        @SerializedName("content") var content: String?)
