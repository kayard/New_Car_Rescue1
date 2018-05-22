package com.kuaijie.new_car_rescue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.kuaijie.new_car_rescue.R;
import com.kuaijie.new_car_rescue.utils.TopTitle;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kathe on 2018/5/8.
 * desc:完工有无路费的页面
 */

public class CompleteOrderActivity extends BaseActivity {

    @Bind(R.id.iv_finish)
    public ImageView iv_finish;
    @Bind(R.id.iv_return)
    public ImageView iv_return;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_order);
        ButterKnife.bind(this);
        settitle();
        setonclick();
    }

    private void settitle() {
        TopTitle topTitle = new TopTitle(getWindow().getDecorView(),this);
        topTitle.tv_title.setText(getResources().getString(R.string.complete_order));
        topTitle.back();
        topTitle.imgb_more.setVisibility(View.GONE);
    }

    private void setonclick() {
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowToast("订单已完成！");
            }
        });

        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompleteOrderActivity.this, RoadTollActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1 && resultCode==1){
            ShowToast("订单已完成！");
        }
    }
}
