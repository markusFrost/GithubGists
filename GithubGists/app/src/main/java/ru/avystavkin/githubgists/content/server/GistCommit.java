package ru.avystavkin.githubgists.content.server;

import ru.avystavkin.githubgists.content.server.local.GistCommitsHistory_1;
import ru.avystavkin.githubgists.content.server.local.User_1;

public class GistCommit {
    private User_1 mUser;

    private String mVersion;

    private String mCommitDateTime;

    GistCommitsHistory_1 mCommitsHistory;

    private String mUrl;

    public User_1 getUser() {
        return mUser;
    }

    public void setUser(User_1 user) {
        this.mUser = user;
    }

    public String getVersion() {
        return mVersion;
    }

    public String getCommitDateTime() {
        return mCommitDateTime;
    }

    public GistCommitsHistory_1 getCommitsHistory() {
        return mCommitsHistory;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setVersion(String version) {
        this.mVersion = version;
    }

    public void setCommitDateTime(String commitDateTime) {
        this.mCommitDateTime = commitDateTime;
    }

    public void setCommitsHistory(GistCommitsHistory_1 commitsHistory) {
        this.mCommitsHistory = commitsHistory;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }
}

