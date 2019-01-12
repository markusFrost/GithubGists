package ru.avystavkin.githubgists.database;

import java.util.List;

import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.repository.GistDao;

public class DbHelper {
    private GistDao mGistDao;
    public DbHelper(AppDatabase appDatabase) {
        mGistDao = appDatabase.gistDao();
    }

    public void insert(List<Gist> gists) {

        try {
            long[] ids = mGistDao.insert(gists);

            List<Gist> list = mGistDao.getGists();

            System.out.println();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
