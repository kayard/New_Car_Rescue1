package com.kuaijie.new_car_rescue.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.kuaijie.new_car_rescue.MyApplication.App;
import com.kuaijie.new_car_rescue.R;


/**
 * Created by kathe on 2018/4/11.
 */

public class TopTitle {
    public LinearLayout lin_return;
    public TextView tv_title;
    public ImageButton imgb_more;

    public PopupWindow optionWindow;
    public View optionView;
    public Activity activity;

    public LinearLayout lin_phone;

    public TopTitle(View v, Activity activity){
        lin_return = v.findViewById(R.id.lin_return);
        imgb_more = v.findViewById(R.id.imgb_more);
        tv_title = v.findViewById(R.id.tv_title);
        this.activity = activity;
        initOptionWindow();
    }

    public void back(){
        lin_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }

    public void tophone(){
        lin_phone = optionView.findViewById(R.id.lin_phone);
        lin_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =new AlertDialog.Builder(activity);
                builder.setTitle("提示")
                        .setMessage("您拨打的电话已关机！")
                        .setCancelable(true)
                        .show();
            }
        });
    }

    private void initOptionWindow(){
        optionView = LayoutInflater.from(activity).inflate(R.layout.dialog_pop_menu, null);

        optionWindow = new PopupWindow(optionView, dp2px(150), LinearLayout.LayoutParams.WRAP_CONTENT,true);
        optionWindow.setFocusable(true);
        optionWindow.setTouchable(true);
        optionWindow.setOutsideTouchable(true);
        //optionWindow.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), (Bitmap) null));  //背景透明
        optionWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        optionWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
        optionWindow.update();
    }


    public void showOptionWindow(View view)
    {
        if(!optionWindow.isShowing()){
            optionWindow.showAsDropDown(view, 0, 0);
        }else{
            optionWindow.dismiss();
        }
    }

    /**
     * dip-->px
     */
    public int dp2px(int dip) {
        // px/dip = density;
        // density = dpi/160
        // 320*480 density = 1 1px = 1dp
        // 1280*720 density = 2 2px = 1dp
        float density = activity.getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }

}
