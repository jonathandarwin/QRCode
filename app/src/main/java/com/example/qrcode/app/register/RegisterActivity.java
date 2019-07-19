package com.example.qrcode.app.register;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.example.qrcode.R;
import com.example.qrcode.app.home.HomeActivity;
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
        setInflaterLayout();
        setListenerInflaterLayout();
        setInitProgress();
        setValidatePhone();
        getBinding().llContent.addView(bindingPhoneNumber.getRoot());
    }

    protected void setListenerInflaterLayout() {
        bindingPhoneNumber.btnNext.setOnClickListener(this);
        bindingPin.btnNext.setOnClickListener(this);
        bindingPinConfirmation.btnNext.setOnClickListener(this);
        bindingDone.btnDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(bindingPhoneNumber.btnNext)){
            String phone = getResources().getString(R.string.text_register_base_phone_number).concat(bindingPhoneNumber.getPhone().trim());
            setLoaderPhoneNumber(true);
            getViewModel().validatePhone(phone).observe(this, this::handleValidatePhone);
        }
        else if(v.equals(bindingPin.btnNext)){
            if(getViewModel().validatePin(bindingPin.getPin())){
                updateProgress();
                getBinding().llContent.removeAllViews();
                getBinding().llContent.addView(bindingPinConfirmation.getRoot());
                getBinding().setTitle(getResources().getString(R.string.text_title_step_3));
            }
            else{
                showToast(getResources().getString(R.string.text_register_pin_invalid));
            }
        }
        else if(v.equals(bindingPinConfirmation.btnNext)){
            if(getViewModel().validatePinConfirmation(bindingPin.getPin(), bindingPinConfirmation.getPinConfirmation())){
                getViewModel().setPin(bindingPin.getPin());
                updateProgress();
                if(!getViewModel().getIsExists()){
                    getViewModel().insertUser(getViewModel().getDataUser());
                }
                getBinding().llContent.removeAllViews();
                getBinding().llContent.addView(bindingDone.getRoot());
                getBinding().setTitle(getResources().getString(R.string.text_title_step_4));
            }
            else{
                showToast(getResources().getString(R.string.text_register_pin_confirmation_invalid));
            }
        }
        else if (v.equals(bindingDone.btnDone)){
            saveUserData(getViewModel().getDataUser());
            gotoIntent(HomeActivity.class, null, true);
        }
    }

    public void onButtonPinClick(View v){
        bindingPin.setPin(getViewModel().processPin(bindingPin.getPin(), ((Button) v).getText().toString(), getResources().getString(R.string.btn_delete)));
    }

    public void onButtonPinConfirmationClick(View v){
        bindingPinConfirmation.setPinConfirmation(getViewModel().processPin(bindingPinConfirmation.getPinConfirmation(), ((Button) v).getText().toString(), getResources().getString(R.string.btn_delete)));
    }

    private void setInflaterLayout(){
        bindingPhoneNumber = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.register_phone_number, getBinding().llContent, false);
        bindingPin = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.register_pin, getBinding().llContent, false);
        bindingPinConfirmation = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.register_pin_confirmation, getBinding().llContent, false);
        bindingDone = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.register_done, getBinding().llContent, false);
    }

    private void setInitProgress(){
        progress = 1;
        bindingPhoneNumber.setPhone("");
        bindingPin.setPin("");
        bindingPinConfirmation.setPinConfirmation("");
        getBinding().setTitle(getResources().getString(R.string.text_title_step_1));

        getBinding().setDot1(getResources().getDrawable(R.drawable.slider_dot_done));
        getBinding().setDot2(getResources().getDrawable(R.drawable.slider_dot));
        getBinding().setDot3(getResources().getDrawable(R.drawable.slider_dot));
        getBinding().setDot4(getResources().getDrawable(R.drawable.slider_dot));

        getBinding().setLine1(getResources().getColor(R.color.colorProgresLine));
        getBinding().setLine2(getResources().getColor(R.color.colorProgresLine));
        getBinding().setLine3(getResources().getColor(R.color.colorProgresLine));
    }

    private void setValidatePhone(){
        bindingPhoneNumber.txtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean isChange = false;
                String phone = s.toString();
                if(phone.startsWith("0")){
                    isChange = true;
                    phone = phone.substring(1);
                }
                if(isChange){
                    bindingPhoneNumber.txtPhone.setText(phone);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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

    private void handleValidatePhone(Integer status){
        setLoaderPhoneNumber(false);
        switch (status){
            case RegisterViewModel.PHONE_VALID:
                updateProgress();
                getBinding().llContent.removeAllViews();
                getBinding().llContent.addView(bindingPin.getRoot());
                getBinding().setTitle(getResources().getString(R.string.text_title_step_2));
                break;
            case RegisterViewModel.PHONE_INVALID:
                showToast(getResources().getString(R.string.text_register_phone_invalid));
                break;
            case RegisterViewModel.ERROR:
                showToast(getResources().getString(R.string.text_error));
                break;
        }
    }

    private void setLoaderPhoneNumber(boolean isShown){
        bindingPhoneNumber.loader.setVisibility(isShown ? View.VISIBLE : View.GONE);
        bindingPhoneNumber.btnNext.setEnabled(isShown ? false : true);
    }
}
