package com.example.homework_04032019;

import android.app.IntentService;
import android.content.Intent;

public class AnyIntentService extends IntentService {

    public static final String KEY_COUNTER = "Counter";
    public static final String KEY_START_COUNTER = "StartCounter";
    public static final String ACTION_CHANGE_COUNTER = "ChangeCounter";
    public static final String ANY_SERVICE = "AnyIntentService";
    private int counter = 0;
    private Intent responseIntent;

    public AnyIntentService() {
        super(ANY_SERVICE);
    }

    public void onCreate() {
        super.onCreate();

        responseIntent = new Intent();
        responseIntent.setAction(ACTION_CHANGE_COUNTER);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent.getBooleanExtra(KEY_START_COUNTER, false)) {
            try {
                while (counter < 100) {
                    counter++;
                    responseIntent.putExtra(KEY_COUNTER, counter);
                    sendBroadcast(responseIntent);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
            }
        }
    }
}
