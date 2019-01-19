package ru.avystavkin.githubgists.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.avystavkin.githubgists.AppDelegate
import ru.avystavkin.githubgists.BuildConfig
import ru.avystavkin.githubgists.api.GithubService
import ru.avystavkin.githubgists.database.AppDatabase
import ru.avystavkin.githubgists.database.DbHelper
import ru.avystavkin.githubgists.repository.github.DefaultGithubRepository
import ru.avystavkin.githubgists.repository.github.GithubRepository
import ru.markusfrost.okhttplibrary.ApiKeyInterceptor
import ru.markusfrost.okhttplibrary.LoggingInterceptor
import ru.markusfrost.okhttplibrary.MockingInterceptor
import java.util.*
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    internal fun provideGithubRepository(
            dbHelper: DbHelper,
            githubService: GithubService): GithubRepository {
        return DefaultGithubRepository(dbHelper, githubService)
    }

    @Provides
    @Singleton
    internal fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(AppDelegate.context,
                AppDatabase::class.java, "database").build()
    }

    @Provides
    @Singleton
    internal fun provideDbHelper(
            appDatabase: AppDatabase): DbHelper {
        return DbHelper(appDatabase)
    }

    @Provides
    @Singleton
    internal fun provideGithubService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor.create())
                .addInterceptor(ApiKeyInterceptor.create())
                .build()
    }

    @Provides
    @Singleton
    internal fun provideResponsesMap(): Map<String, String> {
        val responsesMap = HashMap<String, String>()
        responsesMap["/gists/public"] = "gists.json"
        responsesMap["/gists/id"] = "gists_detail.json"
        responsesMap["/gists/id/commits"] = "gist_commits.json"
        responsesMap["/users/name/gists"] = "user_detail.json"
        return responsesMap
    }

//    @Provides
//    @Singleton
//    internal fun provideOkHttpClient(responsesMap: Map<String, String>): OkHttpClient {
//        return OkHttpClient.Builder()
//                .addInterceptor(MockingInterceptor.create(AppDelegate.context, responsesMap))
//                .build()
//    }
}
