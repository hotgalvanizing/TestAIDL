package com.mx.testaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyAidlService extends Service {

    public MyAidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IBinder iBinder = new IMyAidl.Stub() {

        @Override
        public int add(int num1, int num2) throws RemoteException {
            return num1 + num2;
        }
    };

}
