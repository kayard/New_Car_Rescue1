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
 * Created by kathe on 2018/4/27.
 * desc:填写费用
 */

public class CompletionFeedbackActivity extends BaseActivity {

    @Bind(R.id.btn_next_step)
    public Button btn_next_step;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completion_feedback);
        ButterKnife.bind(this);
        settitle();
        setonclick();
    }

    private void settitle() {
        final TopTitle topTitle =new TopTitle(getWindow().getDecorView(),this);
        topTitle.tv_title.setText(getResources().getString(R.string.title_text_completion_feedback));
        topTitle.back();
        topTitle.imgb_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topTitle.showOptionWindow(view);
            }
        });
        topTitle.tophone();
    }


    private void setonclick() {
        btn_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                setResult(4,intent);
                finish();
            }
        });
    }

}
