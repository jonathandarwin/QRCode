package com.example.qrcode.app.home;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.qrcode.R;
import com.example.qrcode.app.scan.ScanActivity;
import com.example.qrcode.app.showqr.ShowQrDialog;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.HomeActivityBinding;
import com.example.qrcode.model.BaseHistory;
import com.example.qrcode.model.Transaction;
import com.example.qrcode.util.MoneyUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity<HomeActivityBinding, HomeViewModel>
            implements View.OnClickListener{

    HomeAdapter adapter;
    List<BaseHistory> listHistory;

    public HomeActivity(){
        super(HomeViewModel.class, R.layout.home_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setListener() {
        getBinding().menuScan.setOnClickListener(this);
        getBinding().menuShowQr.setOnClickListener(this);
        getBinding().menuTopup.setOnClickListener(this);
    }

    @Override
    protected void setAdapter() {
        listHistory = new ArrayList<>();
        adapter = new HomeAdapter(this, listHistory);
        getBinding().recyclerView.setHasFixedSize(true);
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getBinding().recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().menuScan)){
            gotoIntent(ScanActivity.class, null, false);
        }
        else if(v.equals(getBinding().menuShowQr)){
            gotoIntent(ShowQrDialog.class, null, false);
        } else if (v.equals(getBinding().menuTopup)){

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDataUser();
        updateBalance();
        getListHistory();
    }

    private void updateDataUser(){
        getViewModel().updateUserData(loadUserData().getPhone()).observe(this, user -> {
            if(!user.equals(loadUserData())){
                saveUserData(user);
                updateBalance();
            }
        });
    }

    private void updateBalance(){
        getBinding().setBalance(MoneyUtil.convertMoney(loadUserData().getBalance()));
    }

    private void getListHistory(){
        getBinding().noData.setVisibility(View.GONE);
        getBinding().loader.setVisibility(View.VISIBLE);
        listHistory.clear();
        getViewModel().getHistory(loadUserData().getPhone()).observe(this, new Observer<List<Transaction>>() {
            @Override
            public void onChanged(@Nullable List<Transaction> list) {
                getBinding().loader.setVisibility(View.GONE);
                if(list.size() == 0){
                    getBinding().noData.setVisibility(View.VISIBLE);
                }
                listHistory.clear();
                listHistory.addAll(getViewModel().processHistory(list));
                adapter.notifyDataSetChanged();
            }
        });
    }
}
