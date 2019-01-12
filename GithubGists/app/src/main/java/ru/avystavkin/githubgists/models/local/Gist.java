package ru.avystavkin.githubgists.models.local;

import java.util.List;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Gist {

    @PrimaryKey
    public long id;

    private String mId;

    private String mUrl;

    private String mName;

    @Ignore
    private User mUser;

    @Ignore
    private List<GistFileInfo> mListFiles;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

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

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<GistFileInfo> getListFiles() {
        return mListFiles;
    }

    public void setListFiles(List<GistFileInfo> listFiles) {
        mListFiles = listFiles;
    }
}
