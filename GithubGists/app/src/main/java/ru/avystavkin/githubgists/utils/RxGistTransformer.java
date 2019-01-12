package ru.avystavkin.githubgists.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import ru.avystavkin.githubgists.content.server.GistFileInfo;
import ru.avystavkin.githubgists.content.server.Gist;
import ru.avystavkin.githubgists.content.server.User;
import ru.avystavkin.githubgists.content.server.local.Gist_1;
import ru.avystavkin.githubgists.content.server.local.User_1;

public class RxGistTransformer implements ObservableTransformer<List<Gist>, List<Gist_1>> {

    @Override
    public ObservableSource<List<Gist_1>> apply(Observable<List<Gist>> observable) {
        return observable
                .flatMap( Observable::fromIterable)
                .map(g ->{
                    Gist_1 gist = new Gist_1();
                    User_1 user = new User_1();

                    User userServer = g.getUser();
                    if (userServer != null) {
                        user.setUrl(userServer.getAvatarUrl());
                        user.setId(userServer.getId());
                        user.setName(userServer.getLogin());
                    }
                    gist.setId(g.getId());
                    gist.setListFiles(g.getGistFiles());

                    List<GistFileInfo> list = new ArrayList<GistFileInfo>();
                    for(String key : g.getRawFiles().keySet()) {
                        list.add(g.getRawFiles().get(key));
                    }
                    gist.setListFiles(list);
                    gist.setUrl(g.getUrl());
                    gist.setName(TextUtils.isEmpty(g.getDescription()) ? "gist:" + g.getId() : g.getDescription());
                    gist.setUser(user);

                    return gist;
                })
                .toList()
                .flatMapObservable(Observable::fromArray);
    }
}
