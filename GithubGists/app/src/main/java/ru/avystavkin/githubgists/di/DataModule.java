package ru.avystavkin.githubgists.di;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.avystavkin.githubgists.BuildConfig;
import ru.avystavkin.githubgists.api.GithubService;
import ru.avystavkin.githubgists.mock.MockingInterceptor;
import ru.avystavkin.githubgists.repository.DefaultGithubRepository;
import ru.avystavkin.githubgists.repository.GithubRepository;

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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
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
