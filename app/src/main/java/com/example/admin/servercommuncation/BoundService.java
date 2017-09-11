package com.example.admin.servercommuncation;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.remotecommlibrary.RemoteInterface;

/**
 * Created by Admin on 9/9/2017.
 */

public class BoundService extends Service{
    public static final String TAG = "MyBoundServiceTag";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return iBinder;

    }

    private final RemoteInterface.Stub iBinder = new RemoteInterface.Stub() {
        public int getPid(){
            return Process.myPid();
        }
        public String stuff(){
            return "RandomData";
        }
    };

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
}
