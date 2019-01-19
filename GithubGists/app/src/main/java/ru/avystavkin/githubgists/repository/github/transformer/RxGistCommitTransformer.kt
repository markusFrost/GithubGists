package ru.avystavkin.githubgists.repository.github.transformer

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import ru.avystavkin.githubgists.models.database.GistCommit
import ru.avystavkin.githubgists.models.server.GistCommitServer

class RxGistCommitTransformer : ObservableTransformer<List<GistCommitServer>, List<GistCommit>> {

    override fun apply(observable: Observable<List<GistCommitServer>>): ObservableSource<List<GistCommit>> {
        return observable
                .flatMap { x -> Observable.fromIterable(x) }
                .map { x -> TransformerHelper.convertGistCommitsHistory(x) }
                .toList()
                .flatMapObservable { x -> Observable.fromArray(x) }
    }
}
