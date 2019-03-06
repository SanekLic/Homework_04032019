package com.example.homework_04032019;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String CURRENT_COUNTER_TEXT = "currentCounterText";
    private Intent intentAnyIntentService;
    private AnyBroadcastReceiver anyBroadcastReceiver;
    private TextView counterTextView;
    private Button startServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.counterTextView);
        startServiceButton = findViewById(R.id.startServiceButton);
        intentAnyIntentService = new Intent(this, AnyIntentService.class);
        anyBroadcastReceiver = new AnyBroadcastReceiver(counterTextView);

        IntentFilter intentFilter = new IntentFilter(AnyIntentService.ACTION_CHANGE_COUNTER);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(anyBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(anyBroadcastReceiver);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(CURRENT_COUNTER_TEXT, counterTextView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        counterTextView.setText(savedInstanceState.getString(CURRENT_COUNTER_TEXT));
    }

    public void onButtonClick(View v) {
        startService(intentAnyIntentService.putExtra(AnyIntentService.KEY_START_COUNTER, true));
        startServiceButton.setEnabled(false);
    }
}
