package ru.avystavkin.githubgists

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import ru.avystavkin.githubgists.di.AppComponent
import ru.avystavkin.githubgists.di.DaggerAppComponent
import ru.avystavkin.githubgists.di.DataModule

class AppDelegate : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        context = this

        appComponent = DaggerAppComponent.builder()
                .dataModule(DataModule())
                .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this);
    }

    companion object {

        lateinit var context: Context
            private set
        lateinit var appComponent: AppComponent
            private set
    }
}
