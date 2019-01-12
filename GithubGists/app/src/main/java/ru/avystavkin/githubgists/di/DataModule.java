package ru.avystavkin.githubgists.di;

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
import ru.avystavkin.githubgists.mock.MockingInterceptor;
import ru.avystavkin.githubgists.repository.github.DefaultGithubRepository;
import ru.avystavkin.githubgists.repository.github.GithubRepository;

@Module
public class DataModule {

    @Provides
    @Singleton
    GithubRepository provideGithubRepository(
            @NonNull GithubService githubService) {
        return new DefaultGithubRepository(githubService);
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase() {
        return  Room.databaseBuilder(AppDelegate.getContext(),
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
//                    .addInterceptor(LoggingInterceptor.create())
//                    .addInterceptor(ApiKeyInterceptor.create())
                .addInterceptor(MockingInterceptor.create())
                .build();
    }
}
