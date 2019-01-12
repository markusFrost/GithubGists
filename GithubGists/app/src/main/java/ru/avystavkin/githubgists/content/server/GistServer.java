package ru.avystavkin.githubgists.content.server;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private Map<String, GistFileServer> mFiles;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public UserServer getUser() {
        return mUser;
    }

    public void setUser(UserServer user) {
        this.mUser = user;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public Map<String, GistFileServer> getRawFiles() {
        return mFiles;
    }

    public List<GistFileServer> getGistFiles() {
        List<GistFileServer> list = new ArrayList<GistFileServer>();
        for(String key : mFiles.keySet()) {
            list.add(mFiles.get(key));
        }
        return list;
    }

    public String getName() {
        if (TextUtils.isEmpty(mDescription)) {
            return "gist:" + mId;
        }
        else {
            return mDescription;
        }
    }
}
