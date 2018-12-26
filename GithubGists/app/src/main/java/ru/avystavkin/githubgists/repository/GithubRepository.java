package ru.avystavkin.githubgists.repository;

import android.support.annotation.NonNull;

import java.util.List;

import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.GistHistory;
import rx.Observable;

public interface GithubRepository {

    @NonNull
    Observable<List<Gist>> gists();

    @NonNull
    Observable<Gist> gist_detail(String id);

    @NonNull
    Observable<List<GistHistory>> get_gist_commits(String id);

}
