package com.example.mtdo.aidlprimitive;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AidlSumService extends Service {
    private final String TAG = this.getClass().getName();

    public AidlSumService() {
    }

    public class SumServiceImpl extends ISumService.Stub {

        @Override
        public long sum(long n) throws RemoteException {
            return (n + n);
            // return AidlSumService.this.serviceSum(n);
        }
    }

    public long serviceSum(long n){
        return (n * n);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new SumServiceImpl();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"Service created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Service destroy");
    }
}
