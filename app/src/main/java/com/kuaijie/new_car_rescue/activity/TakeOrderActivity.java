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
 * Created by kathe on 2018/5/6.
 * desc:接单合并界面
 */

public class TakeOrderActivity extends BaseActivity {

    @Bind(R.id.btn_remark)
    public Button btn_remark;
    @Bind(R.id.btn_detail)
    public Button btn_detail;
    @Bind(R.id.btn_next_step)
    public Button btn_next_step;

    private boolean t1 = false,t2 = false;

    private String type = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_order);
        ButterKnife.bind(this);
        settitle();
        setonclick();
    }

    private void settitle() {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        TopTitle topTitle = new TopTitle(getWindow().getDecorView(),this);
        topTitle.tv_title.setText("接单");
        topTitle.imgb_more.setVisibility(View.GONE);
        topTitle.back();
    }


    private void setonclick() {
        btn_remark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TakeOrderActivity.this,TaskAcceptActivity.class);
                startActivityForResult(intent,1);
            }
        });

        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TakeOrderActivity.this,TaskDetailActivity.class);
                startActivityForResult(intent,2);
            }
        });

        btn_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TakeOrderActivity.this,StartOffActivity.class);
                intent.putExtra("type",type);
                intent.putExtra("where","rescueplace");
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //判断能否进行下一步
        if (requestCode==1 && resultCode==1){
            t1=true;
        } else if (requestCode==2 && resultCode==2){
            t2=true;
        }

        if (t1&&t2){
            btn_next_step.setEnabled(true);
        }

    }


}
