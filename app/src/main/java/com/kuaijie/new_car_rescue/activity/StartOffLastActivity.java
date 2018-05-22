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
 * Created by kathe on 2018/5/4.
 * desc:到达目的地功能总集合
 */

public class StartOffLastActivity extends BaseActivity {

    @Bind(R.id.btn_daoda)
    public Button btn_daoda;
    @Bind(R.id.btn_guolu)
    public Button btn_guolu;
    @Bind(R.id.btn_xieche)
    public Button btn_xieche;
    @Bind(R.id.btn_feed)
    public Button btn_feed;
    @Bind(R.id.btn_elec)
    public Button btn_elec;
    @Bind(R.id.btn_next_step)
    public Button btn_next_step;

    private boolean t1=false,t2=false,t3=false,t4=false;    //判断能否进行下一步

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_off_last);
        ButterKnife.bind(this);
        settitle();
        setonclick();
    }

    private void settitle() {
        final TopTitle topTitle = new TopTitle(getWindow().getDecorView(),this);
        topTitle.tv_title.setText("到达目的地");
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
        btn_daoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartOffLastActivity.this,TakePhotoOneActivity.class);
                intent.putExtra("type","daoda");
                startActivityForResult(intent,2);
            }
        });

        btn_guolu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartOffLastActivity.this,RoadTollActivity.class);
                startActivityForResult(intent,1);
            }
        });

        btn_xieche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartOffLastActivity.this,TakePhotoOneActivity.class);
                intent.putExtra("type","xieche");
                startActivityForResult(intent,3);
            }
        });

        btn_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartOffLastActivity.this,CompletionFeedbackActivity.class);
                startActivityForResult(intent,4);
            }
        });

        btn_elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowToast("电子工单暂定！");
            }
        });

        btn_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(CompleteOrderActivity.class,null);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 1){
            t1=true;
        }else if(requestCode == 2 && resultCode == 2){
            t2=true;
        }else if(requestCode == 3 && resultCode == 3){
            t3=true;
        }else if(requestCode == 4 && resultCode == 4){
            t4=true;
        }

        if (t1&&t2&&t3&&t4){
            btn_next_step.setEnabled(true);
        }

    }
}
