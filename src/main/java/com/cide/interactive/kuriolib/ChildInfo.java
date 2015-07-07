package com.cide.interactive.kuriolib;

import android.content.Context;
import android.os.RemoteException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ChildInfo extends Child {

    protected static final String TAG = "ChildInfoDataAccess";
    protected Context mContext;
    protected boolean mProfileChanged;

    /**
     * create a new child info
     * this constructor DO NOT save info in DB
     * u need to call createChildInDB(int userId)
     */
    public ChildInfo(Context context) {
        mContext = context;
        mBirth = null;
    }

    public ChildInfo(Context context, int userId) {
        mContext = context;
        mUserId = userId;
        if (!context.getPackageName().equals(Kurio.PACKAGE_KURIO_SERVICE)) {
            String childAsJson = "";
            try {
                childAsJson = RemoteApi.getChildAsJson(mUserId);
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
            }
            initChildFromJson(childAsJson);
        }
    }

    public static boolean isChild(int userId) {
        try {
            return RemoteApi.getAllUserId().contains(userId);
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage(), e);
            return false;
        }
    }

    //import
    public void importAppsFromOtherChild(int otherChildId) {
        try {
            RemoteApi.importAppsFromOtherChild(mUserId, otherChildId);
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public boolean isProfileChanged() {
        return mProfileChanged;
    }


    public Child getChild() {
        Child child = new Child();
        child.setUserId(mUserId);
        child.setAnalyticsUID(getAnalyticsUID());
        child.setName(mName);
        child.setActivateAdsFilter(mActivateAdsFilter);
        child.setAllowUsbConnection(mAllowUsbConnection);
        child.setAutoAuthorizeApplication(mAutoAuthorizeApplication);
        child.setBirth(mBirth);
        child.setTheme(mTheme);
        child.setWebAccessEnabled(mIsWebAccessOn);
        child.setSerial(Utils.getSerialId());
        child.setBoy(mSexBoy);
        child.setWebListEnabled(mIsWebListOn);
        child.setWebFilterEnabled(mIsWebFilterOn);
        child.setIsActiveUser(true);
        child.setFilterInfo(mFilterInfo);
        child.setDemoMode(mDemoMode);
        return child;
    }

    public void initChildFromJson(String jsonData) {
        Gson gson = new GsonBuilder().create();
        Child child = gson.fromJson(jsonData, Child.class);
        mDemoMode = child.isDemoMode();
        mName = child.getName();
        mTheme = child.getTheme();
        mActivateAdsFilter = child.isActivateAdsFilter();
        mAllowUsbConnection = child.isAllowUsbConnection();
        mAutoAuthorizeApplication = child.isAutoAuthorizeApplication();
        mBirth = child.getBirth();
        mIsWebAccessOn = child.isWebAccessOn();
        mSexBoy = child.isBoy();
        mIsWebListOn = child.isWebListOn();
        mIsWebFilterOn = child.isWebFilterOn();
        mAnalyticsUID = child.getAnalyticsUID();
        mFilterInfo = child.getFilterInfo();
    }

    public String toJson() {

        Gson gson = new GsonBuilder().create();
        String childInfoJson = gson.toJson(getChild());
        return childInfoJson;
    }

    //need to be sure the api is connected before call save
    public void save() {
        try {
            RemoteApi.saveChildInfoFromJson(toJson(), mUserId);
        } catch (RemoteException e) {
            Log.e(TAG, "save", e);
        }

    }
}
