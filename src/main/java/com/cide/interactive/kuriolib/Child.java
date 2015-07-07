package com.cide.interactive.kuriolib;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by lionel on 16/09/14.
 */
public class Child {

    protected static final String TAG = "Child";

    private String Serial;
    protected int mUserId = -1;
    protected String mTheme = null;
    protected String mAnalyticsUID;
    protected String mBirth;
    protected String mName;
    protected boolean mSexBoy;
    protected boolean mActivateAdsFilter;
    protected boolean mAllowUsbConnection;
    protected boolean mAutoAuthorizeApplication;
    protected boolean mIsWebAccessOn;
    protected boolean mIsActiveUser = true;
    protected boolean mDemoMode = false;
    protected String mFilterInfo;
    protected boolean mIsWebListOn;
    protected boolean mIsWebFilterOn;
    protected boolean mLockedInterface;

    public void setFilterInfo(String filterInfo) {
        mFilterInfo = filterInfo;
    }

    public String getFilterInfo() {
        return mFilterInfo;
    }

    public void setDemoMode(boolean demoMode) {
        mDemoMode = demoMode;
    }

    public boolean isDemoMode() {
        return mDemoMode;
    }

    public void setTheme(String theme) {
        mTheme = theme;
    }

    public String getTheme() {
        return mTheme;
    }

    public void setIsActiveUser(boolean isActive) {
        mIsActiveUser = isActive;
    }

    public boolean ismIsActiveUser() {
        return mIsActiveUser;
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String serial) {
        Serial = serial;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getAnalyticsUID() {
        //for old version if the value doesnt exits, we create it the save it to db
        if (mAnalyticsUID == null) {
            mAnalyticsUID = UUID.randomUUID().toString();
        }
        return mAnalyticsUID;
    }

    public void setAnalyticsUID(String mAnalyticsUID) {
        this.mAnalyticsUID = mAnalyticsUID;
    }

    public String getBirth() {
        return mBirth;
    }

    public void setBirth(String mBirth) {
        this.mBirth = mBirth;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public boolean isBoy() {
        return mSexBoy;
    }

    public void setBoy(boolean mSexBoy) {
        this.mSexBoy = mSexBoy;
    }

    public boolean isActivateAdsFilter() {
        return mActivateAdsFilter;
    }

    public void setActivateAdsFilter(boolean mActivateAdsFilter) {
        this.mActivateAdsFilter = mActivateAdsFilter;
    }

    public boolean isAllowUsbConnection() {
        return mAllowUsbConnection;
    }

    public void setAllowUsbConnection(boolean mAllowUsbConnection) {
        this.mAllowUsbConnection = mAllowUsbConnection;
    }

    public boolean isAutoAuthorizeApplication() {
        return mAutoAuthorizeApplication;
    }

    public void setAutoAuthorizeApplication(boolean mAutoAuthorizeApplication) {
        this.mAutoAuthorizeApplication = mAutoAuthorizeApplication;
    }

    public boolean isWebAccessOn() {
        return mIsWebAccessOn;
    }

    public void setWebAccessEnabled(boolean enabled) {
        mIsWebAccessOn = enabled;
    }


    public boolean isWebListOn() {
        return mIsWebListOn;
    }

    public void setWebListEnabled(boolean enabled) {
        mIsWebListOn = enabled;
    }

    public boolean isWebFilterOn() {
        return mIsWebFilterOn;
    }

    public void setWebFilterEnabled(boolean enabled) {
        mIsWebFilterOn = enabled;
    }

    public boolean isLockedInterface() {
        return mLockedInterface;
    }

    public void setLockedInterface(boolean b) {
        mLockedInterface = b;
    }

    public int getAge() {

        Calendar cal = Utils.getCalendar(mBirth, Kurio.DATE_FORMAT_FOR_DB);
        int y = 0, m, d;
        try {
            Calendar now = Calendar.getInstance();
            d = now.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH);
            m = now.get(Calendar.MONTH) - cal.get(Calendar.MONTH);
            y = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
            if (d < 0) {
                --m;
            }
            if (m < 0) {
                --y;
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
        return y;
    }
}
