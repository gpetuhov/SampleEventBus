package com.gpetuhov.android.sampleeventbus.events;

// Deliver result from main activity to second activity
public class ShowResultInSeparateActiivtyEvent {

    private String mResult;

    public ShowResultInSeparateActiivtyEvent(String result) {
        mResult = result;
    }

    public String getResult() {
        return mResult;
    }
}
