package ru.avystavkin.githubgists.content.server.local;

public class User {

    private String mId;

    private String mName;

    private String mUrl;

    private int mGistsCount;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public int getGistsCount() {
        return mGistsCount;
    }

    public void setGistsCount(int gistsCount) {
        mGistsCount = gistsCount;
    }
}
