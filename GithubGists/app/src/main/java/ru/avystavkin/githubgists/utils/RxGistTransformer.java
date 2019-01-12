package ru.avystavkin.githubgists.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import ru.avystavkin.githubgists.content.server.GistFileServer;
import ru.avystavkin.githubgists.content.server.GistServer;
import ru.avystavkin.githubgists.content.server.UserServer;
import ru.avystavkin.githubgists.content.server.local.Gist;
import ru.avystavkin.githubgists.content.server.local.User;

public class RxGistTransformer implements ObservableTransformer<List<GistServer>, List<Gist>> {

    @Override
    public ObservableSource<List<Gist>> apply(Observable<List<GistServer>> observable) {
        return observable
                .flatMap( Observable::fromIterable)
                .map(g ->{
                    Gist gist = new Gist();
                    User user = new User();

                    UserServer userServer = g.getUser();
                    if (userServer != null) {
                        user.setUrl(userServer.getAvatarUrl());
                        user.setId(userServer.getId());
                        user.setName(userServer.getLogin());
                    }
                    gist.setId(g.getId());
                    gist.setListFiles(g.getGistFiles());

                    List<GistFileServer> list = new ArrayList<GistFileServer>();
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
