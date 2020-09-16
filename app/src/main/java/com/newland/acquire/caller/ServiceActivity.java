package com.newland.acquire.caller;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.newland.payment.aidl.IPaymentListener;
import com.newland.payment.aidl.IPaymentService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceActivity extends AppCompatActivity {
    @BindView(R.id.tv_water)
    TextView tvWater;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_value)
    TextView tvValue;

    private IPaymentService paymentService;

    private final static String PARAMS_NAME = "BASE_MERCHANTNAME";

    //必须在AndroidManifest添加权限.
    //<uses-permission android:name="payment.permission.acquire" />

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, final IBinder service) {
            paymentService = IPaymentService.Stub.asInterface(service);
            getParamValue();
            findFlow();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
        tvName.setText("参数名: " + PARAMS_NAME);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        if (paymentService == null) {
            Intent intent = new Intent();
            intent.setPackage("com.newland.payment");
            intent.setAction("android.intent.action.NEWLAND.PAYMENT.SERVICE");
            boolean result = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
            if (!result) {
                Toast.makeText(this, "未找到服务", Toast.LENGTH_LONG).show();
            }
        } else {
            getParamValue();
            findFlow();
        }
    }

    private void getParamValue(){
        try {
            tvValue.setText("参数值: " + paymentService.getParam("BASE_MERCHANTNAME"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void findFlow(){
        try {
            paymentService.findFlow("123456", new IPaymentListener.Stub() {
                @Override
                public void onSuccess(final String code,final  String data){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvWater.setText("流水同步结果: Code:"+code+",data:"+data);
                        }
                    });
                }

                @Override
                public void onFail(final String failCode,final String msg)  {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvWater.setText("流水同步结果: failCode:"+failCode+",msg:"+msg);
                        }
                    });

                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
