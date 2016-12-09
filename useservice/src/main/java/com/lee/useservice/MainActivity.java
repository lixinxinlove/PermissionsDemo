package com.lee.useservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lee.permissionsdemo.IMyAidlInterface;
import com.lee.permissionsdemo.IeeAidlInterface;

public class MainActivity extends AppCompatActivity {


    private Button button;
    private TextView textView;

    private IMyAidlInterface iMyAidlInterface;

    private IeeAidlInterface ieeAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.tv);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Intent intent = new Intent();
                intent.setAction("com.lee.permissionsdemo.LeeService");
                intent.setPackage("com.lee.permissionsdemo");
                bindService(intent,connection, Service.BIND_AUTO_CREATE);
            }
        });

    }


    ServiceConnection connection = new ServiceConnection() {

        String content;

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            ieeAidlInterface = IeeAidlInterface.Stub.asInterface(iBinder);

            try {
                content = ieeAidlInterface.basicTypes(10);
                textView.setText(content);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iMyAidlInterface = null;
        }
    };
}
