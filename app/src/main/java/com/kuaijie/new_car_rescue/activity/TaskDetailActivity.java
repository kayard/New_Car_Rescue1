package com.kuaijie.new_car_rescue.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kuaijie.new_car_rescue.R;
import com.kuaijie.new_car_rescue.utils.TopTitle;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kathe on 2018/4/23.
 * desc:任务细节
 */

public class TaskDetailActivity extends BaseActivity {

    @Bind(R.id.btn_contac_request)
    public Button btn_contac_request;
    @Bind(R.id.ll_rescue_location)
    public LinearLayout ll_rescue_location;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        ButterKnife.bind(this);
        settitle();
        onclick();
    }

    private void settitle() {
        TopTitle topTitle = new TopTitle(getWindow().getDecorView(),this);
        topTitle.tv_title.setText(getResources().getString(R.string.title_text_task_detail));
        topTitle.imgb_more.setVisibility(View.GONE);
        topTitle.back();
    }

    private void onclick() {
        ll_rescue_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(SiteMapActivity.class, null);
            }
        });


        btn_contac_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent();
               setResult(2,intent);
               finish();
            }
        });
    }


    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void diallPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                    1);
        }
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode,grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if(requestCode == 1){
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED){
                ShowToast("权限获取失败！");
            }
        }
    }
}
