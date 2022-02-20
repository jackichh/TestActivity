package com.example.testactivity.ui.newdictionary;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class NewDictionaryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NewDictionaryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Edit title of dictionary");
    }

    public MutableLiveData<String> getText() {
        return mText;
    }
}