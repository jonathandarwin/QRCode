package com.example.qrcode.app.topup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.qrcode.R;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.TopupActivityBinding;
import com.example.qrcode.model.Transaction;
import com.example.qrcode.model.User;
import com.example.qrcode.util.CalendarUtil;
import com.example.qrcode.util.MoneyUtil;

public class TopUpActivity extends BaseActivity<TopupActivityBinding, TopUpViewModel>
            implements View.OnClickListener{

    public TopUpActivity(){
        super(TopUpViewModel.class, R.layout.topup_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBinding().setBalance(MoneyUtil.convertMoney(loadUserData().getBalance()));
        getViewModel().setUpdateTopup(true);
        getBinding().setTopup("");
    }

    @Override
    protected void setListener() {
        getBinding().btnSave.setOnClickListener(this);
        getBinding().txtTopup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String money = MoneyUtil.removeDotMoney(s.toString());
                String newMoney = MoneyUtil.convertMoney(money);
                if(getViewModel().getUpdateTopup()){
                    getViewModel().setUpdateTopup(false);
                    getBinding().txtTopup.setText(newMoney);
                    getBinding().txtTopup.setSelection(newMoney.length());
                }
                else{
                    getViewModel().setUpdateTopup(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().btnSave)){
            String newBalance = getViewModel().setNewBalance(getBinding().getTopup(), loadUserData().getBalance());
            User user = loadUserData().setBalance(newBalance);
            // SAVE TO SHARED PREFERENCE
            saveUserData(user);
            // SAVE TO DATABASE
            getViewModel().updateBalance(user);
            // ADD TRANSACTION
            Transaction transaction = new Transaction()
                    .setAmount(MoneyUtil.removeDotMoney(getBinding().getTopup()))
                    .setDate(CalendarUtil.getDate(this))
                    .setTime(CalendarUtil.getTime(this))
                    .setType(Transaction.TOP_UP)
                    .setPhone(loadUserData().getPhone());
            getViewModel().insertTransaction(transaction, loadUserData().getPhone());

            showToast(getResources().getString(R.string.text_success));
            finish();
        }
    }
}
