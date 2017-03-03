package com.gpetuhov.android.sampleeventbus;

import android.support.v4.app.Fragment;

public class SecondActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SecondFragment();
    }
}