package com.cide.interactive.kuriolib;

import android.content.ContentProvider;
import android.net.Uri;

/**
 * Created by lionel on 07/03/14.
 */
public abstract class DBUriManager extends ContentProvider {

    protected static final String AUTHORITY = "com.cide.interactive.parentalArea.Providers.ParentalDataBaseProvider";

    protected static final String BASE_PATH_CHILD = "childinfo";
    protected static final String BASE_PATH_ACTIVITY_STATUS = "activitystatus";
    protected static final String BASE_PATH_LAYOUT = "layout";
    protected static final String BASE_PATH_TO_ADD_TO_CHILD_LAYOUT = "to_add_to_child_layout";
    protected static final String BASE_PATH_APP_CATEGORY = "appcategory";
    protected static final String BASE_PATH_APP_WHITE_LIST = "appwhitelist";
    protected static final String BASE_PATH_PARENT_PREFERENCES = "parentpreferences";
    protected static final String BASE_PATH_UPDATED_VALUES = "updatedvalues";
    protected static final String BASE_PATH_LAYOUT_FOLDER = "layout_folder";
    protected static final String BASE_PATH_LAYOUT_FOLDER_ACTIVITIES = "layout_folder_activities";
    protected static final String BASE_PATH_APP_ANALYTICS = "app_analytics";
    protected static final String BASE_PATH_TIME_SLOT_SETTINGS = "time_slot_settings";
    protected static final String BASE_PATH_WEB_LIST = "web_list";

    /**
     * URI TO ACCESS SPECIFIC TABLE
     */
    public static final Uri CONTENT_URI_CHILDINFOS = Uri.parse("content://" + AUTHORITY
            + '/' + BASE_PATH_CHILD);

    public static final Uri CONTENT_URI_ACTIVITY_STATUS = Uri.parse("content://" + AUTHORITY
            + '/' + BASE_PATH_ACTIVITY_STATUS);

    public static final Uri CONTENT_URI_LAYOUT = Uri.parse("content://" + AUTHORITY
            + '/' + BASE_PATH_LAYOUT);

    public static final Uri CONTENT_URI_TO_ADD_TO_CHILD_LAYOUT = Uri.parse("content://" + AUTHORITY
            + '/' + BASE_PATH_TO_ADD_TO_CHILD_LAYOUT);

    public static final Uri CONTENT_URI_APP_CATEGORY = Uri.parse("content://" + AUTHORITY
            + '/' + BASE_PATH_APP_CATEGORY);

    public static final Uri CONTENT_URI_APP_WHITE_LIST = Uri.parse("content://" + AUTHORITY
            + '/' + BASE_PATH_APP_WHITE_LIST);

    public static final Uri CONTENT_URI_PARENT_PREFERENCES = Uri.parse("content://" + AUTHORITY
            + '/' + BASE_PATH_PARENT_PREFERENCES);

    public static final Uri CONTENT_URI_UPDATED_VALUES = Uri.parse("content://" + AUTHORITY
            + '/' + BASE_PATH_UPDATED_VALUES);

    public static final Uri CONTENT_URI_LAYOUT_FOLDER = Uri.parse("content://" + AUTHORITY
            + '/' + BASE_PATH_LAYOUT_FOLDER);

    public static final Uri CONTENT_URI_LAYOUT_FOLDER_ACTIVITIES = Uri.parse("content://" + AUTHORITY
            + '/' + BASE_PATH_LAYOUT_FOLDER_ACTIVITIES);

    public static final Uri CONTENT_URI_APP_ANALYTICS = Uri.parse("content://" + AUTHORITY
            + '/' + BASE_PATH_APP_ANALYTICS);

    public static final Uri CONTENT_URI_TIME_SLOT_SETTINGS = Uri.parse("content://" + AUTHORITY
            + '/' + BASE_PATH_TIME_SLOT_SETTINGS);

    public static final Uri CONTENT_URI_WEB_LIST = Uri.parse("content://" + AUTHORITY
            + '/' + BASE_PATH_WEB_LIST);

    /*
     * URI to access method
     */
    public static final Uri CONTENT_URI_CALL = Uri.parse("content://" + AUTHORITY + "/method");

    /**
     * TABLE NAME
     */


    public static final String LAYOUT_TABLE = "layout";
    public static final String LAYOUT_FOLDER_TABLE = "layout_folder";
    public static final String LAYOUT_FOLDER_ACTIVITIES_TABLE = "layout_folder_activities";
    public static final String CHILDINFO_TABLE = "childinfo";
    public static final String APP_CATEGORY_TABLE = "appcategory";
    public static final String ACTIVITY_STATUS_TABLE = "activitystatus";

    public static final String UPDATED_VALUES_TABLE = "updatedvalues";
    public static final String TO_ADD_TO_CHILD_LAYOUT_TABLE = "to_add_to_child_layout_table";

    /**
     * LAYOUT_FOLDER
     */

    public static final String LAYOUT_FOLDER_ID = "layout_folder_id";
    public static final String LAYOUT_FOLDER_NAME = "layout_folder_name";

    /**
     * LAYOUT_FOLDER_ACTIVITIES_TABLE
     */

    public static final String LAYOUT_FOLDER_ACTIVITIES_PACKAGE_NAME = "layout_folder_activities_package_name";
    public static final String LAYOUT_FOLDER_ACTIVITIES_ACTIVITY_NAME = "layout_folder_activities_activity_name";
    public static final String LAYOUT_FOLDER_ACTIVITIES_POSITION_X = "layout_folder_activities_x_position";

    /**
     * TO_ADD_TO_CHILD_LAYOUT_TABLE
     */

    public static final String TO_ADD_TO_CHILD_LAYOUT_USER_ID = "to_add_to_child_layout_user_id";
    public static final String TO_ADD_TO_CHILD_LAYOUT_PACKAGE_NAME = "to_add_to_child_layout_package_name";
    public static final String TO_ADD_TO_CHILD_LAYOUT_ACTIVITY_NAME = "to_add_to_child_layout_activity_name";

    /**
     * LAYOUT
     */

    public static final String LAYOUT_ID = "layout_id";
    public static final String LAYOUT_USER_ID = "user_id";
    public static final String LAYOUT_PACKAGE_NAME = "package_name";
    public static final String LAYOUT_ACTIVITY_NAME = "activity_name";
    //from the LAYOUT_FOLDER table : public static final String LAYOUT_FOLDER_ID = "folder_id";
    public static final String LAYOUT_CATEGORY_ID = "category_id";
    public static final String LAYOUT_POSITION_X = "position_x";
    public static final String LAYOUT_POSITION_Y = "position_y";

    /**
     * CHILD INFO
     */
    public static final String CHILDINFO_ID = "_id";
    public static final String CHILDINFO_UID = "user_id";
    public static final String CHILDINFO_NAME = "name";
    public static final String CHILDINFO_AGE = "age";
    public static final String CHILDINFO_GENDER = "gender";
    public static final String CHILDINFO_THEME = "theme";
    public static final String CHILDINFO_BIRTH = "birth";
    public static final String CHILDINFO_ACTIVATE_ADS_FILTER = "authorize_other_app_call";
    public static final String CHILDINFO_ALLOW_USB_CONNECTION = "allow_usb_connection";
    public static final String CHILDINFO_TIME_CONTROL_ON = "time_control_on";
    public static final String CHILDINFO_LOCKED_INTERFACE = "locked_interface";
    public static final String CHILDINFO_AUTO_AUTHORIZE_APPLICATION = "auto_authorize_application";
    public static final String CHILDINFO_INTERNET_ACCESS_ON = "internet_access_on";
    public static final String CHILDINFO_PROFILE_TYPE = "profile_type";
    public static final String CHILDINFO_DEMO_MODE = "demo_mode";
    public static final String CHILDINFO_PROFILE_CHANGED = "profile_changed";
    public static final String CHILDINFO_ANALYTICS_UID = "analytics_uid";
    public static final String CHILDINFO_FILTER_INFO_NUMBERS = "filter_info_numbers";
    public static final String CHILDINFO_WEB_LIST_ON = "web_list_on";
    public static final String CHILDINFO_FILTER_ON = "filter_on";

    /**
     * APP WHITE LIST
     */
    public static final String APP_WHITE_LIST_ID = "_id";
    public static final String APP_WHITE_LIST_UID = "user_id";
    public static final String APP_WHITE_LIST_PACKAGE_NAME = "package_name";

    /**
     * APP CATEGORY
     */
    public static final String APP_CATEGORY_ID = "category_id";
    public static final String APP_CATEGORY_PACKAGE_NAME = "package_name";
    public static final String APP_CATEGORY_ACTIVITY_NAME = "activity_name";
    //this value is used to know if the app is new (new install and categorized or not)
    // we display those apps in the pop up
    // once the user press ok, or if in the app management the app is categorized,
    // the boolean pass to false, the app is not needed anymore in the pop up
    // same for an uninstall, we pass the boolean to false, the app is not displayed in the pop up
    // we can keep the old categorization like that
    public static final String APP_CATEGORY_IS_NEW_INSTALL = "is_new_install";

    /**
     * ACTIVITY STATUS
     */
    public static final String ACTIVITY_STATUS_ID = "_id";
    public static final String ACTIVITY_STATUS_UID = "user_id";
    public static final String ACTIVITY_STATUS_PACKAGE_NAME = "package_name";
    public static final String ACTIVITY_STATUS_NAME = "activity_name";
    public static final String ACTIVITY_STATUS_ENABLED = "enabled";
    public static final String ACTIVITY_STATUS_LAUNCH_COUNT = "launch_count";
    public static final String ACTIVITY_STATUS_TIME_SPENT = "time_spent";
    public static final String ACTIVITY_STATUS_LAST_KNOW_CATEGORY = "activity_status_last_know_category";


    /**
     * UPDATED VALUES
     * table used to know what we have to reload
     * for this table, we can set a key and an associate value
     */

    public static final String UPDATED_VALUES_KEY = "key";
    public static final String UPDATED_VALUES_VALUE = "value";
    public static final String UPDATED_VALUES_USER_ID = "user_id";


    /**
     * WEB LIST
     * table used to know what we have to reload
     * for this table, we can set a key and an associate value
     */


    /*
     * METHODS
     */
    public static final String METHOD_UPDATE_TABLE = "update_table";

}