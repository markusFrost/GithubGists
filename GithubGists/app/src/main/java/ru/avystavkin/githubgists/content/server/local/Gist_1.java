package ru.avystavkin.githubgists.content.server.local;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.avystavkin.githubgists.content.server.GistFileInfo;
import ru.avystavkin.githubgists.content.server.User;

public class Gist_1 {

    @SerializedName("id")
    private String mId;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("owner")
    private User mUser;

    @SerializedName("files")
    private Map<String, GistFileInfo> mFiles;

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

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        this.mUser = user;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public Map<String, GistFileInfo> getRawFiles() {
        return mFiles;
    }

    public List<GistFileInfo> getGistFiles() {
        List<GistFileInfo> list = new ArrayList<GistFileInfo>();
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
