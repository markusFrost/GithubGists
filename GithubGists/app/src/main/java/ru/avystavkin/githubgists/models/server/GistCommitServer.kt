package ru.avystavkin.githubgists.models.server

import com.google.gson.annotations.SerializedName

data class GistCommitServer(
        @SerializedName("user") val user: UserServer?,
        @SerializedName("version") val version: String?,
        @SerializedName("committed_at") val committedAt: String?,
        @SerializedName("change_status") val status: GistCommitsHistoryServer?,
        @SerializedName("url") val url: String?)
