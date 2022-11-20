package com.example.mycafe.ui.about;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public AboutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("姓名:羅志文" +
                " 學號:C109118205");
    }

    public LiveData<String> getText() {
        return mText;
    }
}