package ru.avystavkin.githubgists.models.local;

public class GistCommitsHistory {
    private int mTotal;

    private int mAdditions;

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
