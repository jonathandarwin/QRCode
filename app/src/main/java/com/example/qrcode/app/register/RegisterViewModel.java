package com.example.qrcode.app.register;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.qrcode.model.User;
import com.example.qrcode.repository.UserRepository;
import com.google.firebase.database.DataSnapshot;

import java.security.PublicKey;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class RegisterViewModel extends ViewModel {

    private UserRepository userRepository;

    public RegisterViewModel(){
        userRepository = new UserRepository();
    }

    public boolean validateInput(User user){
        if(user.getEmail() != null && user.getPassword() != null && user.getName() != null &&
            !user.getEmail().trim().equals("") && !user.getPassword().trim().equals("") && !user.getName().trim().equals(""))
            return true;

        return false;
    }

    public Observable<Boolean> getUserByEmail(String email){
        PublishSubject<Boolean> result = PublishSubject.create();
        userRepository.getUserByEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(results -> {
                    Boolean bool = new Boolean(true);
                    if(results != null){
                        for (DataSnapshot data : results.getChildren()){
                            User user = data.getValue(User.class);
                            if(user.getEmail() != null && user.getEmail().trim().equals(email)){
                                bool = false;
                            }
                            else{
                                bool = true;
                            }
                        }
                    }
                    else{
                        bool = false;
                    }
                    result.onNext(bool);
                }, error -> {
                    result.onNext(false);
                });
        return result;
    }

    public boolean insertUser(User user){
        return userRepository.insertUser(user);
    }
}
