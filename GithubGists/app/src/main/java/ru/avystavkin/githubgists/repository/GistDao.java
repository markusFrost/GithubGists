package ru.avystavkin.githubgists.repository;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.User;

@Dao
public interface GistDao {

    @Insert
    long insert(User user);

    @Insert
    long insert(Gist gist);

    @Query("SELECT * FROM gist")
    List<Gist> getGists();

    @Query("DELETE FROM user")
    void clearUserTable();

    @Query("DELETE FROM gist")
    void clearGistTable();
}
