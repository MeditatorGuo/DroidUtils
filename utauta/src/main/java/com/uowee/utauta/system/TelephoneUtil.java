package com.uowee.utauta.system;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.uowee.utauta.Util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GuoWee on 2018/1/12.
 */

public final class TelephoneUtil {
    private TelephoneUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    @SuppressLint({"HardwareIds", "MissingPermission"})
    public static String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) Util.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI = telephonyManager.getDeviceId();
        return IMEI;
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    public static String getIMSI() {
        TelephonyManager telephonyManager = (TelephonyManager) Util.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        String IMSI = telephonyManager.getSubscriberId();
        return IMSI;
    }

    /**
     * Print telephone info.
     */
    @SuppressLint("MissingPermission")
    public static String printTelephoneInfo() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        StringBuilder sb = new StringBuilder();
        sb.append("手机信息  ").append(time).append(" ______________");
        TelephonyManager tm = (TelephonyManager) Util.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        String IMSI = tm.getSubscriberId();
        //IMSI前面三位460是国家号码，其次的两位是运营商代号，00、02是中国移动，01是联通，03是电信。
        String providerName = null;
        if (IMSI != null) {
            if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
                providerName = "中国移动";
            } else if (IMSI.startsWith("46001")) {
                providerName = "中国联通";
            } else if (IMSI.startsWith("46003")) {
                providerName = "中国电信";
            }
        }
        sb.append(providerName).append("  手机号：").append(tm.getLine1Number()).append(" IMSI是：").append(IMSI);
        sb.append("\nDeviceID(IMEI)       :").append(tm.getDeviceId());
        sb.append("\nDeviceSoftwareVersion:").append(tm.getDeviceSoftwareVersion());
        sb.append("\ngetLine1Number       :").append(tm.getLine1Number());
        sb.append("\nNetworkCountryIso    :").append(tm.getNetworkCountryIso());
        sb.append("\nNetworkOperator      :").append(tm.getNetworkOperator());
        sb.append("\nNetworkOperatorName  :").append(tm.getNetworkOperatorName());
        sb.append("\nNetworkType          :").append(tm.getNetworkType());
        sb.append("\nPhoneType            :").append(tm.getPhoneType());
        sb.append("\nSimCountryIso        :").append(tm.getSimCountryIso());
        sb.append("\nSimOperator          :").append(tm.getSimOperator());
        sb.append("\nSimOperatorName      :").append(tm.getSimOperatorName());
        sb.append("\nSimSerialNumber      :").append(tm.getSimSerialNumber());
        sb.append("\ngetSimState          :").append(tm.getSimState());
        sb.append("\nSubscriberId         :").append(tm.getSubscriberId());
        sb.append("\nVoiceMailNumber      :").append(tm.getVoiceMailNumber());

        return sb.toString();
    }


    public static class TeleInfo {
        public String imsi_1;
        public String imsi_2;
        public String imei_1;
        public String imei_2;
        public int phoneType_1;
        public int phoneType_2;

        @Override
        public String toString() {
            return "TeleInfo{" +
                    "imsi_1='" + imsi_1 + '\'' +
                    ", imsi_2='" + imsi_2 + '\'' +
                    ", imei_1='" + imei_1 + '\'' +
                    ", imei_2='" + imei_2 + '\'' +
                    ", phoneType_1=" + phoneType_1 +
                    ", phoneType_2=" + phoneType_2 +
                    '}';
        }
    }

    /**
     * MTK Phone.
     * <p>
     * 获取 MTK 神机的双卡 IMSI、IMSI 信息
     */
    public static TeleInfo getMtkTeleInfo() {
        TeleInfo teleInfo = new TeleInfo();
        try {
            Class<?> phone = Class.forName("com.android.internal.telephony.Phone");

            Field fields1 = phone.getField("GEMINI_SIM_1");
            fields1.setAccessible(true);
            int simId_1 = (Integer) fields1.get(null);

            Field fields2 = phone.getField("GEMINI_SIM_2");
            fields2.setAccessible(true);
            int simId_2 = (Integer) fields2.get(null);

            TelephonyManager tm = (TelephonyManager) Util.getApp().getSystemService(Context.TELEPHONY_SERVICE);
            Method getSubscriberIdGemini = TelephonyManager.class.getDeclaredMethod("getSubscriberIdGemini", int.class);
            String imsi_1 = (String) getSubscriberIdGemini.invoke(tm, simId_1);
            String imsi_2 = (String) getSubscriberIdGemini.invoke(tm, simId_2);
            teleInfo.imsi_1 = imsi_1;
            teleInfo.imsi_2 = imsi_2;

            Method getDeviceIdGemini = TelephonyManager.class.getDeclaredMethod("getDeviceIdGemini", int.class);
            String imei_1 = (String) getDeviceIdGemini.invoke(tm, simId_1);
            String imei_2 = (String) getDeviceIdGemini.invoke(tm, simId_2);

            teleInfo.imei_1 = imei_1;
            teleInfo.imei_2 = imei_2;

            Method getPhoneTypeGemini = TelephonyManager.class.getDeclaredMethod("getPhoneTypeGemini", int.class);
            int phoneType_1 = (Integer) getPhoneTypeGemini.invoke(tm, simId_1);
            int phoneType_2 = (Integer) getPhoneTypeGemini.invoke(tm, simId_2);
            teleInfo.phoneType_1 = phoneType_1;
            teleInfo.phoneType_2 = phoneType_2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teleInfo;
    }

    /**
     * MTK Phone.
     * <p>
     * 获取 MTK 神机的双卡 IMSI、IMSI 信息
     */
    @SuppressLint("MissingPermission")
    public static TeleInfo getMtkTeleInfo2() {
        TeleInfo teleInfo = new TeleInfo();
        try {
            TelephonyManager tm = (TelephonyManager) Util.getApp().getSystemService(Context.TELEPHONY_SERVICE);
            Class<?> phone = Class.forName("com.android.internal.telephony.Phone");
            Field fields1 = phone.getField("GEMINI_SIM_1");
            fields1.setAccessible(true);
            int simId_1 = (Integer) fields1.get(null);
            Field fields2 = phone.getField("GEMINI_SIM_2");
            fields2.setAccessible(true);
            int simId_2 = (Integer) fields2.get(null);

            Method getDefault = TelephonyManager.class.getMethod("getDefault", int.class);
            TelephonyManager tm1 = (TelephonyManager) getDefault.invoke(tm, simId_1);
            TelephonyManager tm2 = (TelephonyManager) getDefault.invoke(tm, simId_2);

            String imsi_1 = tm1.getSubscriberId();
            String imsi_2 = tm2.getSubscriberId();
            teleInfo.imsi_1 = imsi_1;
            teleInfo.imsi_2 = imsi_2;

            String imei_1 = tm1.getDeviceId();
            String imei_2 = tm2.getDeviceId();
            teleInfo.imei_1 = imei_1;
            teleInfo.imei_2 = imei_2;

            int phoneType_1 = tm1.getPhoneType();
            int phoneType_2 = tm2.getPhoneType();
            teleInfo.phoneType_1 = phoneType_1;
            teleInfo.phoneType_2 = phoneType_2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teleInfo;
    }

    /**
     * Qualcomm Phone.
     * 获取 高通 神机的双卡 IMSI、IMSI 信息
     */
    public static TeleInfo getQualcommTeleInfo() {
        TeleInfo teleInfo = new TeleInfo();
        try {
            TelephonyManager tm = (TelephonyManager) Util.getApp().getSystemService(Context.TELEPHONY_SERVICE);
            Class<?> simTMclass = Class.forName("android.telephony.MSimTelephonyManager");
            @SuppressLint("WrongConstant")
            Object sim = Util.getApp().getSystemService("phone_msim");
            int simId_1 = 0;
            int simId_2 = 1;

            Method getSubscriberId = simTMclass.getMethod("getSubscriberId", int.class);
            String imsi_1 = (String) getSubscriberId.invoke(sim, simId_1);
            String imsi_2 = (String) getSubscriberId.invoke(sim, simId_2);
            teleInfo.imsi_1 = imsi_1;
            teleInfo.imsi_2 = imsi_2;

            Method getDeviceId = simTMclass.getMethod("getDeviceId", int.class);
            String imei_1 = (String) getDeviceId.invoke(sim, simId_1);
            String imei_2 = (String) getDeviceId.invoke(sim, simId_2);
            teleInfo.imei_1 = imei_1;
            teleInfo.imei_2 = imei_2;

            Method getDataState = simTMclass.getMethod("getDataState");
            int phoneType_1 = tm.getDataState();
            int phoneType_2 = (Integer) getDataState.invoke(sim);
            teleInfo.phoneType_1 = phoneType_1;
            teleInfo.phoneType_2 = phoneType_2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teleInfo;
    }

    /**
     * Spreadtrum Phone.
     * <p>
     * 获取 展讯 神机的双卡 IMSI、IMSI 信息
     */
    @SuppressLint("MissingPermission")
    public static TeleInfo getSpreadtrumTeleInfo() {
        TeleInfo teleInfo = new TeleInfo();
        try {
            TelephonyManager tm1 = (TelephonyManager) Util.getApp().getSystemService(Context.TELEPHONY_SERVICE);
            String imsi_1 = tm1.getSubscriberId();
            String imei_1 = tm1.getDeviceId();
            int phoneType_1 = tm1.getPhoneType();
            teleInfo.imsi_1 = imsi_1;
            teleInfo.imei_1 = imei_1;
            teleInfo.phoneType_1 = phoneType_1;

            Class<?> phoneFactory = Class.forName("com.android.internal.telephony.PhoneFactory");
            Method getServiceName = phoneFactory.getMethod("getServiceName", String.class, int.class);
            getServiceName.setAccessible(true);
            String spreadTmService = (String) getServiceName.invoke(phoneFactory, Context.TELEPHONY_SERVICE, 1);

            TelephonyManager tm2 = (TelephonyManager) Util.getApp().getSystemService(spreadTmService);
            String imsi_2 = tm2.getSubscriberId();
            String imei_2 = tm2.getDeviceId();
            int phoneType_2 = tm2.getPhoneType();
            teleInfo.imsi_2 = imsi_2;
            teleInfo.imei_2 = imei_2;
            teleInfo.phoneType_2 = phoneType_2;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return teleInfo;
    }
}
