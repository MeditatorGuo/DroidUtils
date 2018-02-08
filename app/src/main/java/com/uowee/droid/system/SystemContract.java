package com.uowee.droid.system;

import com.uowee.droid.BasePresenter;
import com.uowee.droid.BaseView;

/**
 * Created by GuoWee on 2018/1/30.
 */

public interface SystemContract {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);
    }


    interface Presenter extends BasePresenter {
        void getDeviceInfo();

        void getScreenInfo();

        void getCpuInfo();
    }
}
