package ru.avystavkin.githubgists.content.server;

import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;

public class GistHistoryServer {

    @SerializedName("user")
    private UserServer mUser;

    @SerializedName("version")
    private String mVersion;

    @SerializedName("committed_at")
    private String mCommittedAt;

    @SerializedName("change_status")
    GistHistoryChangeStatusServer mStatus;

    @SerializedName("url")
    private String url;

    public UserServer getUser() {
        return mUser;
    }

    public void setUser(UserServer user) {
        this.mUser = user;
    }

    public String getVersion() {
        return mVersion;
    }

    public String getCommittedAt() {
        return mCommittedAt;
    }

    public GistHistoryChangeStatusServer getChangeStatus() {
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

    public void setChangeStatus(GistHistoryChangeStatusServer changeStatusObject) {
        this.mStatus = changeStatusObject;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

