package com.gpetuhov.android.sampleeventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gpetuhov.android.sampleeventbus.events.CalculationErrorEvent;
import com.gpetuhov.android.sampleeventbus.events.CalculationFinishedEvent;
import com.gpetuhov.android.sampleeventbus.events.StartCalculationEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainFragment extends Fragment {

    // Start calculations button
    @BindView(R.id.button_start) Button mStartButton;

    // Result text
    @BindView(R.id.result) TextView mResult;

    // Show in a separate activity button
    @BindView(R.id.button_show) Button mShowButton;

    // Keeps ButterKnife Unbinder object to properly unbind views in onDestroyView of the fragment
    private Unbinder mUnbinder;

    private Calculator mCalculator;

    @Override
    public void onStart() {
        super.onStart();

        // Register to listen to EventBus events
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        // Unregister from EventBus
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        // Bind views and save reference to Unbinder object
        mUnbinder = ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // This is recommended to do here when using Butterknife in fragments
        mUnbinder.unbind();
    }


    // Called when start button is clicked
    @OnClick(R.id.button_start)
    public void startCalculation() {

        // Create new Calculator instance
        mCalculator = new Calculator();

        // Post new StartCalculationEvent to EventBus
        EventBus.getDefault().post(new StartCalculationEvent());

        mResult.setText("Calculating...");
    }

    // Called when show button is clicked
    @OnClick(R.id.button_show)
    public void showResultInNewActivity() {

    }

    // Called when a CalculationFinishedEvent is posted (in the UI thread)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCalculationFinishedEvent(CalculationFinishedEvent calculationFinishedEvent) {
        // Get result from the event and display it in TextView
        mResult.setText(calculationFinishedEvent.getResult());
    }

    // Called when a CalculationErrorEvent is posted (in the UI thread)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCalculationErrorEvent(CalculationErrorEvent calculationErrorEvent) {
        // Get error message from the event and display it in TextView
        mResult.setText(calculationErrorEvent.getErrorMessage());
    }
}
