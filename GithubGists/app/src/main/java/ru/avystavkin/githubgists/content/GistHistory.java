package ru.avystavkin.githubgists.content;

import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GistHistory {

    @PrimaryKey
    public long id;
    
    @SerializedName("user")
    private User mUser;

    @SerializedName("version")
    private String mVersion;

    @SerializedName("committed_at")
    private String mCommittedAt;

    @SerializedName("change_status")
    GistHistoryChangeStatus mStatus;

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

    public GistHistoryChangeStatus getChangeStatus() {
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

    public void setChangeStatus(GistHistoryChangeStatus changeStatusObject) {
        this.mStatus = changeStatusObject;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

