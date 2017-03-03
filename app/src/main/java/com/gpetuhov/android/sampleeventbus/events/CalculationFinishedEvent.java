package com.gpetuhov.android.sampleeventbus.events;

// Delivers result from the Calculator when posted to EventBus
public class CalculationFinishedEvent {

    private String mResult;

    public CalculationFinishedEvent(String result) {
        mResult = result;
    }

    public String getResult() {
        return mResult;
    }
}
