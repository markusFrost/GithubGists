package ru.avystavkin.githubgists.repository.transformer;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import ru.avystavkin.githubgists.models.local.GistCommit;
import ru.avystavkin.githubgists.models.server.GistCommitServer;

public class RxGistCommitTransformer implements ObservableTransformer<List<GistCommitServer>, List<GistCommit>> {


    @Override
    public ObservableSource<List<GistCommit>> apply(Observable<List<GistCommitServer>> observable) {
        return observable
                .flatMap( Observable::fromIterable)
                .map(TransformerHelper::convertGistCommitsHistory)
                .toList()
                .flatMapObservable(Observable::fromArray);
    }
}
