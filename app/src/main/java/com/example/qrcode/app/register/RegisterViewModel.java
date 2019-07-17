package com.example.qrcode.app.register;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.qrcode.model.User;
import com.example.qrcode.repository.UserRepository;
import com.google.firebase.database.DataSnapshot;

public class RegisterViewModel extends ViewModel {

    public static final int ERROR = 1;
    public static final int PHONE_VALID = 2;
    public static final int PHONE_INVALID = 3;

    private UserRepository userRepository;
    private boolean isExists;
    User user = new User();

    public RegisterViewModel(){
        userRepository = new UserRepository();
    }

    public LiveData<Integer> validatePhone(String phone){
        MutableLiveData<Integer> result = new MutableLiveData<>();
        if(phone != null && !phone.trim().equals("+62")){
            try{
                userRepository.getPhoneNumber(phone)
                        .subscribe(dataSnapshot -> {
                            if(dataSnapshot != null){
                                for(DataSnapshot data : dataSnapshot.getChildren()){
                                    user.setBalance(data.getValue(User.class).getBalance());
                                    setIsExists(true);
                                }
                                user.setPhone(phone);
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

    public void insertUser(User user){
        user.setBalance("0");
        userRepository.insertUser(user);
    }

    public String processPin(String pin, String input, String delete){
        if(input.equals(delete) && pin.length() != 0){
            pin = pin.substring(0, pin.length()-1);
        }
        else if(!input.equals(delete) && pin.length() < 6){
            pin += input;
        }
        return pin;
    }

    public boolean validatePin(String pin){
        return pin.length() == 6 ? true : false;
    }

    public boolean validatePinConfirmation(String pin, String pinConfirmation){
        return pin.equals(pinConfirmation) ? true : false;
    }

    public void setPin(String pin){
        user.setPin(pin);
    }

    public User getDataUser(){
        return user;
    }

    public boolean getIsExists(){
        return isExists;
    }

    public void setIsExists(boolean isExists){
        this.isExists = isExists;
    }
}
