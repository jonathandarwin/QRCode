package com.example.qrcode.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.qrcode.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import rx.Observable;
import rx.subjects.PublishSubject;


public class UserRepository implements ValueEventListener{

    DatabaseReference reference;
    PublishSubject<DataSnapshot> result;

    public UserRepository(){
        reference = FirebaseDatabase.getInstance().getReference();
    }

    public Observable<DataSnapshot> getPhoneNumber(String phone){
        result = PublishSubject.create();
        try{
            reference.child("user").orderByChild("phone").equalTo(phone).addListenerForSingleValueEvent(this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void insertUser(User user){
        try{
            reference.child("user").child(user.getPhone()).setValue(user);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Observable<DataSnapshot> getHistory(String phone){
        result = PublishSubject.create();
        try{
            reference.child("user").child(phone).child("transaction").addListenerForSingleValueEvent(this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        result.onNext(dataSnapshot);
        result.onCompleted();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        result.onNext(null);
        result.onCompleted();
    }
}
