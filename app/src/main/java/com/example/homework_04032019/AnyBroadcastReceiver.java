package com.example.homework_04032019;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

class AnyBroadcastReceiver extends BroadcastReceiver {
    TextView counterTextView;

    AnyBroadcastReceiver(TextView counterTextView) {
        this.counterTextView = counterTextView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int currentCounter = intent.getIntExtra(AnyIntentService.KEY_COUNTER,0);
        counterTextView.setText(String.valueOf(currentCounter));
    }
}
