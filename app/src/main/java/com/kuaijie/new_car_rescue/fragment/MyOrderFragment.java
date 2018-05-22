package com.kuaijie.new_car_rescue.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kuaijie.new_car_rescue.R;

/**
 * Created by kathe on 2018/4/20.
 * desc:我的订单fragment
 */

public class MyOrderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_content,null);
        return view;
    }
}
