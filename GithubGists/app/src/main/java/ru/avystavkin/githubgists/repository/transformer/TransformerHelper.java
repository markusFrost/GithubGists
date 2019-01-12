package ru.avystavkin.githubgists.repository.transformer;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.GistCommit;
import ru.avystavkin.githubgists.models.local.GistCommitsHistory;
import ru.avystavkin.githubgists.models.local.GistFileInfo;
import ru.avystavkin.githubgists.models.local.User;
import ru.avystavkin.githubgists.models.server.GistCommitServer;
import ru.avystavkin.githubgists.models.server.GistCommitsHistoryServer;
import ru.avystavkin.githubgists.models.server.GistServer;
import ru.avystavkin.githubgists.models.server.UserServer;

public class TransformerHelper {

    private TransformerHelper(){}

    public static GistCommit convertGistCommitsHistory(GistCommitServer commitServer) {
        GistCommit commit = new GistCommit();
        GistCommitsHistory history = new GistCommitsHistory();

        User user = convertUser(commitServer.getUser());

        GistCommitsHistoryServer historyServer = commitServer.getStatus();
        if (historyServer != null) {
            history.setAdditions(historyServer.getAdditions());
            history.setDeletions(historyServer.getDeletions());
            history.setTotal(historyServer.getTotal());
        }
        commit.setUser(user);
        commit.setUrl(commitServer.getUrl());
        commit.setCommitDateTime(commitServer.getCommittedAt());
        commit.setCommitsHistory(history);
        commit.setVersion(commitServer.getVersion());

        return commit;
    }

    public static User convertUser(UserServer userServer) {
        User user = new User();
        if (userServer != null) {
            user.setUrl(userServer.getAvatarUrl());
            user.setId(userServer.getId());
            user.setName(userServer.getLogin());
        }
        return user;
    }

    public static Gist convertGistServer(GistServer gistServer) {
        Gist gist = new Gist();

        User user = convertUser(gistServer.getUser());

        gist.setId(gistServer.getId());

        List<GistFileInfo> list = new ArrayList<GistFileInfo>();
        for(String key : gistServer.getFiles().keySet()) {
            list.add(gistServer.getFiles().get(key));
        }
        gist.setListFiles(list);
        gist.setUrl(gistServer.getUrl());
        gist.setName(TextUtils.isEmpty(gistServer.getDescription()) ? "gist:" + gistServer.getId() : gistServer.getDescription());
        gist.setUser(user);

        return gist;
    }
}
