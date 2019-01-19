package ru.avystavkin.githubgists.models.local

import androidx.room.*

@Entity
class Gist {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "gist_int_id")
    var intId: Long = 0

    @ColumnInfo(name = "f_user_id")
    var userId: Long = 0

    var id: String? = null

    var url: String? = null

    var name: String? = null

    @Ignore
    @Embedded
    var user: User? = null

    @Ignore
    var listFiles: List<GistFileInfo>? = null
}
