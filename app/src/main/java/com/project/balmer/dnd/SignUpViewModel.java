package com.project.balmer.dnd;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.balmer.dnd.Model.CustomerInfo;
import com.project.balmer.dnd.repository.DataManager;

public class SignUpViewModel extends ViewModel {
    private FirebaseAuth auth;
    private static DataManager dataManager;
    private String userEmail;

    public void init(Activity activity) {
        if (auth != null) {
            return;
        }
        dataManager = DataManager.getdm(activity);
    }
    public void createNewUserOnDb(CustomerInfo customerInfo, FinishedTask finishedTask){
        dataManager.createUserOnDb(customerInfo, new DataManager.CreateUser() {
            @Override
            public void onSuccess(boolean done) {
                Log.e("Creating user", "true");
                finishedTask.onSuccess(true);
            }
        });
    }
    public interface FinishedTask{
        void onSuccess(boolean done);
    }

}