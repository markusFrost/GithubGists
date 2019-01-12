package ru.avystavkin.githubgists.models.server;

import com.google.gson.annotations.SerializedName;

public class GistCommitServer {
    @SerializedName("user")
    private UserServer mUser;

    @SerializedName("version")
    private String mVersion;

    @SerializedName("committed_at")
    private String mCommittedAt;

    @SerializedName("change_status")
    GistCommitsHistoryServer mStatus;

    @SerializedName("url")
    private String mUrl;

    public UserServer getUser() {
        return mUser;
    }

    public void setUser(UserServer user) {
        mUser = user;
    }

    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        mVersion = version;
    }

    public String getCommittedAt() {
        return mCommittedAt;
    }

    public void setCommittedAt(String committedAt) {
        mCommittedAt = committedAt;
    }

    public GistCommitsHistoryServer getStatus() {
        return mStatus;
    }

    public void setStatus(GistCommitsHistoryServer status) {
        mStatus = status;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
