package ru.avystavkin.githubgists.repository;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.User;

@Dao
public interface GistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User user);

    @Insert
    long insert(Gist gist);

    @Query("SELECT * FROM gist")
    List<Gist> getGists();

    @Query("select gist.f_user_id as user_id, count(gist.f_user_id) as user_gists_count,\n" +
            "user.user_name as user_name, user.user_url as user_url, user.user_id\n" +
            "from Gist INNER JOIN User\n" +
            "where (gist.f_user_id = user.user_id)\n" +
            "group by user_id\n" +
            "order by user_gists_count DESC\n" +
            "LIMIT :count")
    List<User> getPopularUsers(int count);

    @Query("DELETE FROM user")
    void clearUserTable();

    @Query("DELETE FROM gist")
    void clearGistTable();
}
