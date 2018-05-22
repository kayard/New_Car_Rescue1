package com.kuaijie.new_car_rescue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.kuaijie.new_car_rescue.R;
import com.kuaijie.new_car_rescue.utils.TopTitle;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kathe on 2018/4/27.
 * desc:到达救援地出发去往目的地(复用)
 */

public class StartOffActivity extends BaseActivity {

    @Bind(R.id.btn_input)
    public Button btn_input;
    @Bind(R.id.btn_next_step)
    public Button btn_next_step;

    private String where = "";
    private String type = "";
    private TopTitle topTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_off);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        where = intent.getStringExtra("where");
        settitle();
        setonclick();
    }

    private void settitle() {
        topTitle = new TopTitle(getWindow().getDecorView(),this);
        if (where.equals("rescueplace")){
            topTitle.tv_title.setText("救援地救援");
        } else if (where.equals("destination")) {
            topTitle.tv_title.setText("目的地救援");
            btn_input.setText("前往目的地");
        }
        topTitle.back();
        topTitle.imgb_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topTitle.showOptionWindow(view);
            }
        });
        topTitle.tophone();
    }

    private void setonclick(){
        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_next_step.setEnabled(true);
            }
        });

        btn_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("tuoche")) {    //类型为拖车的时候
                    if (where.equals("rescueplace")) {
                        Intent intent = new Intent(StartOffActivity.this, StartOffNextActivity.class);
                        startActivity(intent);
                    } else if (where.equals("destination")) {
                        startActivity(StartOffLastActivity.class, null);
                    }
                } else if (type.equals("dadian")){    //类型为搭电的时候
                    startActivity(StartOffDDActivity.class, null);
                }
            }
        });

    }
}
