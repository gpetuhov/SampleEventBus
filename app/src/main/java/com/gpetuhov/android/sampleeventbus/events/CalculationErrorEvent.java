package com.gpetuhov.android.sampleeventbus.events;

// Delivers error message in case of an error during calculation in the Calculator
public class CalculationErrorEvent {

    private String mErrorMessage;

    public CalculationErrorEvent(String errorMessage) {
        mErrorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }
}
