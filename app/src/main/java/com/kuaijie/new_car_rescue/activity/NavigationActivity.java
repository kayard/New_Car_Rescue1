package com.kuaijie.new_car_rescue.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.amap.api.maps.MapView;
import com.kuaijie.new_car_rescue.R;

/**
 * Created by kathe on 2018/4/24.
 * desc:导航
 */

public class NavigationActivity extends BaseActivity {

    public MapView mv_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        mv_text = findViewById(R.id.mv_test);
        mv_text.onCreate(savedInstanceState);
    }

}
