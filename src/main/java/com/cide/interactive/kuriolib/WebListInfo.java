package com.cide.interactive.kuriolib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by lionel on 12/01/15.
 */
public class WebListInfo {

    private String mUrl = "";
    private boolean mEnabled = true;
    private boolean mBookmarked = false;
    private boolean mIsWhiteList = false;
    private int mIdBookmarkInBrowser = 0;

    private boolean mIsNew = false;
    private int mChildId;

    public int getChildId() {
        return mChildId;
    }

    public void setChildId(int childId) {
        mChildId = childId;
    }

    public int getIdBookmarkInBrowser() {
        return mIdBookmarkInBrowser;
    }

    public void setIdBookmarkInBrowser(int mIdBookmarkInBrowser) {
        this.mIdBookmarkInBrowser = mIdBookmarkInBrowser;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public boolean isEnabled() {
        return mEnabled;
    }

    public void setEnabled(boolean mEnabled) {
        this.mEnabled = mEnabled;
    }

    public boolean isBookmarked() {
        return mBookmarked;
    }

    public void setBookmarked(boolean mBookmarked) {
        this.mBookmarked = mBookmarked;
    }

    public boolean isWhiteList() {
        return mIsWhiteList;
    }

    public void setWhiteList(boolean mIsWhiteList) {
        this.mIsWhiteList = mIsWhiteList;
    }

    public boolean isNew() {
        return mIsNew;
    }

    public void setIsNew(boolean isNew) {
        this.mIsNew = isNew;
    }

    public String toJson() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

}
