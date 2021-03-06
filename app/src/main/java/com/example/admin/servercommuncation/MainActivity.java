package com.example.admin.servercommuncation;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.remotecommlibrary.RemoteInterface;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Intent boundIntent;
    RemoteInterface boundService;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isBound) {
            boundIntent = new Intent(this, BoundService.class);
            Log.d(TAG, "onCreate: " + BoundService.class);
            bindService(boundIntent, serviceConnection, this.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isBound)
        stopService(boundIntent);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boundService = RemoteInterface.Stub.asInterface(iBinder);
            try {
                Log.d(TAG, "onServiceConnected: " + boundService.stuff());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };
}
