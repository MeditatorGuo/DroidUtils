package com.uowee.droid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uowee.droid.adapter.GridViewAdapter;
import com.uowee.droid.adapter.LinearAdapter;
import com.uowee.droid.model.GridItem;
import com.uowee.droid.util.R;
import com.uowee.tangram.VirtualLayoutManager;
import com.uowee.tangram.adapter.DelegateAdapter;
import com.uowee.tangram.helper.GridLayoutHelper;
import com.uowee.tangram.helper.LinearLayoutHelper;
import com.uowee.utauta.system.DeviceUtil;
import com.uowee.utauta.system.ScreenUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by GuoWee on 2018/1/18.
 */

public class MainActivity extends AppCompatActivity {
    private static List<GridItem> items = null;

    static {
        items = new ArrayList<>();
        items.add(new GridItem("Screen", R.mipmap.genius));
        items.add(new GridItem("Device", R.mipmap.genius));
        items.add(new GridItem("Screen", R.mipmap.genius));
        items.add(new GridItem("Screen", R.mipmap.genius));
        items.add(new GridItem("Screen", R.mipmap.genius));
    }

    private Context mContext;
    private RecyclerView mRecyclerView;
    private DelegateAdapter delegateAdapter;
    final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mRecyclerView = findViewById(R.id.main_view);
        initView();
    }

    public void initView() {
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        adapters.add(initLinearLayout(this));
        adapters.add(initGridLayout(this));


        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        delegateAdapter = new DelegateAdapter(manager);

        delegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(delegateAdapter);

    }

    public LinearAdapter initLinearLayout(Context context) {
        LinearLayoutHelper helper = new LinearLayoutHelper();
        LinearAdapter adapter = new LinearAdapter(context, helper, "System");
        return adapter;
    }

    public GridViewAdapter initGridLayout(final Context context) {
        GridLayoutHelper helper = new GridLayoutHelper(3);
        helper.setAutoExpand(false);

        GridViewAdapter adapter = new GridViewAdapter(context, helper, items);
        adapter.setOnGridViewItemListener(gridViewItemListener);
        return adapter;
    }

    private GridViewAdapter.OnGridViewItemListener gridViewItemListener = new GridViewAdapter.OnGridViewItemListener() {
        @Override
        public void onItemClick(View view, int position) {
            switch (position) {
                case 1:
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage(getScreenInfo());
                    builder.create().show();
                    break;
                case 2:
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(mContext);
                    builder1.setMessage(getDeviceInfo());
                    builder1.create().show();
                    break;
                default:
                    break;
            }


        }

        @Override
        public void onItemLongClick(View view, int position) {

        }
    };


    private String getScreenInfo() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(ScreenUtil.getScreenWidth() + "*" + ScreenUtil.getDpi() + "\n");
        buffer.append(ScreenUtil.getStatusHeight() + "+" + ScreenUtil.getBottomStatusHeight() + "\n");
        buffer.append(ScreenUtil.getTitleHeight(this) + "\n");
        buffer.append(ScreenUtil.isScreenOriatationPortrait());
        return buffer.toString();
    }

    private String getDeviceInfo() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(DeviceUtil.getBootTimeString() + "\n");
        buffer.append(DeviceUtil.getOSVersionCode() + "\n");
        buffer.append(DeviceUtil.getOSVersionName() + "\n");
        buffer.append(DeviceUtil.getOSVersionDisplayName() + "\n");
        buffer.append(DeviceUtil.getAndroidID() + "\n");
        buffer.append(DeviceUtil.getModel() + ", " + DeviceUtil.getManufacturer() + "\n");
        buffer.append(DeviceUtil.isDeviceRooted() + ", " + DeviceUtil.getBrand() + ", " + DeviceUtil.getMacAddress());


        return buffer.toString();
    }


}
