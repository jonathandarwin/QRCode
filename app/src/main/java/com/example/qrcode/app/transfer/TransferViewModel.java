package com.example.qrcode.app.transfer;

import android.arch.lifecycle.ViewModel;

import com.example.qrcode.model.Transaction;
import com.example.qrcode.model.User;
import com.example.qrcode.repository.TransferRepository;
import com.example.qrcode.repository.UserRepository;
import com.google.firebase.database.DataSnapshot;

public class TransferViewModel extends ViewModel {

    protected boolean updatePrice;
    protected TransferRepository transferRepository;
    protected UserRepository userRepository;

    public TransferViewModel(){
        transferRepository = new TransferRepository();
        userRepository = new UserRepository();
    }

    public void setUpdatePrice(boolean updatePrice){
        this.updatePrice = updatePrice;
    }

    public boolean getUpdatePrice(){
        return updatePrice;
    }

    public boolean validateBalance(String balance, String price){
        return Integer.parseInt(balance) >= Integer.parseInt(price);
    }

    public void insertTransaction(Transaction transaction, String phone){
        transferRepository.insertTransaction(transaction, phone);
    }

    public void updateReceiverBalance(User user){
        userRepository.getPhoneNumber(user.getPhone())
                .subscribe(dataSnapshot ->  {
                    if(dataSnapshot != null){
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            String balance = data.getValue(User.class).getBalance();
                            balance = Integer.toString(Integer.parseInt(user.getBalance()) + Integer.parseInt(balance));
                            user.setBalance(balance);
                            transferRepository.updateBalance(user);
                        }
                    }
                }, error -> {

                });
    }

    public void updateSenderBalance(User user){
        transferRepository.updateBalance(user);
    }
}
