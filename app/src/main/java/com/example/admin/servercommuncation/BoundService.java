package com.example.admin.servercommuncation;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Admin on 9/9/2017.
 */

public class BoundService extends Service{
    public static final String TAG = "MyBoundServiceTag";

    //IBinder iBinder = new ServerBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return new RemoteInterface.Stub() {

            @Override
            public int getPid() throws RemoteException {
                Log.d(TAG, "getPid: ");
                return Process.myPid();
            }

            public String stuff(){
                Log.d(TAG, "stuff: ");
                return "RandomData";
            }
        };
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");

    }

    public class ServerBinder extends Binder {
        public BoundService getService(){
            return BoundService.this;
        }
    }
}
