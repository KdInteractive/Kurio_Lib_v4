package com.cide.interactive.kuriolib;

import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.ServiceManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lionel on 06/02/15.
 */
public class AppManagementUtil {


    public static ArrayList<String> GOOGLE_ACCOUNT_PACKAGE_NAME;

    static {
        GOOGLE_ACCOUNT_PACKAGE_NAME = new ArrayList<>(0);
        GOOGLE_ACCOUNT_PACKAGE_NAME.add("com.google.android.gsf.login");
        GOOGLE_ACCOUNT_PACKAGE_NAME.add("com.google.android.gms");
    }

    public static ArrayList<String> SPECIFIC_SYSTEM_DEPENDS_ON;

    static {
        SPECIFIC_SYSTEM_DEPENDS_ON = new ArrayList<>(0);
        SPECIFIC_SYSTEM_DEPENDS_ON.add(Kurio.PACKAGE_PLAY_STORE);
        SPECIFIC_SYSTEM_DEPENDS_ON.add("com.google.android.apps.plus");
        SPECIFIC_SYSTEM_DEPENDS_ON.add("com.google.android.apps.docs");
        SPECIFIC_SYSTEM_DEPENDS_ON.add("com.google.android.gm");
        SPECIFIC_SYSTEM_DEPENDS_ON.add("com.google.android.talk");
        SPECIFIC_SYSTEM_DEPENDS_ON.add("com.google.android.calendar");
    }


    public static ArrayList<String> SPECIFIC_APPS_TO_NOT_DISPLAY_IN_CHILD;

    static {
        SPECIFIC_APPS_TO_NOT_DISPLAY_IN_CHILD = new ArrayList<>(0);
        SPECIFIC_APPS_TO_NOT_DISPLAY_IN_CHILD.add(Kurio.ADOBE_AIR_PACKAGE_NAME);
        SPECIFIC_APPS_TO_NOT_DISPLAY_IN_CHILD.add("com.android.settings");
    }

    /**
     * Query the package manager for MAIN/LAUNCHER activities in the supplied
     * package.
     * <p/>
     * in general the flag here is PackageManager.GET_UNINSTALLED_PACKAGES
     */
    public static List<ResolveInfo> findLaunchableActivitiesForPackage(Context context,
                                                                       String packageName, int flags) {
        final PackageManager packageManager = context.getPackageManager();

        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mainIntent.setPackage(packageName);

        final List<ResolveInfo> apps = packageManager.queryIntentActivities(
                mainIntent, flags);

        return apps != null ? apps : new ArrayList<ResolveInfo>();
    }

    /**
     * Query the package manager for MAIN/LAUNCHER activities for all packages for a specific user
     * <p/>
     * //in general the flag is 0 here
     */
    public static List<ResolveInfo> findAllLaunchableActivitiesForUser(Context context, int userId, int flags) {

        final PackageManager packageManager = context.getPackageManager();

        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> apps = packageManager.queryIntentActivitiesAsUser(
                mainIntent, flags, userId);

        return apps != null ? apps : new ArrayList<ResolveInfo>();
    }

    /**
     * Query the package manager for HOME activity for all packages
     */
    public static List<ResolveInfo> findAllLauncher(Context context) {
        final PackageManager packageManager = context.getPackageManager();

        final ArrayList<ResolveInfo> apps = new ArrayList<>();
        packageManager.getHomeActivities(apps);
        return apps != null ? apps : new ArrayList<ResolveInfo>();
    }

    /**
     * Query the package manager for MAIN activities in the supplied
     * package.
     * <p/>
     * for example
     * flags can be PackageManager.GET_DISABLED_COMPONENTS | PackageManager.GET_UNINSTALLED_PACKAGES
     */

    public static List<ResolveInfo> findMainActivitiesForPackage(Context context,
                                                                 String packageName, int flags) {
        final PackageManager packageManager = context.getPackageManager();

        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.setPackage(packageName);

        final List<ResolveInfo> apps = packageManager.queryIntentActivities(
                mainIntent, flags);

        return apps != null ? apps : new ArrayList<ResolveInfo>(0);
    }

    /**
     * Query the package manager for MAIN/LAUNCHER activities for all packages
     */
    public static List<ResolveInfo> findAllActivities(Context context, int flags) {

        final PackageManager packageManager = context.getPackageManager();
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        ArrayList<ResolveInfo> allAppCategoryInfo = new ArrayList<>();
        allAppCategoryInfo.addAll(packageManager.queryIntentActivities(
                mainIntent, flags));

        return allAppCategoryInfo;
    }

    /**
     * Query the package manager for browser activity for all packages
     */
    public static List<ResolveInfo> findBrowserActivities(Context context) {
        final PackageManager packageManager = context.getPackageManager();

        final Intent mainIntent = new Intent(Intent.ACTION_VIEW, null);
        mainIntent.setData(Uri.parse("http://www.google.com"));

        final List<ResolveInfo> apps = packageManager.queryIntentActivities(
                mainIntent, PackageManager.GET_UNINSTALLED_PACKAGES);

        return apps != null ? apps : new ArrayList<ResolveInfo>();
    }

    public static ArrayList<ResolveInfo> removeAppsToNotDisplay(List<ResolveInfo> allActivities) {

        ArrayList<String> appsToNotDisplay = new ArrayList<>(Arrays.asList(Kurio.APP_TO_NOT_DISPLAY));

        ArrayList<ResolveInfo> newAllActivities = new ArrayList<>();

        for (ResolveInfo rInfo : allActivities) {

            if (!appsToNotDisplay.contains(rInfo.activityInfo.packageName)) {
                newAllActivities.add(rInfo);
            }
        }

        return newAllActivities;
    }

    public static ArrayList<String[]> removeAppsToNotDisplay(ArrayList<String[]> appsToDisplay) {

        ArrayList<String> appsToNotDisplay = new ArrayList<>(Arrays.asList(Kurio.APP_TO_NOT_DISPLAY));

        ArrayList<String[]> newAllActivities = new ArrayList<>();

        for (String[] app : appsToDisplay) {

            if (!appsToNotDisplay.contains(app[0])) {
                newAllActivities.add(app);
            }
        }

        return newAllActivities;
    }

    /**
     * Query the package manager for MAIN/LAUNCHER activities for all packages
     * without non visible packages
     */
    public static List<ResolveInfo> findActivitiesForVisiblePackages(Context context) {

        List<ResolveInfo> allAppCategoryInfo = null;

        allAppCategoryInfo = com.cide.interactive.kuriolib.AppManagementUtil
                .findAllActivities(context, PackageManager.GET_UNINSTALLED_PACKAGES);

        final List<ResolveInfo> apps = removeAppsToNotDisplay(allAppCategoryInfo);

        return apps != null ? apps : new ArrayList<ResolveInfo>();
    }

    public static boolean setApplicationHiddenAsUser(
            String packageName, boolean blocked, int userHandle) {
        final IPackageManager pmForUser
                = IPackageManager.Stub.asInterface(ServiceManager.getService("package"));
        try {
            Method setApplicationHiddenSettingAsUser;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setApplicationHiddenSettingAsUser =
                        pmForUser.getClass().getMethod("setApplicationHiddenSettingAsUser"
                                , String.class, boolean.class, int.class);
            } else {
                setApplicationHiddenSettingAsUser =
                        pmForUser.getClass().getMethod("setApplicationBlockedSettingAsUser"
                                , String.class, boolean.class, int.class);
            }
            return (boolean) setApplicationHiddenSettingAsUser
                    .invoke(pmForUser, packageName, blocked, userHandle);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean getApplicationHiddenAsUser(
            String packageName, int userHandle) {
        final IPackageManager pmForUser
                = IPackageManager.Stub.asInterface(ServiceManager.getService("package"));

        try {
            Method getApplicationHiddenSettingAsUser;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getApplicationHiddenSettingAsUser =
                        pmForUser.getClass().getMethod("getApplicationHiddenSettingAsUser"
                                , String.class, int.class);
            } else {
                getApplicationHiddenSettingAsUser =
                        pmForUser.getClass().getMethod("getApplicationBlockedSettingAsUser"
                                , String.class, int.class);
            }
            return (boolean) getApplicationHiddenSettingAsUser
                    .invoke(pmForUser, packageName, userHandle);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

}
