package ru.avystavkin.githubgists.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey @ColumnInfo(name = "user_id") var id: Long,
    @ColumnInfo(name = "user_name") var name: String?,
    @ColumnInfo(name = "user_url") var url: String?,
    @ColumnInfo(name = "user_gists_count") var gistsCount: Int) {
    constructor() : this (0,"", "", 0)
}
