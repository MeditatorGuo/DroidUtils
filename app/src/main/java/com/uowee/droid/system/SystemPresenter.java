package com.uowee.droid.system;

import com.uowee.utauta.system.DeviceUtil;
import com.uowee.utauta.system.ScreenUtil;

/**
 * Created by GuoWee on 2018/1/30.
 */

public class SystemPresenter implements SystemContract.Presenter {

    private SystemContract.View mView;

    public SystemPresenter(SystemContract.View view) {
        mView = view;
        mView.setPresenter(this);
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
}
