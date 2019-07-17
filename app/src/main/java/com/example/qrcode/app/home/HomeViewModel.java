package com.example.qrcode.app.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.qrcode.model.User;
import com.example.qrcode.repository.UserRepository;
import com.google.firebase.database.DataSnapshot;

import rx.Observable;
import rx.subjects.PublishSubject;

public class HomeViewModel extends ViewModel {

    private UserRepository userRepository;

    public HomeViewModel(){
        userRepository = new UserRepository();
    }

    public LiveData<User> updateUserData(String phone){
        final MutableLiveData<User> result = new MutableLiveData<>();
        userRepository.getPhoneNumber(phone)
                .subscribe(dataSnapshot -> {
                    if(dataSnapshot != null){
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            User user = data.getValue(User.class);
                            result.setValue(user);
                        }
                    }
                }, error ->{

                });
        return result;
    }
}
