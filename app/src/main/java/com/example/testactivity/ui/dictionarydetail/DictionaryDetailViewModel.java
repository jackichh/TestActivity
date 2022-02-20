package com.example.testactivity.ui.dictionarydetail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testactivity.adapter.DrawerAdapter;

public class DictionaryDetailViewModel extends ViewModel {

    DrawerAdapter mDrawerAdapter;

    // TODO: Implement the ViewModel
    private String mText;

    public DictionaryDetailViewModel(){
        mText = "";
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText=mText;
    }
}