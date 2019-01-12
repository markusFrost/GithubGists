package ru.avystavkin.githubgists.models.server;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

import ru.avystavkin.githubgists.models.local.GistFileInfo;

public class GistServer {

    @SerializedName("id")
    private String mId;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("owner")
    private UserServer mUser;

    @SerializedName("files")
    private Map<String, GistFileInfo> mFiles;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public UserServer getUser() {
        return mUser;
    }

    public void setUser(UserServer user) {
        mUser = user;
    }

    public Map<String, GistFileInfo> getFiles() {
        return mFiles;
    }

    public void setFiles(Map<String, GistFileInfo> files) {
        mFiles = files;
    }
}
