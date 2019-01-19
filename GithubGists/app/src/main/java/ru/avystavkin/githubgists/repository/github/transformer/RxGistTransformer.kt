package ru.avystavkin.githubgists.repository.github.transformer


import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import ru.avystavkin.githubgists.models.database.Gist
import ru.avystavkin.githubgists.models.server.GistServer

class RxGistTransformer : ObservableTransformer<GistServer, Gist> {
    override fun apply(observable: Observable<GistServer>): ObservableSource<Gist> {
        return observable
                .map { x -> TransformerHelper.convertGistServer(x) }
                .flatMap { x -> Observable.just(x) }
    }
}
