package ru.avystavkin.githubgists.content.server.local;

import java.util.List;

import ru.avystavkin.githubgists.content.server.GistFileServer;

public class Gist {

    private String mId;

    private String mUrl;

    private String mName;

    private User mUser;

    private List<GistFileServer> mListFiles;

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

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public List<GistFileServer> getListFiles() {
        return mListFiles;
    }

    public void setListFiles(List<GistFileServer> listFiles) {
        mListFiles = listFiles;
    }
}
