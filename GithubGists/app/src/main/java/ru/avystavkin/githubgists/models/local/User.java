package ru.avystavkin.githubgists.models.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    @ColumnInfo(name = "user_id")
    private long mId;

    @ColumnInfo(name = "user_name")
    private String mName;

    @ColumnInfo(name = "user_url")
    private String mUrl;

    @ColumnInfo(name = "user_gists_count")
    private int mGistsCount;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
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
