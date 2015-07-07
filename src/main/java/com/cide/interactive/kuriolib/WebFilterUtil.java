package com.cide.interactive.kuriolib;

import android.content.Context;

import com.cide.interactive.kuriolib.FileUtils;


import java.util.LinkedHashMap;

/**
 * Created by leehack on 23/12/14.
 */
public class WebFilterUtil {

    public final static String AUTOMATIC_FILTER = "A@";
    public final static String CUSTOM_FILTER = "C@";

    public static int[] AGE_LIMIT = {8, 10, 12};

    public static LinkedHashMap<String, Integer> FILTER_LIST;

    static {
        FILTER_LIST = new LinkedHashMap<>();
        FILTER_LIST.put("2100", R.string.filter_adult);
        FILTER_LIST.put("2114", R.string.filter_uncategorized);
        FILTER_LIST.put("2102", R.string.filter_chat_sms);
        FILTER_LIST.put("2112", R.string.filter_social_networking);
        FILTER_LIST.put("2101", R.string.filter_business);
        FILTER_LIST.put("2111", R.string.filter_sex_education);
        FILTER_LIST.put("2104", R.string.filter_entertainement);
        FILTER_LIST.put("2108", R.string.filter_streaming);
        FILTER_LIST.put("2105", R.string.filter_health);
        FILTER_LIST.put("2113", R.string.filter_sports);
        FILTER_LIST.put("2110", R.string.filter_religion);
        FILTER_LIST.put("2107", R.string.filter_online_shopping);
        FILTER_LIST.put("2109", R.string.filter_search_engines);
        FILTER_LIST.put("2106", R.string.filter_misc);
        FILTER_LIST.put("2115", R.string.filter_online_ads);
//        FILTER_LIST.put("2103", R.string.filter_child);
    }


    public static String createFilterInfo(Context context, int age, final boolean isCustom) {
        String settings = isCustom ? CUSTOM_FILTER : AUTOMATIC_FILTER;

        if (age < AGE_LIMIT[0]) {
            return settings.concat(FileUtils.readTextFromResource(context, R.raw.filter_info_less_7));
        } else if (age < AGE_LIMIT[1]) {
            return settings.concat(FileUtils.readTextFromResource(context, R.raw.filter_info_8));
        } else if (age < AGE_LIMIT[2]) {
            return settings.concat(FileUtils.readTextFromResource(context, R.raw.filter_info_10));
        } else {
            return settings.concat(FileUtils.readTextFromResource(context, R.raw.filter_info_13));
        }
    }

    public static int getLabelResIdForFilter(String filterId) {
        return FILTER_LIST.get(filterId);
    }

    public static String getAdultFilter() {
        return "2100";
    }

}
