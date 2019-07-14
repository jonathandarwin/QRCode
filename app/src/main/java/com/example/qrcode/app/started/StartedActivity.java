package com.example.qrcode.app.started;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.qrcode.R;
import com.example.qrcode.app.register.RegisterActivity;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.StartedActivityBinding;

import java.util.ArrayList;
import java.util.List;

public class StartedActivity extends BaseActivity<StartedActivityBinding, StartedViewModel> {

    private StartedAdapter adapter;
    List<Integer> listLayout;

    public StartedActivity(){
        super(StartedViewModel.class, R.layout.started_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setAdapter() {
        setLayout();
        adapter = new StartedAdapter(this, listLayout);
        getBinding().recyclerView.setAdapter(adapter);
        getBinding().recyclerView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                switch (i){
                    case 0:
                        activeCircle(true, false, false);
                        break;
                    case 1:
                        activeCircle(false, true, false);
                        break;
                    case 2:
                        activeCircle(false, false, true);
                        break;
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setLayout(){
        listLayout = new ArrayList<>();
        listLayout.add(R.layout.started_slider_1);
        listLayout.add(R.layout.started_slider_2);
        listLayout.add(R.layout.started_slider_3);
    }

    private void activeCircle(boolean dot1, boolean dot2, boolean dot3){
        getBinding().setDot1(getResources().getColor(dot1 ? R.color.colorWhite : R.color.colorDot));
        getBinding().setDot2(getResources().getColor(dot2 ? R.color.colorWhite : R.color.colorDot));
        getBinding().setDot3(getResources().getColor(dot3 ? R.color.colorWhite : R.color.colorDot));
    }

    public void onButtonGoListener(){
        gotoIntent(RegisterActivity.class, null, true);
    }
}