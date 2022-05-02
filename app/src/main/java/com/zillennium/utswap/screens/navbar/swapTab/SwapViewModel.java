package com.zillennium.utswap.screens.navbar.swapTab;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SwapViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SwapViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Coming Soon");
    }

    public LiveData<String> getText() {
        return mText;
    }
}