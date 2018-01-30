package com.uowee.droid;

/**
 * Created by GuoWee on 2018/1/30.
 */

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
