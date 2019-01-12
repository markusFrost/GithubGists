package ru.avystavkin.githubgists.repository.transformer;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.server.GistServer;

public class RxGistsListTransformer implements ObservableTransformer<List<GistServer>, List<Gist>> {

    @Override
    public ObservableSource<List<Gist>> apply(Observable<List<GistServer>> observable) {
        return observable
                .flatMap( Observable::fromIterable)
                .map(TransformerHelper::convertGistServer)
                .toList()
                .flatMapObservable(Observable::fromArray);
    }
}
