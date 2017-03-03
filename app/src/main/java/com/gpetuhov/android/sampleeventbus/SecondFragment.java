package com.gpetuhov.android.sampleeventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gpetuhov.android.sampleeventbus.events.ShowResultInSeparateActiivtyEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

// Fragment for the second activity
public class SecondFragment extends Fragment {

    // Result text
    @BindView(R.id.result_in_second_fragment) TextView mSecondResultTextView;

    // Keeps ButterKnife Unbinder object to properly unbind views in onDestroyView of the fragment
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second, container, false);

        // Bind views and save reference to Unbinder object
        mUnbinder = ButterKnife.bind(this, v);

        // Manually remove sticky event with result from EventBus.
        // To do this we don't have to register to EventBus in onStart() like in MainFragment.
        ShowResultInSeparateActiivtyEvent event =
                EventBus.getDefault().removeStickyEvent(ShowResultInSeparateActiivtyEvent.class);

        // Check if an event was actually posted before
        if(event != null) {
            // Display result
            mSecondResultTextView.setText(event.getResult());
        }

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // This is recommended to do here when using Butterknife in fragments
        mUnbinder.unbind();
    }
}
