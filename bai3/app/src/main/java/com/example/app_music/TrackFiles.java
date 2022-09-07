package com.example.app_music;

public class TrackFiles {
    private String title;
    private String singer;
    private int thumnail;

    public TrackFiles(String title, String singer, int thumnail) {
        this.title = title;
        this.singer = singer;
        this.thumnail = thumnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getThumnail() {
        return thumnail;
    }

    public void setThumnail(int thumnail) {
        this.thumnail = thumnail;
    }
}
