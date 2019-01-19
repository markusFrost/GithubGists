package ru.avystavkin.githubgists.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User {

    @PrimaryKey
    @ColumnInfo(name = "user_id")
    var id: Long = 0

    @ColumnInfo(name = "user_name")
    var name: String? = null

    @ColumnInfo(name = "user_url")
    var url: String? = null

    @ColumnInfo(name = "user_gists_count")
    var gistsCount: Int = 0
}
