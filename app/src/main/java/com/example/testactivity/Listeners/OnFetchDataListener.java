package com.example.testactivity.Listeners;

import com.example.testactivity.Models.APIResponse;

public interface OnFetchDataListener {
    void onFetchData(APIResponse apiResponse, String message);
    void onError(String message);
}
