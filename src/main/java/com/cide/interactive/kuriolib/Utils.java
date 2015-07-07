package com.cide.interactive.kuriolib;

import android.app.StatusBarManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Environment;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.util.Patterns;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final String TAG = "Utils";
    private static String sRomId = null;
    private static String sSerialId = null;

    public static String formatCalendarToLocalFormat(Context context, Calendar c) {
        java.text.DateFormat dateFormat = android.text.format.DateFormat
                .getDateFormat(context);
        return dateFormat.format(c.getTime());
    }

    public static String formatCalendar(Calendar c, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        return sdf.format(c.getTime());
    }

    public static Calendar getCalendar(String date, String format) {
        Calendar result = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        try {
            Date dateD = sdf.parse(date);
            result.setTime(dateD);
        } catch (ParseException e) {

        }
        return result;
    }

    public static String getRomId() {
        if (sRomId == null) {
            try {
                sRomId = getSerialId().substring(17, 20);
            } catch (Exception e) {
                sRomId = "";
            }
        }
        return sRomId;
    }

    public static String getDefaultPref(String key, String defVal) {
        String value = defVal;
        File preloadFile = new File(Kurio.SYSTEM_APP_PRELOAD_DIR, "default.pref");
        File obbFile = new File(Environment.getLegacyExternalStorageObbDirectory(), "default.pref");
        if (!preloadFile.exists()) {
            try {
                FileUtils.copyFile(new FileInputStream(obbFile), new FileOutputStream(preloadFile));
            } catch (IOException e) {
            }
        }
        String line;
        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(preloadFile);

            br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("#")) {
                    continue;
                }
                String[] keyValue = line.split(":");
                if (keyValue[0].equals(key)) {
                    value = keyValue[1];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    public static String getSerialId() {
        if (sSerialId == null) {

            try {
                sSerialId = new BufferedReader(new InputStreamReader(
                        new FileInputStream(new File(Environment
                                .getLegacyExternalStorageObbDirectory().getAbsolutePath(), "sn.txt")
                        ))).readLine();
            } catch (Exception e) {
                sSerialId = "";
            }

            if (sSerialId == "") {
                try {
                    sSerialId = Build.SERIAL;
                } catch (Exception e) {
                    sSerialId = "";
                }
            }
        }
        return sSerialId;
    }

    public static void disableAllButtons(StatusBarManager statusBarManager) {
        statusBarManager.disable(View.STATUS_BAR_DISABLE_HOME | View.STATUS_BAR_DISABLE_RECENT
                | View.STATUS_BAR_DISABLE_BACK | View.STATUS_BAR_DISABLE_NOTIFICATION_ICONS
                | View.STATUS_BAR_DISABLE_NOTIFICATION_TICKER | View.STATUS_BAR_DISABLE_SYSTEM_INFO
                | View.STATUS_BAR_DISABLE_NOTIFICATION_ALERTS | View.STATUS_BAR_DISABLE_EXPAND
                | View.STATUS_BAR_DISABLE_SEARCH);
    }

    public static void disableInstallationCheckForUser(int mUserId) {
        ComponentName componentName = new ComponentName(Kurio.PACKAGE_KURIO_SERVICE, "com.cide.interactive.parentalArea.Receivers.PackageInfoReceiver");
        IPackageManager pmForUser = IPackageManager.Stub.asInterface(ServiceManager.getService("package"));
        try {
            pmForUser.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP, mUserId);
        } catch (Exception e) {
            Log.e(TAG, "disableInstallationCheckForUser : ", e);
        }
    }

    public static void enableInstallationCheckForUser(int mUserId) {
        ComponentName componentName = new ComponentName(Kurio.PACKAGE_KURIO_SERVICE, "com.cide.interactive.parentalArea.Receivers.PackageInfoReceiver");
        IPackageManager pmForUser = IPackageManager.Stub.asInterface(ServiceManager.getService("package"));
        try {
            pmForUser.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP, mUserId);
        } catch (Exception e) {
            Log.e(TAG, "enableInstallationCheckForUser : ", e);
        }
    }

    public static Context getOwnerContext(Context context) {
        Context userContext = null;
        try {
            userContext = context.createPackageContextAsUser(Kurio.PACKAGE_KURIO_SERVICE, 0, UserHandle.OWNER);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return userContext;
    }

    public static String getLicensedPackageName(PackageManager pm) {
        final Intent intent = new Intent(Kurio.ACTION_LICENCED_PACKAGE);

        for (ResolveInfo info : pm.queryBroadcastReceivers(intent, 0)) {
            if (info.activityInfo != null) {
                final String packageName = info.activityInfo.packageName;
                return packageName;
            }
        }
        return null;
    }

    public static String getLicencedCopyrights(Context context) {

        String packageName = Utils.getLicensedPackageName(context.getPackageManager());
        if (packageName == null) {
            packageName = Kurio.PACKAGE_KURIO_SETTINGS;
        }
        Resources res;
        try {
            res = context.getPackageManager().getResourcesForApplication(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            res = context.getResources();
            e.printStackTrace();
        }

        int copyrightsId = res.getIdentifier("copyrights", "string", packageName);
        return res.getString(copyrightsId);
    }

    public static ArrayList<String> getLicencedLayout(Context context) {
        String packageName = Utils.getLicensedPackageName(context.getPackageManager());

        if (packageName == null) {
            packageName = Kurio.PACKAGE_KURIO_SETTINGS;
        }
        ArrayList<String> dataFromFile =
                FileUtils.getTextFileAsArray(context,
                        packageName, "raw", Kurio.RAW_LICENCED_ACTIVITIES);

        return dataFromFile;
    }

    public static boolean whetherStringIsUrl(String strUrl) {
        String strPattern = "^([a-zA-Z0-9-_]+\\.)+([a-zA-z/]+)$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strUrl);
        return m.matches();
    }

    public static String getTimeStringFormat(Context context, int hours, int minutes) {
        String hourString = String.valueOf(hours);
        String minutesString = String.valueOf(minutes);
        if (hours < 10) {
            hourString = "0" + hourString;
        }
        if (minutes < 10) {
            minutesString = "0" + minutesString;
        }

        return context.getResources().getString(R.string.time_format_hour_minute, hourString, minutesString);
    }

    public static String getTimeStringAMPMFormat(Context context, int hours, int minutes) {
        String hoursString = String.valueOf(hours);
        String minutesString = String.valueOf(minutes);

        if (minutes < 10) {
            minutesString = "0" + minutesString;
        }

        return hoursString + ":" + minutesString;
    }

    public static String getTimeStringFormat(Context context, int minutes) {
        int hour = minutes / 60;
        int mins = minutes % 60;
        String hoursString = String.valueOf(hour);
        String minutesString = String.valueOf(mins);
        if (hour < 10) {
            hoursString = "0" + hoursString;
        }
        if (mins < 10) {
            minutesString = "0" + minutesString;
        }

        return context.getResources().getString(R.string.time_format_hour_minute, hoursString, minutesString);
    }

    public static String getTimeStringAMPMFormat(Context context, int minutes) {
        int hour = minutes / 60;
        int mins = minutes % 60;
        String hoursString = String.valueOf(hour);
        String minutesString = String.valueOf(mins);

        if (mins < 10) {
            minutesString = "0" + minutesString;
        }

        return hoursString + ":" + minutesString;
    }

    public static String getHourStringFormat(Context context, int hours) {
        String hoursString = String.valueOf(hours);
        if (hours < 10) {
            hoursString = "0" + hoursString;
        }

        return context.getResources().getString(R.string.time_format_hour, hoursString);
    }

    public static String getHourStringAMPMFormat(Context context, int hours) {
        return String.valueOf(hours);
    }

    public static String getFirstLetterDay(TimeSlotSetting ts) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, ts.getDay());

        SimpleDateFormat sdf = new SimpleDateFormat("E");

        return String.valueOf(sdf.format(c.getTime()).charAt(0));
    }

    public static SpannableString createSpannableString(String text, ArrayList<ImageSpan> images) {
        SpannableString spannableString = new SpannableString(text);
        for (int index = 1 ; index < images.size() + 1 ; index++) {
            if (text.contains("$" + index)) {
                spannableString.setSpan(images.get(index-1), text.indexOf("$" + index), text.indexOf("$" + index) + ("$" + index).length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            }
        }

        return spannableString;
    }

    public static boolean isEmailAddressValid(CharSequence email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
