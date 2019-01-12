package ru.avystavkin.githubgists;

import android.app.Application;
import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.di.AppComponent;
import ru.avystavkin.githubgists.di.DaggerAppComponent;
import ru.avystavkin.githubgists.di.DataModule;

public class AppDelegate extends Application {

    private static Context sContext;
    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;

        Picasso picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(this))
                .build();
        Picasso.setSingletonInstance(picasso);

        sAppComponent = DaggerAppComponent.builder()
                .dataModule(new DataModule())
                .build();
    }

    @NonNull
    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    @NonNull
    public static Context getContext() {
        return sContext;
    }
}
