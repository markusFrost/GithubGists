package ru.avystavkin.githubgists.models.local;

public class GistCommit {
    private User mUser;

    private String mVersion;

    private String mCommitDateTime;

    private GistCommitsHistory mCommitsHistory;

    private String mUrl;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        mVersion = version;
    }

    public String getCommitDateTime() {
        return mCommitDateTime;
    }

    public void setCommitDateTime(String commitDateTime) {
        mCommitDateTime = commitDateTime;
    }

    public GistCommitsHistory getCommitsHistory() {
        return mCommitsHistory;
    }

    public void setCommitsHistory(GistCommitsHistory commitsHistory) {
        mCommitsHistory = commitsHistory;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}

