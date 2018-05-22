package com.kuaijie.new_car_rescue.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kuaijie.new_car_rescue.MyApplication.App;
import com.kuaijie.new_car_rescue.R;
import com.kuaijie.new_car_rescue.fragment.FeeRegistrationFragment;
import com.kuaijie.new_car_rescue.fragment.IndexFragment;
import com.kuaijie.new_car_rescue.fragment.MyOrderFragment;
import com.kuaijie.new_car_rescue.fragment.PayrollInquiriesFragment;
import com.kuaijie.new_car_rescue.fragment.RescueAssistanceFragment;
import com.kuaijie.new_car_rescue.fragment.SeparationApplicationFragment;
import com.kuaijie.new_car_rescue.fragment.ShiftLeaveFragment;
import com.kuaijie.new_car_rescue.utils.TopTitle;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    @Bind(R.id.drawer_layout)
    public DrawerLayout drawer_layout;
    @Bind(R.id.nav_view)
    public NavigationView nav_view;
    @Bind(R.id.ll_logoff)
    public LinearLayout ll_logoff;


    private View headview;

    private Fragment[] fragments;

    // 默认的首页
    private int defaultPage = 0;
    // 被选中的页面
    private int indexPage;
    // 当前的页面
    private int currentTabIndex;

    private FragmentManager fragmentManager;

    private TextView topTitle;   //标题
    private LinearLayout lin_menu;    //左上菜单
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //startActivity(LoginActivity.class,null);
        initView();
        setDefault();
        setListener();
    }

    private void initView() {
        fragments = new Fragment[]{new IndexFragment(),new MyOrderFragment(),new RescueAssistanceFragment()
                ,new ShiftLeaveFragment(),new SeparationApplicationFragment(),new FeeRegistrationFragment(),new PayrollInquiriesFragment()};
        fragmentManager = getFragmentManager();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer_layout,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();
        headview = nav_view.getHeaderView(0);
    }

    private void setDefault() {
        lin_menu = getWindow().getDecorView().findViewById(R.id.lin_menu);
        topTitle = getWindow().getDecorView().findViewById(R.id.tv_title);
        drawer = findViewById(R.id.drawer_layout);
        nav_view.getMenu().getItem(defaultPage).setChecked(true);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content,fragments[defaultPage]);
        transaction.commit();
    }

    private void setListener() {
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_index:
                        topTitle.setText(getResources().getString(R.string.index));
                        indexPage = 0;
                        break;
                    case R.id.menu_my_order:
                        topTitle.setText(getResources().getString(R.string.my_order));
                        indexPage = 1;
                        break;
                    case R.id.menu_rescue_assistance:
                        topTitle.setText(getResources().getString(R.string.rescue_assistance));
                        indexPage = 2;
                        break;
                    case R.id.menu_shift_leave:
                        topTitle.setText(getResources().getString(R.string.shift_leave));
                        indexPage = 3;
                        break;
                    case R.id.menu_separation_application:
                        topTitle.setText(getResources().getString(R.string.separation_application));
                        indexPage = 4;
                        break;
                    case R.id.menu_fee_registration:
                        topTitle.setText(getResources().getString(R.string.fee_registration));
                        indexPage = 5;
                        break;
                    case R.id.menu_payroll_inquiries:
                        topTitle.setText(getResources().getString(R.string.payroll_inquiries));
                        indexPage = 6;
                        break;
                    default:
                        indexPage = -1;
                        break;
                }
                changePage();
                drawer_layout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ll_logoff.setOnClickListener(new View.OnClickListener() {         //退出
            @Override
            public void onClick(View view) {
                startActivity(LoginActivity.class,null);
                finish();
            }
        });

        lin_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

    }

    private void changePage() {
        if (currentTabIndex != indexPage) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.hide(fragments[currentTabIndex]);
            if (!fragments[indexPage].isAdded())
                transaction.add(R.id.content, fragments[indexPage]);
            transaction.show(fragments[indexPage]).commit();
        }
        currentTabIndex = indexPage;
    }


    @Override
    public void onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public int getIndexPage(){
        return indexPage;
    }


}
