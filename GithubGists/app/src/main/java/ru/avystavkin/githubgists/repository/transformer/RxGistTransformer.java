package ru.avystavkin.githubgists.repository.transformer;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.server.GistServer;

public class RxGistTransformer implements ObservableTransformer<GistServer, Gist> {
    @Override
    public ObservableSource<Gist> apply(Observable<GistServer> observable) {
        return observable
                .map(TransformerHelper::convertGistServer)
                .flatMap(Observable::just);
    }
}
