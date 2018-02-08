package com.uowee.droid.system;

import com.uowee.utauta.system.CpuUtil;
import com.uowee.utauta.system.DeviceUtil;
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
        String name = DeviceUtil.getOSVersionDisplayName();
        mView.showMessage(name);

    }

    @Override
    public void getScreenInfo() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(ScreenUtil.getScreenWidth() + "*" + ScreenUtil.getDpi() + "\n");
        buffer.append(ScreenUtil.getStatusHeight() + ", " + ScreenUtil.getBottomStatusHeight() + "\n");
        buffer.append(ScreenUtil.isScreenOriatationPortrait());

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
        String message = TelephoneUtil.getIMEI();
        mView.showMessage(message);
    }
}
