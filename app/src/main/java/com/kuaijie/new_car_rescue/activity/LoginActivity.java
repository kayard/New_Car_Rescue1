package com.kuaijie.new_car_rescue.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kuaijie.new_car_rescue.MyApplication.AppConstant;
import com.kuaijie.new_car_rescue.R;
import com.kuaijie.new_car_rescue.utils.L;
import com.kuaijie.new_car_rescue.utils.TopTitle;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kathe on 2018/4/11.
 */

public class LoginActivity extends BaseActivity {

    @Bind(R.id.btn_login)
    public Button btn_login;
    @Bind(R.id.et_account)
    public EditText et_account;
    @Bind(R.id.et_password)
    public EditText et_password;

    private String account;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        settitle();
        setonclick();
    }

    private void settitle() {
        TopTitle topTitle = new TopTitle(getWindow().getDecorView(),this);
        topTitle.tv_title.setText(getResources().getString(R.string.title_text_login));
        topTitle.lin_return.setVisibility(View.GONE);
        topTitle.imgb_more.setVisibility(View.GONE);
    }

    private void  setonclick(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account = et_account.getText().toString();
                password = et_password.getText().toString();
                if (!account.equals("") && !password.equals("")){
                    OkGo.<String>get(AppConstant.LOGIN)
                            .tag(this)
                            .params("method","methoda")
                            .params("userName",account)
                            .params("passWord",password)
                            .cacheMode(CacheMode.NO_CACHE)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    startActivity(MainActivity.class,null);
                                    ShowToast(response.body());
                                    finish();
                                }

                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                    ShowToast("/(ㄒoㄒ)/~~");
                                }
                            });
                } else {
                    ShowToast("请填写完整的账号密码！");
                }


            }
        });
    }

}
