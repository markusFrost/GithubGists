package ru.avystavkin.githubgists.models.database

import androidx.room.*

@Entity
data class Gist(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "gist_int_id") var intId: Long,
        @ColumnInfo(name = "f_user_id") var userId: Long,
        var id: String?,
        var url: String?,
        var name: String?,
        @Ignore @Embedded var user: User?,
        @Ignore var listFiles: List<GistFileInfo>?) {
    constructor() : this(0,0,"","","",null,null)
}

