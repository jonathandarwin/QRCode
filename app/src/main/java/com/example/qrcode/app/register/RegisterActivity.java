package com.example.qrcode.app.register;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;

import com.example.qrcode.R;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.RegisterActivityBinding;
import com.example.qrcode.databinding.RegisterDoneBinding;
import com.example.qrcode.databinding.RegisterPhoneNumberBinding;
import com.example.qrcode.databinding.RegisterPinBinding;
import com.example.qrcode.databinding.RegisterPinConfirmationBinding;

public class RegisterActivity extends BaseActivity<RegisterActivityBinding, RegisterViewModel>
            implements View.OnClickListener{

    RegisterPhoneNumberBinding bindingPhoneNumber;
    RegisterPinBinding bindingPin;
    RegisterPinConfirmationBinding bindingPinConfirmation;
    RegisterDoneBinding bindingDone;

    int progress;

    public RegisterActivity(){
        super(RegisterViewModel.class, R.layout.register_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInitProgress();
        setInflaterLayout();
        setListenerInflaterLayout();
        getBinding().llContent.addView(bindingPhoneNumber.getRoot());
    }

    protected void setListenerInflaterLayout() {
        bindingPhoneNumber.btnNext.setOnClickListener(this);
        bindingPin.btnNext.setOnClickListener(this);
        bindingPinConfirmation.btnNext.setOnClickListener(this);
        bindingDone.btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(bindingPhoneNumber.btnNext)){
            updateProgress();
            getBinding().llContent.removeAllViews();
            getBinding().llContent.addView(bindingPin.getRoot());
        }
        else if(v.equals(bindingPin.btnNext)){
            updateProgress();
            getBinding().llContent.removeAllViews();
            getBinding().llContent.addView(bindingPinConfirmation.getRoot());
        }
        else if(v.equals(bindingPinConfirmation.btnNext)){
            updateProgress();
            getBinding().llContent.removeAllViews();
            getBinding().llContent.addView(bindingDone.getRoot());
        }
    }

    private void setInflaterLayout(){
        bindingPhoneNumber = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.register_phone_number, getBinding().llContent, false);
        bindingPin = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.register_pin, getBinding().llContent, false);
        bindingPinConfirmation = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.register_pin_confirmation, getBinding().llContent, false);
        bindingDone = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.register_done, getBinding().llContent, false);
    }

    private void setInitProgress(){
        progress = 1;

        getBinding().setDot1(getResources().getDrawable(R.drawable.slider_dot_done));
        getBinding().setDot2(getResources().getDrawable(R.drawable.slider_dot));
        getBinding().setDot3(getResources().getDrawable(R.drawable.slider_dot));
        getBinding().setDot4(getResources().getDrawable(R.drawable.slider_dot));

        getBinding().setLine1(getResources().getColor(R.color.colorProgresLine));
        getBinding().setLine2(getResources().getColor(R.color.colorProgresLine));
        getBinding().setLine3(getResources().getColor(R.color.colorProgresLine));
    }

    private void updateProgress(){
        progress++;
        switch (progress){
            case 2:
                getBinding().setDot2(getResources().getDrawable(R.drawable.slider_dot_done));
                getBinding().setLine1(getColorDone());
                break;
            case 3:
                getBinding().setDot3(getResources().getDrawable(R.drawable.slider_dot_done));
                getBinding().setLine2(getColorDone());
                break;
            case 4:
                getBinding().setDot4(getResources().getDrawable(R.drawable.slider_dot_done));
                getBinding().setLine3(getColorDone());
                break;
        }
    }

    private int getColorDone(){
        return getResources().getColor(R.color.colorBackground);
    }
}
