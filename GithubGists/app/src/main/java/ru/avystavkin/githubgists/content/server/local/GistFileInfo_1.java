package ru.avystavkin.githubgists.content.server.local;


import com.google.gson.annotations.SerializedName;

public class GistFileInfo_1 {

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
