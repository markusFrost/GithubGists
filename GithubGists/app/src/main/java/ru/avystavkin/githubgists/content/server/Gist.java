package ru.avystavkin.githubgists.content.server;

import java.util.List;

import ru.avystavkin.githubgists.content.server.local.User_1;

public class Gist {
    private String mId;

    private String mUrl;

    private String mName;

    private User_1 mUser;

    private List<GistFileInfo> mListFiles;

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

    public User_1 getUser() {
        return mUser;
    }

    public void setUser(User_1 user) {
        mUser = user;
    }

    public List<GistFileInfo> getListFiles() {
        return mListFiles;
    }

    public void setListFiles(List<GistFileInfo> listFiles) {
        mListFiles = listFiles;
    }
}
