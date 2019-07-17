package com.example.qrcode.app.home.scan.transfer;

import android.arch.lifecycle.ViewModel;

public class TransferViewModel extends ViewModel {

    protected boolean updatePhone;

    public TransferViewModel(){

    }

    public void setUpdatePhone(boolean updatePhone){
        this.updatePhone = updatePhone;
    }

    public boolean getUpdatePhone(){
        return updatePhone;
    }

}
