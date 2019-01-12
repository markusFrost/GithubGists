package ru.avystavkin.githubgists.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import ru.avystavkin.githubgists.models.local.GistFileInfo;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.User;
import ru.avystavkin.githubgists.models.server.GistServer;
import ru.avystavkin.githubgists.models.server.UserServer;

public class RxGistTransformer implements ObservableTransformer<List<GistServer>, List<Gist>> {

    @Override
    public ObservableSource<List<Gist>> apply(Observable<List<GistServer>> observable) {
        return observable
                .flatMap( Observable::fromIterable)
                .map(g -> {
                    Gist gist = new Gist();
                    User user = new User();

                    UserServer userServer = g.getUser();
                    if (userServer != null) {
                        user.setUrl(userServer.getAvatarUrl());
                        user.setId(userServer.getId());
                        user.setName(userServer.getLogin());
                    }
                    gist.setId(g.getId());

                    List<GistFileInfo> list = new ArrayList<GistFileInfo>();
                    for(String key : g.getFiles().keySet()) {
                        list.add(g.getFiles().get(key));
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
