package com.lee.permissionsdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by android on 2016/12/9.
 */
public class LeeService extends Service {

    public LeeService() {
    }


    IeeAidlInterface.Stub binder = new IeeAidlInterface.Stub() {
        @Override
        public String basicTypes(int i) throws RemoteException {
            return i + 1000 + "";
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
