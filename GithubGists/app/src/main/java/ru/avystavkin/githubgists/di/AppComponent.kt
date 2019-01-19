package ru.avystavkin.githubgists.di

import dagger.Component
import ru.avystavkin.githubgists.screen.gist_detail.GistDetailActivity
import ru.avystavkin.githubgists.screen.main.MainPageActivity
import ru.avystavkin.githubgists.screen.user_detail.UserDetailActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface AppComponent {

    fun injectGistActivity(activity: MainPageActivity)

    fun injectGistDetailActivity(activity: GistDetailActivity)

    fun injectUserDetailActivity(activity: UserDetailActivity)
}
