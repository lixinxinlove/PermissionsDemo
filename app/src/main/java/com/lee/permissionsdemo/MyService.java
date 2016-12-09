package com.lee.permissionsdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    public MyService() {
    }


    IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
        @Override
        public String sayHello() throws RemoteException {
            return "lixinxin hello";
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


}
