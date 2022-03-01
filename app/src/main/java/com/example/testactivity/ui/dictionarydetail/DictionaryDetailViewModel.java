package com.example.testactivity.ui.dictionarydetail;

import androidx.lifecycle.ViewModel;

public class DictionaryDetailViewModel extends ViewModel {

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