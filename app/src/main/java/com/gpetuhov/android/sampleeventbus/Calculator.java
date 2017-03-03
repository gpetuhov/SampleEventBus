package com.gpetuhov.android.sampleeventbus;


import com.gpetuhov.android.sampleeventbus.events.CalculationErrorEvent;
import com.gpetuhov.android.sampleeventbus.events.CalculationFinishedEvent;
import com.gpetuhov.android.sampleeventbus.events.StartCalculationEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

// Start calculation when StartCalculationEvent is posted to EventBus.
// Return result via CalculationFinishedEvent.
// Return error messege via CalculationErrorEvent.
public class Calculator {

    // Sleep time imitates calculation process
    public static final int SLEEP_TIME = 5000;

    // This is returned as result
    public static final int RESULT = 5;

    public Calculator() {
        // Calculator must listen to EventBus to be started
        EventBus.getDefault().register(this);
    }

    // Called when a StartCalculationEvent is posted (in a separate thread not to block the UI)
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onStartCalculationEvent(StartCalculationEvent startCalculationEvent) {
        try {
            // Imitate calculations by sleeping
            Thread.sleep(SLEEP_TIME);
            // Send result
            sendResult(RESULT);
        } catch (InterruptedException e) {
            // Error during calculation
            sendError("Error during calculation");
        }
    }

    // Post result to EventBus
    private void sendResult(int result) {
        // Convert result to String
        String resultString = String.valueOf(result);

        // Post result to EventBus
        EventBus.getDefault().post(new CalculationFinishedEvent(resultString));

        // This is needed, because new instance of Calculator is created for each calculation
        unregisterFromEventBus();
    }

    // Post error to EventBus
    private void sendError(String errorMessage) {
        // Post error to EventBus
        EventBus.getDefault().post(new CalculationErrorEvent(errorMessage));

        // This is needed, because new instance of Calculator is created for each calculation
        unregisterFromEventBus();
    }

    // Unregister from EventBus
    private void unregisterFromEventBus() {
        EventBus.getDefault().unregister(this);
    }
}
