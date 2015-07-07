// IAccountManagerService.aidl
package com.cide.interactive.parentalArea.Api;

interface IRemoteApi {

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    boolean deleteUser(int userId);
    List getAllUserId();

    void authorizeAppsForUsers(in List activityInfoToEnable, in List appsToDisable, int userId, boolean forDefaultList);

    //app Category
    void setCategoryForApp(String packageName, String activityName, String categroyId);
    List getAppCategory();
    List getAppForCategory(String categoryId);
    String getCategoryIdForApp(String packageName, String activityName);
    Map getCategories();
    List getDefaultAppsToEnable();
    List getDefaultAppsToDisable();
    List getAppsToCategorize();

    //PArentPreference
    void setParentPreferences(String key, String value);
    String getParentPreferencesForKey(String key);

    //to replace DBrequestHelper
    void needToReload(String keyToReload, int userId);

    //import
    void importAppsFromOtherChild(int currentChildId, int otherChildId);

    int createChildFromJson(String childInfoAsJson);
    String getChildAsJson(int userId);
    void saveChildInfoFromJson(String childAsJson, int userId);

    String getTimeSlotForUser(int userId);
    void saveTimeSlotsForUser(String timeSlot);
    void saveTimeSlotForUser(String timeSlot);
    void importTimeSlots(int fromUserId, int toUserId);

    String getWebListForUser(int userId);
    void addWebListForUsers(String webListInfoAsJson);
    void deleteWebList(String webListInfoAsJson);
    void deleteWebListForUser(int userId);
    void updateWebList(String webListInfoAsJson, int userId);
    void updateWebListUrl(String oldUrl, String newUrl);
    void importWebListFromChild(int fromChild, int toChild);

    void enabledGoogleAccount(boolean enabled, int userId);
    boolean isGoogleAccountEnabled(int userId);
    boolean appsNeedGoogleAccount(in List appsToCheck);

    void increaseAnalyticsUsage(String usageKey);
    void timeManagementUsedOnce();
    void increaseAppAnalyticsValueForKey(String packageName, String key);
}