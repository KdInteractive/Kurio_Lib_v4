package com.cide.interactive.kuriolib;

/**
 * Created by lionel on 10/12/13.
 */


public class Kurio {

    /**
     * *************************
     */

    public static final String DATE_FORMAT_FOR_DB = "dd-MM-yyyy";

    public static final String RECOVER_FOLDER_NAME = "/recover/";

    public static final String TITLE_PASSWORD = "TITLE_PASSWORD";
    public static final String FOR_TIME_LOCK = "FOR_TIME_LOCK";

    /***********
     * Broadcast
     */

    public static final String PACKAGE_ADDED = "com.cide.interactive.PACKAGE_ADDED";
    public static final String PACKAGE_REMOVED = "com.cide.interactive.PACKAGE_REMOVED";
    public static final String PACKAGE_CHANGED = "com.cide.interactive.PACKAGE_CHANGED";

    /**
     * Key for App category
     */
    public static final String APP_CATEGORY_UNCATEGORIZED_ID = "uncategorized";
    public static final String APP_CATEGORY_LICENCED = "licencedcategory";
    public static final String APP_CATEGORY_ALL_APPS = "allapps";
    public static final String APP_CATEGORY_FAVORITES = "favorites";
    public static final String APP_CATEGORY_UNKNOW = "unknow";
    public static final String APP_CATEGORY_EDUCATIONAL = "educational";
    public static final String APP_CATEGORY_OTHER = "activities";
    public static final String APP_CATEGORY_EBOOKS = "ebooks";

    /**
     * Keys for Analytics
     */
    public static final String ANALYTICS_APP_MANAGEMENT_COUNT = "app_management_count";
    public static final String ANALYTICS_FAQ_COUNT = "faq_count";
    public static final String ANALYTICS_USER_MANUAL_COUNT = "user_manual_count";
    public static final String ANALYTICS_CONTACT_US_COUNT = "contact_us_count";
    public static final String ANALYTICS_TIME_CONTROL_USED_ONCE = "time_control_used_once";
    public static final String ANALYTICS_LAST_PARENT_LOG = "last_parent_log";
    public static final String ANALYTICS_ACTIVE_PROFILES = "active_profiles";

    /**
     * Key for ParentPreferencesDataAccess
     */
    public static final String EMAIL_KEY = "email";
    public static final String SETUPDATE_KEY = "setup_date";
    public static final String EMAIL_DEMO_MODE = "demo@kurioworld.com";


    /**
     * Key for UpdatedValuesTable
     */

    public static final String UPDATED_APP_CATEGORY = "app_category_updated";
    public static final String RELOAD_LAUNCHER_APPS = "reload_launcher_apps";
    public static final String UPDATED_LAYOUT = "layout_updated";
    public static final String UPDATED_THEME = "updated_theme";

    /**
     * keys for package name
     */
    public static final String ADOBE_AIR_PACKAGE_NAME = "com.adobe.air";
    public static final String KIDOZ_STORE_PACKAGE_NAME = "com.kidoz.store.kurio";
    public static final String PACKAGE_KURIO_LAUNCHER = "com.cide.interactive.kurioLauncher";
    public static final String PACKAGE_KURIO_SERVICE = "com.cide.interactive.parentalArea";
    public static final String PACKAGE_KURIO_SETTINGS = "com.cide.interactive.kuriosettings";
    public static final String PACKAGE_KURIO_PRELOADER = "com.cide.interactive.preloader";
    public static final String PACKAGE_PLAY_STORE = "com.android.vending";
    public static final String PACKAGE_DEFAULT_BROWSER = "com.android.browser";
    public static final String PACKAGE_TECHNO_SOURCE = "com.kurio.support";
    public static final String KURIO_STORE_PACKAGE_NAME = "com.kidoz.store.kurio";


    /**
     * System Preload folder
     */
    public static final String SYSTEM_APP_PRELOAD_DIR = "/mnt/Shared2/.preload/";
    //File that is put on the tablet in the factory (to detect factory reset
    public static final String FILE_FROM_FACTORY = SYSTEM_APP_PRELOAD_DIR + "jsff";

    /**
     * KurioServer URLs
     */
    public static final String URL_HTTP_KDTABLET_COM = "http://www.kdtablets.com";
    public static final String URL_HTTPS_KDTABLET_COM = "https://www.kdtablets.com";
    public static final String URL_UPDATE_CHECK = URL_HTTP_KDTABLET_COM + "/downloads/updater.php";
    public static final String URL_UPDATE_DOWNLOAD_BASE = URL_HTTP_KDTABLET_COM + "/downloads/";

    /**
     * LICENCED PACKAGE
     */
    public static final String ACTION_LICENCED_PACKAGE = "com.cide.interactive.action.LICENSE_PACK";
    public static final String RAW_LICENCED_ACTIVITIES = "layoutlicencedcategory";

    public static final int ICON_DENSITY = 55;


    public final static String[] APP_TO_NOT_DISPLAY = {
            PACKAGE_KURIO_SERVICE,
            PACKAGE_KURIO_SETTINGS,
            PACKAGE_KURIO_LAUNCHER,
            "com.cide.interactive.licensepack",
//            "com.adobe.air",
//            "com.softwinner.update",
//            "com.softwinner.explore",
//            "com.intel.dongle",
//            "com.adobe.flashplayer",
            "com.google.android.gms",
            "com.android.settings",
            "com.kurio.appstore",
//            "com.cide.interactive.kuriodebugtool.app",
//            "com.lmi.general.rescue",
//            "com.kurio.support",
            "com.android.providers.downloads.ui",
            "com.cide.interactive.preloader",
            PACKAGE_PLAY_STORE
    };
}

