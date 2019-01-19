package ru.avystavkin.githubgists.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.avystavkin.githubgists.models.database.Gist
import ru.avystavkin.githubgists.models.database.User

@Dao
interface GistDao {

    @get:Query("SELECT * FROM gist")
    val gists: List<Gist>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Insert
    fun insert(gist: Gist): Long

    @Query("select gist.f_user_id as user_id, count(gist.f_user_id) as user_gists_count,\n" +
            "user.user_name as user_name, user.user_url as user_url, user.user_id\n" +
            "from Gist INNER JOIN User\n" +
            "where (gist.f_user_id = user.user_id)\n" +
            "group by user_id\n" +
            "order by user_gists_count DESC\n" +
            "LIMIT :count")
    fun getPopularUsers(count: Int): List<User>

    @Query("DELETE FROM user")
    fun clearUserTable()

    @Query("DELETE FROM gist")
    fun clearGistTable()
}
