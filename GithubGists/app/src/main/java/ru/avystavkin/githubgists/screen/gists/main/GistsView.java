package ru.avystavkin.githubgists.screen.gists.main;

import android.support.annotation.NonNull;

import java.util.List;

import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.screen.general.LoadingView;

public interface GistsView extends LoadingView {

    void showGists(@NonNull List<Gist> gists);

    void showError();
}
