package ru.avystavkin.githubgists.database

import ru.avystavkin.githubgists.models.local.Gist
import ru.avystavkin.githubgists.models.local.User
import ru.avystavkin.githubgists.repository.GistDao

class DbHelper(appDatabase: AppDatabase) {
    private val mGistDao: GistDao = appDatabase.gistDao()

    val gists: List<Gist> = mGistDao.gists

    fun insert(gists: List<Gist>) {
        mGistDao.clearUserTable()
        mGistDao.clearGistTable()

        for (gist in gists) {
            mGistDao.insert(gist.user)
            mGistDao.insert(gist)
        }
    }

    fun getPopularUsers(count: Int): List<User> {
        return mGistDao.getPopularUsers(count)
    }
}
