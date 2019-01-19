package ru.avystavkin.githubgists.models.server

import com.google.gson.annotations.SerializedName

class GistCommitsHistoryServer {
    @SerializedName("total")
    var total: Int = 0

    @SerializedName("additions")
    var additions: Int = 0

    @SerializedName("deletions")
    var deletions: Int = 0
}
