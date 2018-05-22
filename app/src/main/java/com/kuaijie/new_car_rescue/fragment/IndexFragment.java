package com.kuaijie.new_car_rescue.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kuaijie.new_car_rescue.MyApplication.App;
import com.kuaijie.new_car_rescue.R;
import com.kuaijie.new_car_rescue.activity.TakeOrderActivity;
import com.kuaijie.new_car_rescue.activity.TaskAcceptActivity;
import com.kuaijie.new_car_rescue.activity.TaskDetailActivity;

/**
 * Created by kathe on 2018/4/20.
 * desc:
 */

public class IndexFragment extends Fragment {

    private ConstraintLayout cl_test;
    private ConstraintLayout cl_test1;
    private TextView tv_order_time;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_content,null);
        findview(view);
        return view;
    }

    private void findview(View view) {
        cl_test = view.findViewById(R.id.cl_test);
        cl_test1 = view.findViewById(R.id.cl_test1);
        tv_order_time = view.findViewById(R.id.tv_order_time);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settime();
        onclick();
    }

    private void settime() {
        CountDownTimer timer = new CountDownTimer(300*1000,1000) {
            @Override
            public void onTick(long t) {
                long l = t/1000;
                if (l > 0) {
                    tv_order_time.setText("");
                    tv_order_time.append((l / 3600 >= 10) ? l / 3600 + ":" : "0" + l / 3600 + ":");
                    tv_order_time.append((l / 60 % 60 >= 10) ? l / 60 % 60 + ":" : "0" + l / 60 % 60 + ":");
                    tv_order_time.append((l % 60 >= 10) ? l % 60 + "" : "0" + l % 60);
                }else {
                    tv_order_time.setText("00:00:00");
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void onclick() {
        cl_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TakeOrderActivity.class);
                intent.putExtra("type","tuoche");
                startActivity(intent);
            }
        });

        cl_test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TakeOrderActivity.class);
                intent.putExtra("type","dadian");
                startActivity(intent);
            }
        });
    }

}
