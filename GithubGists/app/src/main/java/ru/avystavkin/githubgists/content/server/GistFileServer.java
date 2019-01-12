package ru.avystavkin.githubgists.content.server;

import com.google.gson.annotations.SerializedName;

public class GistFileServer {

    @SerializedName("filename")
    private String mFileName;

    @SerializedName("content")
    private String mContent;

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        this.mFileName = fileName;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }
}
