package ru.avystavkin.githubgists.database;

import java.util.List;

import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.User;
import ru.avystavkin.githubgists.repository.GistDao;

public class DbHelper {
    private GistDao mGistDao;
    public DbHelper(AppDatabase appDatabase) {
        mGistDao = appDatabase.gistDao();
    }

    public void insert(List<Gist> gists) {
        mGistDao.clearUserTable();
        mGistDao.clearGistTable();

        for (Gist gist : gists) {
            mGistDao.insert(gist.getUser());
            mGistDao.insert(gist);
        }
    }

    public List<Gist> getGists() {
       return mGistDao.getGists();
    }

    public List<User> getPopularUsers(int count) {
        return mGistDao.getPopularUsers(count);
    }
}
