package ru.avystavkin.githubgists.models.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_int_id")
    private long mIntId;

    @ColumnInfo(name = "user_string_id")
    private String mId;

    @ColumnInfo(name = "user_name")
    private String mName;

    @ColumnInfo(name = "user_url")
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

    public long getIntId() {
        return mIntId;
    }

    public void setIntId(long intId) {
        mIntId = intId;
    }
}
