package com.uowee.droid.system;

import com.uowee.utauta.system.AppUtil;
import com.uowee.utauta.system.CpuUtil;
import com.uowee.utauta.system.DeviceUtil;
import com.uowee.utauta.system.SDCardUtil;
import com.uowee.utauta.system.ScreenUtil;
import com.uowee.utauta.system.TelephoneUtil;

/**
 * Created by GuoWee on 2018/1/30.
 */

public class SystemPresenter implements SystemContract.Presenter {

    private SystemContract.View mView;

    public SystemPresenter(SystemContract.View view) {
        mView = view;
        //mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getDeviceInfo() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(DeviceUtil.getOSVersionDisplayName() + "\n");
        buffer.append(DeviceUtil.getOSVersionCode() + ", " + DeviceUtil.getAndroidID() + "\n");
        buffer.append(DeviceUtil.getBootTimeString() + "\n");
        buffer.append(DeviceUtil.getLocalIpAddress() + ", " + DeviceUtil.getMacAddress());
        mView.showMessage(buffer.toString());

    }

    @Override
    public void getScreenInfo() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("屏幕分辨率:" + ScreenUtil.getScreenWidth() + "*" + ScreenUtil.getDpi() + "\n");
        buffer.append("可用屏幕分辨率:" + ScreenUtil.getScreenWidth() + "*" + ScreenUtil.getScreenHeight() + "\n");
        buffer.append("状态栏高度:" + ScreenUtil.getStatusHeight() + "\n底部导航栏高度:" + ScreenUtil.getBottomStatusHeight() + "\n");
        buffer.append("是否竖屏:" + ScreenUtil.isScreenOriatationPortrait());

        mView.showMessage(buffer.toString());
    }

    @Override
    public void getCpuInfo() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(CpuUtil.getCpuInfo() + "\n");
        buffer.append(CpuUtil.getCpuName() + "\n");
        buffer.append(CpuUtil.getCoresNumbers());
        mView.showMessage(buffer.toString());
    }

    @Override
    public void getTelephoneInfo() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(TelephoneUtil.printTelephoneInfo());


        mView.showMessage(buffer.toString());
    }

    @Override
    public void getAppInfo() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(AppUtil.getVersionCode() + "," + AppUtil.getVersionName() + "\n");
        buffer.append(AppUtil.getAppName("com.uowee.droid.util") + "\n");
        buffer.append(AppUtil.getAppApk("com.uowee.droid.util") + "\n");
        buffer.append(AppUtil.getAppDate("com.uowee.droid.util") + "\n");
        buffer.append(AppUtil.getUserAgent("com.uowee.droid.util"));
        mView.showMessage(buffer.toString());
    }

    @Override
    public void getSDCardPath() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(SDCardUtil.getSDCardInfo() + "\n");
        buffer.append(SDCardUtil.getSDCardInfoBelow14());
        mView.showMessage(buffer.toString());
    }
}
