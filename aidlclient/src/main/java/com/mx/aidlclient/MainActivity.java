package com.mx.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mx.testaidl.IMyAidl;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText num1;
    private EditText num2;
    private Button btnOk;
    IMyAidl iMyAidl;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidl = IMyAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iMyAidl = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        bindService();
    }

    private void init() {
        num1 = findViewById(R.id.num1_tx);
        num2 = findViewById(R.id.num2_tx);
        btnOk = findViewById(R.id.ok_btn);

        btnOk.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnOk) {
            int number1 = Integer.parseInt(num1.getText().toString());
            int number2 = Integer.parseInt(num2.getText().toString());
            int x = 0;
            try {
                x = iMyAidl.add(number1, number2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            btnOk.setText(x + "");
        }
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.mx.testaidl", "com.mx.testaidl.MyAidlService"));
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }
}
