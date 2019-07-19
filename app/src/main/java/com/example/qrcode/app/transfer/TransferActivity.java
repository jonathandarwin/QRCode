package com.example.qrcode.app.transfer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.qrcode.R;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.TransferActivityBinding;
import com.example.qrcode.model.Transaction;
import com.example.qrcode.model.User;
import com.example.qrcode.util.CalendarUtil;
import com.example.qrcode.util.MoneyUtil;

public class TransferActivity extends BaseActivity<TransferActivityBinding, TransferViewModel>
            implements View.OnClickListener{

    public TransferActivity(){
        super(TransferViewModel.class, R.layout.transfer_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setUpdatePrice(true);
        getBinding().setPhone(getIntent().getExtras().getString("phone"));
        getBinding().setPrice("");
        getBinding().setBalance(MoneyUtil.convertMoney(loadUserData().getBalance()));
    }

    @Override
    protected void setListener() {
        getBinding().back.setOnClickListener(this);
        getBinding().btnTransfer.setOnClickListener(this);
        getBinding().txtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String money = MoneyUtil.removeDotMoney(s.toString());
                String newMoney = MoneyUtil.convertMoney(money);
                if(getViewModel().getUpdatePrice()){
                    getViewModel().setUpdatePrice(false);
                    getBinding().txtPrice.setText(newMoney);
                    getBinding().txtPrice.setSelection(newMoney.length());
                }
                else{
                    getViewModel().setUpdatePrice(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().back)){
            finish();
        }
        else if (v.equals(getBinding().btnTransfer)){
            String price = MoneyUtil.removeDotMoney(getBinding().getPrice());
            if(getViewModel().validateBalance(loadUserData().getBalance(), price)){
                Transaction sender = new Transaction()
                        .setDate(CalendarUtil.getDate(this))
                        .setTime(CalendarUtil.getTime(this))
                        .setPhone(getBinding().getPhone())
                        .setAmount(price)
                        .setType(Transaction.TRANSFER_OUT);

                Transaction receiver = new Transaction()
                        .setDate(CalendarUtil.getDate(this))
                        .setTime(CalendarUtil.getTime(this))
                        .setPhone(loadUserData().getPhone())
                        .setAmount(price)
                        .setType(Transaction.TRANSFER_IN);

                // INSERT SENDER
                getViewModel().insertTransaction(sender, loadUserData().getPhone());
                // INSERT RECEIVER
                getViewModel().insertTransaction(receiver, getBinding().getPhone());
                // UPDATE RECEIVER BALANCE
                User userReceiver = new User()
                        .setBalance(price)
                        .setPhone(getBinding().getPhone());
                getViewModel().updateReceiverBalance(userReceiver);
                // UPDATE SENDER BALANCE
                User userSender = new User()
                        .setBalance(Integer.toString(Integer.parseInt(loadUserData().getBalance()) - Integer.parseInt(price)))
                        .setPhone(loadUserData().getPhone());
                getViewModel().updateSenderBalance(userSender);

                showToast(getResources().getString(R.string.text_success));
                finish();
            }
            else{
                showToast(getResources().getString(R.string.text_not_enough_balance));
            }
        }
    }
}
