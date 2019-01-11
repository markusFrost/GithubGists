package ru.avystavkin.githubgists.content;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private String mId;

    @SerializedName("login")
    private String mLogin;

    @SerializedName("avatar_url")
    private String mAvatarUrl;

    private int mGistsCount;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        this.mLogin = login;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.mAvatarUrl = avatarUrl;
    }

    public int getGistsCount() {
        return mGistsCount;
    }

    public void setGistsCount(int gistsCount) {
        this.mGistsCount = gistsCount;
    }
}
