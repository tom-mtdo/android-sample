package com.example.mtdo.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = MyService.class.getName();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, TAG + " created.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(TAG, TAG + " started.");
        handleCommand(intent);
        return START_STICKY;
    }

    public void handleCommand(Intent intent){
        Bundle extras = null;

        if (intent == null ){
            Log.i(TAG, "Intent is null");
            return;
        } else {
            extras = intent.getExtras();
        }
        if (extras == null){
            Log.i(TAG, "extras is null");
        } else {
            /*
             * get data from intent
             */
            Log.i(TAG, "Data1: " + extras.getString("data1"));
        }

        /*
         * main job going to be here
         */
        synchronized (this) {
            try {
                wait(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, TAG + " destroyed.");
    }
}
