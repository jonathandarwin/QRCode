package com.example.qrcode.repository;

import android.support.annotation.NonNull;

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
    final PublishSubject<DataSnapshot> result;

    public UserRepository(){
        reference = FirebaseDatabase.getInstance().getReference();
        result = PublishSubject.create();
    }

    public boolean insertUser(User user){
        try{
            reference.child("user").push().setValue(user);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public Observable<DataSnapshot> getUserByEmail(String email){
        final PublishSubject<DataSnapshot> result = PublishSubject.create();
        try{
            reference.child("user").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public Observable<DataSnapshot> doLogin(User user){
        try{
            reference.child("user").orderByChild("email").equalTo(user.getEmail()).addListenerForSingleValueEvent(this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
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
