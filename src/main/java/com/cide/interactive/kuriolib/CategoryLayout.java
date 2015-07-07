package com.cide.interactive.kuriolib;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by lionel on 23/12/14.
 */
public class CategoryLayout {
    private final static String TAG = "CategoryLayout";

    private ArrayList<AppInfo> mAppsList;

    public static class AppInfo {
        public String mPackageName;
        public String mActivityName;
        public String mCategoryId;
        public String mFolderId;
        public int mX;
        public int mY;

        public AppInfo(String packageName, String activityName, int x, int y
                , String categoryId, String folderId) {
            mPackageName = packageName;
            mActivityName = activityName;
            mX = x;
            mY = y;
            mCategoryId = categoryId;
            mFolderId = folderId;
        }

        public AppInfo(String packageName, String activityName, int x, int y) {
            mPackageName = packageName;
            mActivityName = activityName;
            mX = x;
            mY = y;
            mCategoryId = null;
            mFolderId = null;
        }
    }

    public CategoryLayout() {
        mAppsList = new ArrayList<>(0);
    }

    public String toJson() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(mAppsList);
    }

    public ArrayList<AppInfo> initFromJson(String json) {
        mAppsList = new GsonBuilder().create().fromJson(json, new TypeToken<ArrayList<AppInfo>>() {
        }.getType());
        return mAppsList;
    }

    public void addAppInfo(String packageName, String activityName, int x, int y
            , String categoryId, String folderId) {
        mAppsList.add(new AppInfo(packageName,activityName,x,y,categoryId,folderId));
    }

    public static ArrayList<AppInfo> getLicensedAppInfoList(Context context) {
        ArrayList<AppInfo> appsList = new ArrayList<>(0);
        ArrayList<String> dataFromFile = Utils.getLicencedLayout(context);

        for (String line : dataFromFile) {
            String[] splitLine = line.split("@");
            AppInfo appInfo = new AppInfo(splitLine[0], splitLine[1], Integer.valueOf(splitLine[2]),
                    Integer.valueOf(splitLine[3]));
            appsList.add(appInfo);
        }
        return appsList;
    }
}
