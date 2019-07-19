package com.example.qrcode.app.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.example.qrcode.model.BaseHistory;
import com.example.qrcode.model.HeaderHistory;
import com.example.qrcode.model.Transaction;
import com.example.qrcode.model.User;
import com.example.qrcode.repository.UserRepository;
import com.google.firebase.database.DataSnapshot;
import java.util.ArrayList;
import java.util.List;
import rx.subscriptions.CompositeSubscription;

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

    // repo.callHist
    public LiveData<List<Transaction>> getHistory(String phone){
        final MutableLiveData<List<Transaction>> result = new MutableLiveData<>();
        userRepository.getHistory(phone)
                .subscribe(dataSnapshot ->  {
                    if(dataSnapshot != null){
                        List<Transaction> item = new ArrayList<>();
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            item.add(data.getValue(Transaction.class));
                        }
                        result.setValue(item);
                    }
                    else{
                        result.setValue(new ArrayList<>());
                    }
                }, error -> {
                    result.setValue(new ArrayList<>());
                });
        return result;
    }

    public List<BaseHistory> processHistory(List<Transaction> listTransaction){
        List<BaseHistory> listHistory = new ArrayList<>();
        for(int i=0; i<listTransaction.size(); i++){
            if(i == 0 || !listTransaction.get(i).getDate().equals(listTransaction.get(i-1).getDate())){
                listHistory.add(new HeaderHistory().setDate(listTransaction.get(i).getDate()));
            }
            listHistory.add(listTransaction.get(i));
        }
        return listHistory;
    }

}
