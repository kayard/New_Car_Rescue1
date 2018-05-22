package com.kuaijie.new_car_rescue.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.amap.api.maps.MapView;
import com.kuaijie.new_car_rescue.R;
import com.kuaijie.new_car_rescue.utils.TopTitle;

/**
 * Created by kathe on 2018/4/23.
 * desc:导航
 */

public class SiteMapActivity extends BaseActivity {

    public MapView mv_map;
    public Button btn_navigation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_map);
        mv_map = findViewById(R.id.mv_map);
        mv_map.onCreate(savedInstanceState);
        findview();
        initview();
        onclick();
    }

    private void findview() {
        btn_navigation = findViewById(R.id.btn_navigation);
    }

    private void initview() {
        TopTitle topTitle = new TopTitle(getWindow().getDecorView(),this);
        topTitle.tv_title.setText("救援地");
        topTitle.back();
    }

    private void onclick(){
        btn_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(NavigationActivity.class,null);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mv_map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mv_map.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mv_map.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mv_map.onSaveInstanceState(outState);
    }
}
