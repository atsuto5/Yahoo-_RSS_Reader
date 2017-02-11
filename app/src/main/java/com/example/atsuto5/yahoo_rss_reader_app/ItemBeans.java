package com.example.atsuto5.yahoo_rss_reader_app;

/**
 * Created by Atsuto5 on 2017/02/11.
 */
public class ItemBeans {
    private String mTitle;
    private String mSummary;


    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setSummary(String summary) {
        this.mSummary = summary;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSummary() {
        return this.mSummary;
        }

}
