package com.example.testactivity.ui.newdictionary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewDictionaryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NewDictionaryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Edit title of dictionary");
    }

    public LiveData<String> getText() {
        return mText;
    }
}