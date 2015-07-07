package com.cide.interactive.kuriolib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;

import com.cide.interactive.parentalArea.Api.IRemoteApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RemoteApi {
    private static final String TAG = "RemoteApi";

    private static final String KURIO_API_SERVICE_CLASS = ".Api.RemoteApiService";
    private static final String KURIO_PARENTAL_API_SERVICE_PACKAGE = "com.cide.interactive.parentalArea";
    private static final String KURIO_INTENT_ACTION_BIND_API_SERVICE = "com.cide.interactive.intent.action.bindApiService";

    private static ServiceConnection sConnection;
    private static Context sContext;
    private static IRemoteApi sService = null;
    private static RemoteApi sSelf = null;

    private static boolean sConnected = false;

    private static final ArrayList<ServiceConnectedListener> sCallbacks = new ArrayList<>();

    public interface ServiceConnectedListener {
        void onServiceConnected();
    }

    public static synchronized RemoteApi safeRemoteCall(Context context
            , final ServiceConnectedListener listner) {
        if (sSelf == null) {
            sSelf = new RemoteApi(context.getApplicationContext());
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!sConnected) {
                    safelyConnectTheService();
                    registerServiceConnectedListner(new ServiceConnectedListener() {
                        @Override
                        public void onServiceConnected() {
                            if (listner != null) {
                                listner.onServiceConnected();
                            }
                        }
                    });
                } else {
                    if (listner != null) {
                        listner.onServiceConnected();
                    }
                }
            }
        }).start();
        return sSelf;
    }

    public RemoteApi(Context context) {
        sContext = context.getApplicationContext();
    }

    public static void registerServiceConnectedListner(ServiceConnectedListener callback) {

        synchronized (sCallbacks) {
            sCallbacks.add(callback);
        }
    }

    /**
     * Method to connect the Service.
     */
    private static void safelyConnectTheService() {
        if (sService == null) {
            sConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder iService) {
                    sService = IRemoteApi.Stub.asInterface(iService);
                    sConnected = true;
                    synchronized (sCallbacks) {
                        while(!sCallbacks.isEmpty()) {
                            final ServiceConnectedListener sc = sCallbacks.remove(0);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    sc.onServiceConnected();
                                }
                            }, "safeRemoteApi").start();
                        }
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    sService = null;
                    sConnected = false;
                }
            };
            Intent bindIntent = new Intent(KURIO_INTENT_ACTION_BIND_API_SERVICE);
            bindIntent.setClassName(KURIO_PARENTAL_API_SERVICE_PACKAGE, KURIO_PARENTAL_API_SERVICE_PACKAGE + KURIO_API_SERVICE_CLASS);
            sContext.bindService(bindIntent, sConnection, Context.BIND_AUTO_CREATE);
        }
    }

    public static boolean deleteUser(int userId) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            return sService.deleteUser(userId);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static ArrayList<Integer> getAllUserId() throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            return (ArrayList<Integer>) sService.getAllUserId();
        } else {
            throw new RemoteException("Service is not connected");
        }
    }


    public static void authorizeAppsForUsers(ArrayList<ResolveInfo> activityInfoToEnable, List<ResolveInfo> appsToDisable, int userId, boolean forDefaultList) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.authorizeAppsForUsers(activityInfoToEnable, appsToDisable, userId, forDefaultList);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    //app Category

    public static void setCategoryForApp(String packageName, String activityName, String categroyId) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.setCategoryForApp(packageName, activityName, categroyId);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static ArrayList<String[]> getAppCategory() throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            return (ArrayList<String[]>) sService.getAppCategory();
        } else {
            throw new RemoteException("Service is not connected");
        }
    }


    /**
     * @param categoryId
     * @return array of string :
     * 0 for packageName
     * 1 for activityName
     * @throws RemoteException
     */
    public static ArrayList<String[]> getAppForCategory(String categoryId) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            return (ArrayList<String[]>) sService.getAppForCategory(categoryId);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static String getCategoryIdForApp(String packageName, String activityName) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            return sService.getCategoryIdForApp(packageName, activityName);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static HashMap<String, String> getCategories() throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            return (HashMap) sService.getCategories();
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static ArrayList<ResolveInfo> getDefaultAppsToEnable() throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            return (ArrayList) sService.getDefaultAppsToEnable();
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static ArrayList<ResolveInfo> getDefaultAppsToDisable() throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            return (ArrayList) sService.getDefaultAppsToDisable();
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static ArrayList<ResolveInfo> getAppsToCategorize() throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            return (ArrayList) sService.getAppsToCategorize();
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    //PArentPreference
    public static void setParentPreferences(String key, String value) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.setParentPreferences(key, value);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }


    public static String getParentPreferencesForKey(String key) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            return sService.getParentPreferencesForKey(key);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }


    public static void needToReload(String keyToReload, int userId) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.needToReload(keyToReload, userId);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }


    public static void importAppsFromOtherChild(int currentChildId, int otherChildId) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.importAppsFromOtherChild(currentChildId, otherChildId);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static int createChildFromJson(String childInfoAsJson) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            return sService.createChildFromJson(childInfoAsJson);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static String getChildAsJson(int userId) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            return sService.getChildAsJson(userId);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static void saveChildInfoFromJson(String childAsJson, int userId) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.saveChildInfoFromJson(childAsJson, userId);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static ArrayList<TimeSlotSetting> getTimeSlotForUser(int userId) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            ArrayList<TimeSlotSetting> timeSlotSettings = new ArrayList<>();
            String jsonData = sService.getTimeSlotForUser(userId);

            Gson gson = new GsonBuilder().create();
            try {
                JSONObject jsonObject = new JSONObject(jsonData);
                timeSlotSettings = gson.fromJson(jsonObject.get("timeSlot").toString(), new TypeToken<ArrayList<TimeSlotSetting>>() {
                }.getType());

            } catch (JSONException e) {
                Log.e(TAG, "TimeSlotSettings : createFromJson", e);
            }

            return timeSlotSettings;
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static synchronized void saveTimeSlotForUser(ArrayList<TimeSlotSetting> timeSlotSettings) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            Gson gson = new GsonBuilder().create();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("timeSlot", gson.toJsonTree(timeSlotSettings));
            String timeSlot = gson.toJson(jsonObject);
            sService.saveTimeSlotsForUser(timeSlot);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static void saveTimeSlotForUser(TimeSlotSetting timeSlotSetting) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.saveTimeSlotForUser(timeSlotSetting.toJson());
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static void importTimeSlots(int fromUserId, int toUserId) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.importTimeSlots(fromUserId, toUserId);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static ArrayList<WebListInfo> getWebListForUser(int userId) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            String list = sService.getWebListForUser(userId);
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(list, new TypeToken<ArrayList<WebListInfo>>() {
            }.getType());
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static void addWebListForUsers(WebListInfo webListInfo) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.addWebListForUsers(webListInfo.toJson());
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static void deleteWebList(WebListInfo webListInfo) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.deleteWebList(webListInfo.toJson());
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static void updateWebList(WebListInfo webListInfo, int userId) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.updateWebList(webListInfo.toJson(), userId);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static void updateWebListUrl(String oldUrl, String newUrl) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.updateWebListUrl(oldUrl, newUrl);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static void importWebListFromChild(int fromChild, int toChild) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.importWebListFromChild(fromChild, toChild);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    private static boolean isRunOnUiThread() {
        Looper l = Looper.myLooper();
        if (Looper.getMainLooper() == l) {
            Log.e(TAG, "RemoteApi Should not be called from MainThread", new Throwable());
        }
        return false;
    }


    public static void enabledGoogleAccount(boolean enabled, int userId) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.enabledGoogleAccount(enabled, userId);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static boolean isGoogleAccountEnabled(int userId) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            return sService.isGoogleAccountEnabled(userId);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static boolean appsNeedGoogleAccount(ArrayList<ResolveInfo> appsToCheck) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            return sService.appsNeedGoogleAccount(appsToCheck);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static void increaseAnalyticsUsage(String usageKey) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.increaseAnalyticsUsage(usageKey);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static void timeManagementUsedOnce() throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.timeManagementUsedOnce();
        } else {
            throw new RemoteException("Service is not connected");
        }
    }

    public static void increaseAppAnalyticsValueForKey(String packageName, String key) throws RemoteException {
        if (sService != null && sConnected && !isRunOnUiThread()) {
            sService.increaseAppAnalyticsValueForKey(packageName, key);
        } else {
            throw new RemoteException("Service is not connected");
        }
    }
}
