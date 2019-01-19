package ru.avystavkin.githubgists.models.server

import com.google.gson.annotations.SerializedName

data class GistCommitsHistoryServer (
        @SerializedName("total") val total: Int,
        @SerializedName("additions") val additions: Int,
        @SerializedName("deletions") var deletions: Int)