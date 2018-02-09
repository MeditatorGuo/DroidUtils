package com.uowee.utauta.system;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;

import com.uowee.utauta.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by GuoWee on 2018/1/17.
 */

public final class AppUtil {
    public static final String SEMICOLON = ";";
    public static final String SourceType = "Android";

    private AppUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isRunningOnEmulator() {
        return Build.BRAND.contains("generic")
                || Build.DEVICE.contains("generic")
                || Build.PRODUCT.contains("sdk")
                || Build.HARDWARE.contains("goldfish")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.PRODUCT.contains("vbox86p")
                || Build.DEVICE.contains("vbox86p")
                || Build.HARDWARE.contains("vbox86");
    }

    /**
     * need < uses-permission android:name =“android.permission.GET_TASKS” />
     * 判断是否前台运行
     */
    public static boolean isRunningForeground() {
        ActivityManager am = (ActivityManager) Util.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName componentName = taskList.get(0).topActivity;
            if (componentName != null && componentName.getPackageName().equals(Util.getApp().getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取App包 信息版本号
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageManager packageManager = Util.getApp().getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(Util.getApp().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    /**
     * 判断APK包是否已经安装
     *
     * @param packageName 包名
     * @return 包存在则返回true，否则返回false
     */
    public static boolean isPackageExists(String packageName) {
        if (null == packageName || "".equals(packageName)) {
            throw new IllegalArgumentException("Package name cannot be null or empty !");
        }
        try {
            ApplicationInfo info = Util.getApp().getPackageManager()
                    .getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return null != info;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static String getAppName(String packageName) {
        PackageManager pm = Util.getApp().getPackageManager();
        String appName = null;
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            appName = String.valueOf(pm.getApplicationLabel(applicationInfo));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;
    }


    public static Drawable getAppIcon(String packageName) {
        PackageManager pm = Util.getApp().getPackageManager();
        Drawable appIcon = null;
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            appIcon = applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appIcon;
    }


    public static long getAppDate(String packageName) {
        long lastUpdateTime = 0;
        try {
            PackageInfo packageInfo = Util.getApp().getPackageManager().getPackageInfo(packageName, 0);
            lastUpdateTime = packageInfo.lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return lastUpdateTime;
    }


    public static long getAppSize(String packageName) {
        long appSize = 0;
        try {
            ApplicationInfo applicationInfo = Util.getApp().getPackageManager().getApplicationInfo(packageName, 0);
            appSize = new File(applicationInfo.sourceDir).length();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appSize;
    }

    public static String getAppApk(String packageName) {
        String sourceDir = null;
        try {
            ApplicationInfo applicationInfo = Util.getApp().getPackageManager().getApplicationInfo(packageName, 0);
            sourceDir = applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return sourceDir;
    }

    public static void createDeskShortCut(String shortCutName, int icon, Class<?> cls) {
        Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortcutIntent.putExtra("duplicate", false);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortCutName);
        Parcelable ico = Intent.ShortcutIconResource.fromContext(Util.getApp().getApplicationContext(), icon);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, ico);
        Intent intent = new Intent(Util.getApp(), cls);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        Util.getApp().sendBroadcast(shortcutIntent);
    }

    public static void createShortcut(String shortCutName, int iconId, Intent presentIntent) {
        Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortcutIntent.putExtra("duplicate", false);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortCutName);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(Util.getApp(), iconId));
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, presentIntent);
        Util.getApp().sendBroadcast(shortcutIntent);
    }

    /**
     * @return 当前程序的版本名称
     */
    public static String getVersionName() {
        String version;
        try {
            PackageManager pm = Util.getApp().getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(Util.getApp().getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            version = "";
        }
        return version;
    }

    /**
     * 方法: getVersionCode
     * 描述: 获取客户端版本号
     *
     * @return int    版本号
     */
    public static int getVersionCode() {
        int versionCode;
        try {
            PackageManager pm = Util.getApp().getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(Util.getApp().getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            versionCode = 999;
        }
        return versionCode;
    }

    /**
     * 获取进程名字
     *
     * @param pid
     * @return
     */
    public static String getProcessName(int pid) {
        ActivityManager am = (ActivityManager) Util.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    /**
     * 执行脚本
     *
     * @param script
     * @return
     */
    public static String runScript(String script) {
        String sRet;
        try {
            final Process m_process = Runtime.getRuntime().exec(script);
            final StringBuilder sbread = new StringBuilder();
            Thread tout = new Thread(new Runnable() {
                public void run() {
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(m_process.getInputStream()),
                            8192);
                    String ls_1;
                    try {
                        while ((ls_1 = bufferedReader.readLine()) != null) {
                            sbread.append(ls_1).append("\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            tout.start();

            final StringBuilder sberr = new StringBuilder();
            Thread terr = new Thread(new Runnable() {
                public void run() {
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(m_process.getErrorStream()),
                            8192);
                    String ls_1;
                    try {
                        while ((ls_1 = bufferedReader.readLine()) != null) {
                            sberr.append(ls_1).append("\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            terr.start();

            m_process.waitFor();
            while (tout.isAlive()) {
                Thread.sleep(50);
            }
            if (terr.isAlive())
                terr.interrupt();
            String stdout = sbread.toString();
            String stderr = sberr.toString();
            sRet = stdout + stderr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sRet;
    }

    public static void killProcesses(int pid, String processName) {
        ActivityManager activityManager = (ActivityManager) Util.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName;
        try {
            if (!processName.contains(":")) {
                packageName = processName;
            } else {
                packageName = processName.split(":")[0];
            }
            activityManager.killBackgroundProcesses(packageName);
            Method forceStopPackage = activityManager.getClass().getDeclaredMethod("forceStopPackage", String.class);
            forceStopPackage.setAccessible(true);
            forceStopPackage.invoke(activityManager, packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isSystemApp(String packageName) {
        boolean isSys = false;
        PackageManager pm = Util.getApp().getPackageManager();
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            if (applicationInfo != null && (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
                isSys = true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            isSys = false;
        }
        return isSys;
    }

    /**
     * 获取userAgent
     *
     * @return 系统相关的信息
     */
    public static String getUserAgent(String appId) {
        StringBuffer userAgent = new StringBuffer();
        // ==============================================================
        // User-Agent
        // 格式：
        // 应用名称;应用版本;平台;OS版本;OS版本名称;厂商;机型;分辨率(宽*高);安装渠道;网络;
        // 示例：
        // HET;2.2.0;Android;4.2.2;N7100XXUEMI6BYTuifei;samsung;GT-I9300;480*800;360;WIFI;
        userAgent.append(appId);// 应用名称
        userAgent.append(SEMICOLON);
        userAgent.append(getVersionName()); // App版本
        userAgent.append(SEMICOLON);
        userAgent.append(SourceType);// 平台
        userAgent.append(SEMICOLON);
        return userAgent.toString();
    }

}
