package ru.avystavkin.githubgists.content.server.local;

import com.google.gson.annotations.SerializedName;

import ru.avystavkin.githubgists.content.server.GistCommitsHistory;
import ru.avystavkin.githubgists.content.server.User;

public class GistCommit_1 {
    @SerializedName("user")
    private User mUser;

    @SerializedName("version")
    private String mVersion;

    @SerializedName("committed_at")
    private String mCommittedAt;

    @SerializedName("change_status")
    GistCommitsHistory mStatus;

    @SerializedName("url")
    private String url;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        this.mUser = user;
    }

    public String getVersion() {
        return mVersion;
    }

    public String getCommittedAt() {
        return mCommittedAt;
    }

    public GistCommitsHistory getChangeStatus() {
        return mStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setVersion(String version) {
        this.mVersion = version;
    }

    public void setCommittedAt(String committedAt) {
        this.mCommittedAt = committedAt;
    }

    public void setChangeStatus(GistCommitsHistory changeStatusObject) {
        this.mStatus = changeStatusObject;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
