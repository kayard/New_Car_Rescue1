package com.kuaijie.new_car_rescue.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kuaijie.new_car_rescue.R;

/**
 * Created by kathe on 2018/4/20.
 * desc:工资查看
 */

public class PayrollInquiriesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payroll_inquiries,null);
        return view;
    }
}
