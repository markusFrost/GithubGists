package ru.avystavkin.githubgists.content.server.local;

public class GistCommit {
    private User mUser;

    private String mVersion;

    private String mCommitDateTime;

    GistCommitsHistory mCommitsHistory;

    private String mUrl;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        this.mUser = user;
    }

    public String getVersion() {
        return mVersion;
    }

    public String getCommitDateTime() {
        return mCommitDateTime;
    }

    public GistCommitsHistory getCommitsHistory() {
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

    public void setCommitsHistory(GistCommitsHistory commitsHistory) {
        this.mCommitsHistory = commitsHistory;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }
}
