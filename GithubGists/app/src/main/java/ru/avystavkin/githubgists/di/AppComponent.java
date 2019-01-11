package ru.avystavkin.githubgists.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.avystavkin.githubgists.screen.main.MainPageActivity;
import ru.avystavkin.githubgists.screen.gist_detail.GistDetailActivity;
import ru.avystavkin.githubgists.screen.user_detail.UserDetailActivity;

@Singleton
@Component(modules = {DataModule.class})
public interface AppComponent {

    void injectGistActivity(MainPageActivity activity);

    void injectGistDetailActivity(GistDetailActivity activity);

    void injectUserDetailActivity(UserDetailActivity activity);
}
