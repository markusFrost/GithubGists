package ru.avystavkin.githubgists.di;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.avystavkin.githubgists.AppDelegate;
import ru.avystavkin.githubgists.BuildConfig;
import ru.avystavkin.githubgists.api.GithubService;
import ru.avystavkin.githubgists.database.AppDatabase;
import ru.avystavkin.githubgists.database.DbHelper;
import ru.avystavkin.githubgists.repository.github.DefaultGithubRepository;
import ru.avystavkin.githubgists.repository.github.GithubRepository;
import ru.markusfrost.okhttplibrary.ApiKeyInterceptor;
import ru.markusfrost.okhttplibrary.LoggingInterceptor;

@Module
public class DataModule {

    @Provides
    @Singleton
    GithubRepository provideGithubRepository(
            @NonNull DbHelper dbHelper,
            @NonNull GithubService githubService) {
        return new DefaultGithubRepository(dbHelper, githubService);
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase() {
        return  Room.databaseBuilder(AppDelegate.Companion.getContext(),
                AppDatabase.class, "database").build();
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(
            @NonNull AppDatabase appDatabase) {
       return new DbHelper(appDatabase);
    }

    @Provides
    @Singleton
    GithubService provideGithubService(@NonNull Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                    .addInterceptor(LoggingInterceptor.Companion.create())
                    .addInterceptor(ApiKeyInterceptor.Companion.create())
                .build();
    }

    @Provides
    @Singleton
    Map<String, String> provideResponsesMap() {
        Map<String, String> responsesMap = new HashMap<>();
        responsesMap.put("/gists/public", "gists.json");
        responsesMap.put("/gists/id", "gists_detail.json");
        responsesMap.put("/gists/id/commits", "gist_commits.json");
        responsesMap.put("/users/name/gists", "user_detail.json");
        return responsesMap;
    }

//    @Provides
//    @Singleton
//    OkHttpClient provideOkHttpClient(Map<String, String> responsesMap) {
//        return new OkHttpClient.Builder()
//                .addInterceptor(MockingInterceptor.create(AppDelegate.getContext(), responsesMap))
//                .build();
//    }
}
