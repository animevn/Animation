package com.haanhgs.animation.viewmodel;

import com.haanhgs.animation.model.Model;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private final MutableLiveData<Model> liveData = new MutableLiveData<>();

    public MutableLiveData<Model> getLiveData() {
        return liveData;
    }

    public void setLiveData(){
        liveData.setValue(new Model());
    }

    public void setDuration(int duration) {
        if (liveData.getValue() == null) setLiveData();
        liveData.getValue().setDuration(duration);
        liveData.setValue(liveData.getValue());
    }
}
