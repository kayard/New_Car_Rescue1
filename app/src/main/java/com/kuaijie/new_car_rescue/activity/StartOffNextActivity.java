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
 * Created by kathe on 2018/5/3.
 * desc:将出发页面之后的功能集合
 */

public class StartOffNextActivity extends BaseActivity{

    @Bind(R.id.btn_guolu)
    public Button btn_guolu;
    @Bind(R.id.btn_zhengche)
    public Button btn_zhengche;
    @Bind(R.id.btn_zhijian)
    public Button btn_zhijian;
    @Bind(R.id.btn_beiche)
    public Button btn_beiche;
    @Bind(R.id.btn_next_step)
    public Button btn_next_step;

    private boolean t1=false,t2=false,t3=false,t4=false;    //判断能否进行下一步
    private String next = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_off_next);
        ButterKnife.bind(this);
        settitle();
        setonclick();
    }

    private void settitle() {
        Intent intent = getIntent();
        next = intent.getStringExtra("next");
        final TopTitle topTitle = new TopTitle(getWindow().getDecorView(),this);
        topTitle.tv_title.setText("到达救援地");
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
        btn_guolu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartOffNextActivity.this,RoadTollActivity.class);
                startActivityForResult(intent,1);
            }
        });

        btn_zhengche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartOffNextActivity.this,TakePhotoOneActivity.class);
                intent.putExtra("type","zhengche");
                startActivityForResult(intent,2);
            }
        });

        btn_zhijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartOffNextActivity.this,CarCheckActivity.class);
                startActivityForResult(intent,3);
                t3=true;
            }
        });

        btn_beiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartOffNextActivity.this,TakePhotoOneActivity.class);
                intent.putExtra("type","beiche");
                startActivityForResult(intent,4);
            }
        });

        btn_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartOffNextActivity.this,StartOffActivity.class);
                intent.putExtra("where","destination");
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
        } else if(requestCode==3 && resultCode==3){
            t3=true;
        } else if(requestCode==4 && resultCode==4){
            t4=true;
        }

        if (t1&&t2&&t3&&t4){
            btn_next_step.setEnabled(true);
        }

        if (next.equals("yes")){
            if (t1&&t2&&t4){
                btn_next_step.setEnabled(true);
            }
        }

    }
}
