package com.example.qrcode.app.login;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.qrcode.model.User;
import com.example.qrcode.repository.UserRepository;
import com.google.firebase.database.DataSnapshot;

import java.security.PublicKey;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class LoginViewModel extends ViewModel {

    private UserRepository userRepository;

    public LoginViewModel(){
        userRepository = new UserRepository();
    }

    public boolean validateInput(User user){
        if(user.getEmail() != null && !user.getEmail().trim().equals("") &&
                user.getPassword() != null && !user.getPassword().trim().equals(""))
            return true;
        return false;
    }

    public Observable<Boolean> doLogin(User user){
        final PublishSubject<Boolean> result = PublishSubject.create();
        try{
            userRepository.doLogin(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.computation())
                    .subscribe(dataSnapshot -> {
                        if(dataSnapshot != null){
                            for(DataSnapshot data : dataSnapshot.getChildren()){
                                User userResponse = data.getValue(User.class);
                                if(userResponse.getPassword().equals(user.getPassword())){
                                    result.onNext(true);
                                }
                                else{
                                    result.onNext(false);
                                }
                            }
                        }
                        else{
                            result.onNext(false);
                        }
                    }, error -> {
                        result.onNext(false);
                    });
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
