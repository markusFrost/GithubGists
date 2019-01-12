package ru.avystavkin.githubgists.models.local;

public class GistFileInfo {
    private String mFileName;

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
