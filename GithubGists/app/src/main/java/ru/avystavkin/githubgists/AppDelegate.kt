package ru.avystavkin.githubgists

import android.app.Application
import android.content.Context
import ru.avystavkin.githubgists.di.AppComponent
import ru.avystavkin.githubgists.di.DaggerAppComponent
import ru.avystavkin.githubgists.di.DataModule

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()

        context = this

        appComponent = DaggerAppComponent.builder()
                .dataModule(DataModule())
                .build()
    }

    companion object {

        lateinit var context: Context
            private set
        lateinit var appComponent: AppComponent
            private set
    }
}
