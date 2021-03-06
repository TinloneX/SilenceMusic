package com.angel.music.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Angel on 2017/4/24.
 */
public class Utils {

    /**
     * 判断是否是快速重复点击
     */
    private static long lastClickTime;
    private static String[] clickTag = new String[5];
    private static long[] clickTagTime = new long[5];
    private static int clickTagCunt = 0;

    public static boolean isFastClick() {//没有tag,会共用时间
        return isFastClick(800);
    }

    public static boolean isFastClick(int millisSecond) {//没有tag,会共用时间
        long time = System.currentTimeMillis();
        if (time - lastClickTime < millisSecond) {
            lastClickTime = time;
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }

    public static boolean isFastClick(@NonNull String tag) {//传唯一标识如className
        return isFastClick(tag, 800);
    }

    public static boolean isFastClick(@NonNull String tag, int millisSecond) {//传唯一标识如className
        long time = System.currentTimeMillis();
        boolean isSaved = false;
        for (int i = 0; i < clickTag.length; i++) {
            if (tag.equals(clickTag[i])) {
                if (time - clickTagTime[i] < millisSecond) {
                    clickTagTime[i] = time;
                    return true;
                } else {
                    clickTagTime[i] = time;
                    return false;
                }
            }
        }
        if (!isSaved) {
            clickTag[clickTagCunt] = tag;
            clickTagTime[clickTagCunt] = time;
            clickTagCunt++;
        }
        return false;
    }
}
