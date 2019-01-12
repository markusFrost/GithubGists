package ru.avystavkin.githubgists.models.server;

import com.google.gson.annotations.SerializedName;

public class GistCommitsHistoryServer {
    @SerializedName("total")
    private int mTotal;

    @SerializedName("additions")
    private int mAdditions;

    @SerializedName("deletions")
    private int mDeletions;

    public int getTotal() {
        return mTotal;
    }

    public int getAdditions() {
        return mAdditions;
    }

    public int getDeletions() {
        return mDeletions;
    }

    public void setTotal(int total) {
        this.mTotal = total;
    }

    public void setAdditions(int additions) {
        this.mAdditions = additions;
    }

    public void setDeletions(int deletions) {
        this.mDeletions = deletions;
    }
}
