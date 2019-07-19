package com.example.qrcode.app.topup;

import android.arch.lifecycle.ViewModel;

import com.example.qrcode.model.Transaction;
import com.example.qrcode.model.User;
import com.example.qrcode.repository.TransferRepository;
import com.example.qrcode.repository.UserRepository;
import com.example.qrcode.util.MoneyUtil;

public class TopUpViewModel extends ViewModel {

    protected boolean updateTopup;
    TransferRepository transferRepository;

    public TopUpViewModel(){
        transferRepository = new TransferRepository();
    }

    public void setUpdateTopup(boolean updateTopup){
        this.updateTopup = updateTopup;
    }

    public boolean getUpdateTopup(){
        return updateTopup;
    }

    public String setNewBalance(String topup, String balance){
        int topupInt = Integer.parseInt(MoneyUtil.removeDotMoney(topup));
        int balanceInt = Integer.parseInt(balance);
        return Integer.toString(topupInt + balanceInt);
    }

    public void updateBalance(User user){
        transferRepository.updateBalance(user);
    }

    public void insertTransaction(Transaction transaction, String phone){
        transferRepository.insertTransaction(transaction, phone);
    }
}
