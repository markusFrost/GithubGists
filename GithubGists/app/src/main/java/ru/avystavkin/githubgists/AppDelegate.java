package ru.avystavkin.githubgists;

import android.app.Application;
import android.content.Context;

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
