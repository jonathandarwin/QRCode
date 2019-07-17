package com.example.qrcode.repository;

import android.support.annotation.NonNull;

import com.example.qrcode.model.Transaction;
import com.example.qrcode.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import rx.subjects.PublishSubject;

public class TransferRepository implements ValueEventListener {

    DatabaseReference reference;
    final PublishSubject<DataSnapshot> result;

    public TransferRepository(){
        reference = FirebaseDatabase.getInstance().getReference();
        result = PublishSubject.create();
    }

    public void insertTransaction(Transaction transaction, String phone){
        try{
            reference.child("user").child(phone).child("transaction").push().setValue(transaction);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateBalance(User user){
        try {
            reference.child("user").child(user.getPhone()).child("balance").setValue(user.getBalance());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        result.onNext(dataSnapshot);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        result.onNext(null);
    }
}
