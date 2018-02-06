package com.uowee.easy.dialog.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;

/**
 * Created by GuoWee on 2018/2/6.
 */

public class Utils {

    public static int getColor(Context context, @ColorRes int colorId) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            //noinspection deprecation
            return context.getResources().getColor(colorId);
        } else {
            return context.getColor(colorId);
        }
    }

    /**
     * 将color转换为colorList，添加disable的颜色处理
     *
     * @param newPrimaryColor
     * @return
     */
    public static ColorStateList wrapColor(int newPrimaryColor) {
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_enabled}, // disabled
                new int[]{} // enabled
        };
        int[] colors = new int[]{
                adjustAlpha(newPrimaryColor, 0.4f),
                newPrimaryColor
        };
        return new ColorStateList(states, colors);
    }


    /**
     * 添加颜色透明度
     *
     * @param color
     * @param factor
     * @return
     */
    public static int adjustAlpha(int color, @SuppressWarnings("SameParameterValue") float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    public static int dp2px(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static Drawable getDrawable(Context context, int color) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            //noinspection deprecation
            return context.getResources().getDrawable(color);
        } else {
            return context.getDrawable(color);
        }
    }
}
