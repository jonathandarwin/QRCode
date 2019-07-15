package com.example.qrcode.app.register;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.qrcode.R;
import com.example.qrcode.model.User;
import com.example.qrcode.repository.UserRepository;
import com.google.firebase.database.DataSnapshot;

import rx.schedulers.Schedulers;

public class RegisterViewModel extends ViewModel {

    public static final int ERROR = 1;
    public static final int PHONE_VALID = 2;
    public static final int PHONE_INVALID = 3;

    private UserRepository userRepository;
    String phone;

    public RegisterViewModel(){
        userRepository = new UserRepository();
    }

    public LiveData<Integer> validatePhone(String phone){
        MutableLiveData<Integer> result = new MutableLiveData<>();
        if(phone != null && !phone.trim().equals("+62")){
//            setPhone(phone);
//            result.setValue(PHONE_VALID);
            try{
                userRepository.getPhoneNumber(phone)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                        .subscribe(dataSnapshot -> {
                            if(dataSnapshot != null){
                                setPhone(phone);
                                result.setValue(PHONE_VALID);
                            }
                            else{
                                result.setValue(ERROR);
                            }
                        }, error -> {
                            result.setValue(ERROR);
                        });
            }
            catch (Exception e){
                e.printStackTrace();
                result.setValue(ERROR);
            }
        }
        else{
            result.setValue(PHONE_INVALID);
        }
        return result;
    }

    private void setPhone(String phone){
        this.phone = phone;
    }
}
