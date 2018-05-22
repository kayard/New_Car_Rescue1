package com.kuaijie.new_car_rescue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.kuaijie.new_car_rescue.R;
import com.kuaijie.new_car_rescue.utils.TopTitle;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kathe on 2018/4/25.
 * desc:接单
 */

public class TaskAcceptActivity extends BaseActivity {

    @Bind(R.id.btn_accept_order)
    public Button btn_accept_order;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_accept);
        ButterKnife.bind(this);
        settitle();
        onclick();
    }

    private void settitle() {
        TopTitle topTitle = new TopTitle(getWindow().getDecorView(),this);
        topTitle.tv_title.setText(getResources().getString(R.string.title_text_task_accept));
        topTitle.imgb_more.setVisibility(View.GONE);
        topTitle.back();
    }

    private void onclick(){
        btn_accept_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(1,intent);
                finish();
            }
        });
    }

}
