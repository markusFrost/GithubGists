package ru.avystavkin.githubgists.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.User;
import ru.avystavkin.githubgists.repository.GistDao;

@Database(entities = {Gist.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GistDao gistDao();
}
