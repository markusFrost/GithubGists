package ru.avystavkin.githubgists.repository;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import ru.avystavkin.githubgists.models.local.Gist;

@Dao
public interface GistDao {

    @Insert
    long[] insert(List<Gist> gists);

    @Insert
    long insert(Gist gist);

//    @Insert
//    long insert(User user);

    @Query("SELECT * FROM gist")
    List<Gist> getGists();
}
