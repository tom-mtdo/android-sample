package com.example.mtdo.aidlprimitive;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    ISumService mISumService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mISumService = ISumService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mISumService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSumService(View view){
        //Toast.makeText(this,"Start button click", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,AidlSumService.class);
        startService(intent);
        bindService(intent, mConnection, 0);
        Toast.makeText(this, "Service bindded", Toast.LENGTH_SHORT).show();
    }

    public void stopSumService(View view){
        //Toast.makeText(this,"Stop button click", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,AidlSumService.class);
        stopService(intent);
    }

    public void useSumService(View view){
        long result = 0;
        try {
            result = mISumService.sum(3L);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Toast.makeText(this,"Result from SumService = " + result, Toast.LENGTH_SHORT).show();
    }

}
