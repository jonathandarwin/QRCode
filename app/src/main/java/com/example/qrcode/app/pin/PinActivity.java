package com.example.qrcode.app.pin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.qrcode.R;
import com.example.qrcode.app.home.HomeActivity;
import com.example.qrcode.app.register.RegisterActivity;
import com.example.qrcode.app.started.StartedActivity;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.PinActivityBinding;

public class PinActivity extends BaseActivity<PinActivityBinding, PinViewModel>
            implements View.OnClickListener{

    public PinActivity(){
        super(PinViewModel.class, R.layout.pin_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resetPin();
    }

    @Override
    protected void setListener() {
        getBinding().btnChangeAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().btnChangeAccount)){
            deleteUserData();
            gotoIntent(StartedActivity.class, null, true);
        }
    }

    public void onButtonPinClick(View v){
        String pin = getBinding().getPin();
        String input = ((Button) v).getText().toString();
        if(input.equals(getResources().getString(R.string.btn_delete)) && getBinding().getPin().length() != 0){
            pin = pin.substring(0, pin.length()-1);
        }
        else if(!input.equals(getResources().getString(R.string.btn_delete))){
            pin += input;
        }

        if(pin.length() == 6){
            if(getViewModel().checkPin(pin, loadUserData().getPin())){
                gotoIntent(HomeActivity.class, null, true);
            }
            else{
                Toast.makeText(this, getResources().getString(R.string.text_incorrect_pin), Toast.LENGTH_SHORT).show();
                resetPin();
            }
        }
        else{
            getBinding().setPin(pin);
            getBinding().setDisplayPin(getViewModel().setDisplayPin(pin));
        }
    }

    private void resetPin(){
        getBinding().setPin("");
        getBinding().setDisplayPin("");
    }
}
