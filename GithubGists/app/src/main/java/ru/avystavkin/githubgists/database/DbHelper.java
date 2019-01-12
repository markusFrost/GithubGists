package ru.avystavkin.githubgists.database;

import java.util.List;

import ru.avystavkin.githubgists.models.local.Gist;

public class DbHelper {
    private AppDatabase mAppDatabase;
    public DbHelper(AppDatabase appDatabase) {
        mAppDatabase = appDatabase;
    }

    public void insert(List<Gist> gists) {
//        for (Gist gist : gists) {
//            long id = mAppDatabase.gistDao().insert(gist.getUser());
//        }
    }
}
