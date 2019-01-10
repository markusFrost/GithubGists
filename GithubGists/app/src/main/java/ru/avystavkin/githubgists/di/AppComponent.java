package ru.avystavkin.githubgists.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.avystavkin.githubgists.screen.main.MainPageActivity;
import ru.avystavkin.githubgists.screen.detail.GistDetailActivity;

@Singleton
@Component(modules = {DataModule.class})
public interface AppComponent {

    void injectGistActivity(MainPageActivity gistsActivity);

    void injectGistDetailActivity(GistDetailActivity gistDetailActivity);
}
