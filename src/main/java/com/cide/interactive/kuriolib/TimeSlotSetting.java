package com.cide.interactive.kuriolib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Created by lionel on 05/12/14.
 */
public class TimeSlotSetting {

    private int mDay;
    private int mChildId;
    private int mSessionTime;
    private int mPlayTime;
    private int mRestTime;
    private ArrayList<String[]> mTimeSlot = new ArrayList<>();
    private boolean mException;
    private boolean mEnabled;
    private boolean mIsEducational;


    public TimeSlotSetting() {

    }

    public TimeSlotSetting(TimeSlotSetting ts) {
        mDay = ts.getDay();
        mChildId = ts.getChildId();
        mSessionTime = ts.getSessionTime();
        mPlayTime = ts.getPlayTime();
        mRestTime = ts.getRestTime();
        mTimeSlot = ts.getTimeSlot();
        mEnabled = ts.isEnabled();
        mIsEducational = ts.isEducational();
    }

    public TimeSlotSetting(int day, int childId, int sessionTime, int playTime, int restTime,
                           ArrayList<String[]> timeSlot, boolean exception, boolean enabled, boolean isEducational) {
        mDay = day;
        mChildId = childId;
        mSessionTime = sessionTime;
        mPlayTime = playTime;
        mRestTime = restTime;
        mTimeSlot = timeSlot;
        mEnabled = enabled;
        mException = exception;
        mIsEducational = isEducational;

    }

    public TimeSlotSetting(int day, int childId, int sessionTime, int playTime, int restTime,
                           String timeSlot, boolean exception, boolean enabled, boolean isEducational) {
        this(day, childId, sessionTime, playTime, restTime, getTimeSlotFromString(timeSlot), exception, enabled, isEducational);
    }

    private static ArrayList<String[]> getTimeSlotFromString(String timeSlot) {
        ArrayList<String[]> allTimeSlot = new ArrayList<>();

        String[] split = timeSlot.split("@");
        for (String value : split) {
            String[] splitTimeSlot = value.split("-");
            allTimeSlot.add(splitTimeSlot);
        }
        return allTimeSlot;
    }

    public int getDay() {
        return mDay;
    }

    public void setDay(int day) {
        mDay = day;
    }

    public int getChildId() {
        return mChildId;
    }

    public void setChildId(int childId) {
        mChildId = childId;
    }

    public int getSessionTime() {
        return mSessionTime;
    }

    public void setSessionTime(int sessionTime) {
        mSessionTime = sessionTime;
    }

    public int getPlayTime() {
        return mPlayTime;
    }

    public void setPlayTime(int playTime) {
        mPlayTime = playTime;
    }

    public int getRestTime() {
        return mRestTime;
    }

    public void setRestTime(int restTime) {
        mRestTime = restTime;
    }

    public void setEducationalEnabed(boolean enabled) {
        mIsEducational = enabled;
    }

    public boolean isEducational() {
        return mIsEducational;
    }

    /**
     * 0 for start time
     * 1 for end time
     *
     * @return
     */
    public ArrayList<String[]> getTimeSlot() {
        return mTimeSlot;
    }

    public void setTimeSlot(ArrayList<String[]> timeSlot) {
        mTimeSlot = timeSlot;
    }

//    public boolean isException() {
//        return mException;
//    }

//    public void setException(boolean exception) {
//        mException = exception;
//    }

    public boolean isEnabled() {
        return mEnabled;
    }

    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
    }

    public String getTimeSlotAsString() {

        String timeSlotAsString = "";
        for (int i = 0; i < mTimeSlot.size(); i++) {
            String[] timeSlot = mTimeSlot.get(i);

            timeSlotAsString = timeSlotAsString.concat(timeSlot[0] + '-' + timeSlot[1]);

            if (i < (mTimeSlot.size() - 1)) {
                timeSlotAsString = timeSlotAsString.concat("@");
            }
        }
        return timeSlotAsString;
    }

    public void addTimeSlot(String timeSlot) {
        String[] split = timeSlot.split("@");

        for (String value : split) {
            String[] valueSplit = value.split("-");
            mTimeSlot.add(valueSplit);
        }
    }

    public void setTimeSlot(String timeSlot) {
        String[] split = timeSlot.split("@");
        mTimeSlot.clear();
        for (String value : split) {
            String[] valueSplit = value.split("-");
            mTimeSlot.add(valueSplit);
        }
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
