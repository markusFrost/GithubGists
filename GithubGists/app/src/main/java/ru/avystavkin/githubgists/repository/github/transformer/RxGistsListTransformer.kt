package ru.avystavkin.githubgists.repository.github.transformer

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import ru.avystavkin.githubgists.models.database.Gist
import ru.avystavkin.githubgists.models.server.GistServer

class RxGistsListTransformer : ObservableTransformer<List<GistServer>, List<Gist>> {

    override fun apply(observable: Observable<List<GistServer>>): ObservableSource<List<Gist>> {
        return observable
                .flatMap { x -> Observable.fromIterable(x) }
                .map(TransformerHelper::convertGistServer)
                .toList()
                .flatMapObservable { x -> Observable.fromArray(x) }
    }
}
