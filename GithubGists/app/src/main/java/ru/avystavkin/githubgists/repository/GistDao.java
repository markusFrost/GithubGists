package ru.avystavkin.githubgists.repository;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import ru.avystavkin.githubgists.models.local.Gist;

@Dao
public interface GistDao {

    @Insert
    void insert(Gist gist);

    @Query("SELECT * FROM gist")
    List<Gist> getGists();
}
