package com.uowee.droid.util.layout;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by GuoWee on 2018/1/13.
 */

public class RootActivity extends ListActivity {

    private String[] mTitles = new String[]{
            AllLayoutActivity.class.getSimpleName(),
            LinearLayoutHelperActivity.class.getSimpleName(),
            GridLayoutHelperActivity.class.getSimpleName(),
            StaggeredGridLayoutHelperActivity.class.getSimpleName(),
            ColumnLayoutHelperActivity.class.getSimpleName(),
            SingleLayoutHelperActivity.class.getSimpleName(),
            StickyLayoutHelperActivity.class.getSimpleName(),
            FixLayoutHelperActivity.class.getSimpleName(),
            ScrollFixLayoutHelperActivity.class.getSimpleName(),
            FloatLayoutHelperActivity.class.getSimpleName(),
            OnePlusNLayoutHelperActivity.class.getSimpleName()

    };

    private Class[] mActivities = new Class[]{
            AllLayoutActivity.class,
            LinearLayoutHelperActivity.class,
            GridLayoutHelperActivity.class,
            StaggeredGridLayoutHelperActivity.class,
            ColumnLayoutHelperActivity.class,
            SingleLayoutHelperActivity.class,
            StickyLayoutHelperActivity.class,
            FixLayoutHelperActivity.class,
            ScrollFixLayoutHelperActivity.class,
            FloatLayoutHelperActivity.class,
            OnePlusNLayoutHelperActivity.class
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mTitles));
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        startActivity(new Intent(this, mActivities[position]));
    }
}
