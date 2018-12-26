package ru.avystavkin.githubgists.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.avystavkin.githubgists.screen.gists.main.GistsActivity;
import ru.avystavkin.githubgists.screen.gists.detail.GistDetailActivity;

@Singleton
@Component(modules = {DataModule.class})
public interface AppComponent {

    void injectGistActivity(GistsActivity gistsActivity);

    void injectGistDetailActivity(GistDetailActivity gistDetailActivity);
}
