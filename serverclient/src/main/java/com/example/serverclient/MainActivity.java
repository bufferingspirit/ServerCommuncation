package com.example.serverclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.remotecommlibrary.RemoteInterface;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    RemoteInterface myServer;
    RemoteServiceConnection connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initService();
    }

    /** Binds this activity to the service. */
    private void initService() {
        connection = new RemoteServiceConnection();
        Intent i = new Intent();

        i.setAction("com.example.admin.service.ACTION_BIND");
        i.setComponent(new ComponentName("com.example.admin.servercommuncation", "com.example.admin.servercommuncation.BoundService"));

        boolean ret = bindService(i, connection, Context.BIND_AUTO_CREATE);
        Log.d(TAG, "initService() bound with " + ret);
    }

    /** Unbinds this activity from the service. */
    private void releaseService() {
        unbindService(connection);
        connection = null;
        Log.d(TAG, "releaseService() unbound.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseService();
    }

    public void callServer(View view){
        try {
            Log.d(TAG, "callServer: " + myServer.stuff());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private class RemoteServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myServer = RemoteInterface.Stub.asInterface((IBinder) iBinder);
            Log.d(TAG, "onServiceConnected() connected");
            Toast.makeText(MainActivity.this, "Service connected", Toast.LENGTH_LONG)
                    .show();
            try {
                Log.d(TAG, "onServiceConnected: "  + myServer.stuff());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myServer = null;
            Log.d(TAG, "onServiceDisconnected() disconnected");
            Toast.makeText(MainActivity.this, "Service connected", Toast.LENGTH_LONG)
                    .show();
        }
    }

}
