package ru.avystavkin.githubgists.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.avystavkin.githubgists.models.database.Gist
import ru.avystavkin.githubgists.models.database.User
import ru.avystavkin.githubgists.repository.GistDao

@Database(entities = [Gist::class, User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gistDao(): GistDao
}
