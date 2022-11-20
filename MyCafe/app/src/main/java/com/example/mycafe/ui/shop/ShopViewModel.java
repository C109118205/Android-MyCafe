package com.example.mycafe.ui.shop;


import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycafe.R;

public class ShopViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ShopViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("BUY");
    }

    public LiveData<String> getText() {
        return mText;
    }

}