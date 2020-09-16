package com.newland.acquire.caller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_service, R.id.btn_sale, R.id.btn_voidsale, R.id.btn_refund,
            R.id.btn_union_scan, R.id.btn_union_void,R.id.btn_union_refund,
    R.id.btn_installment_sale,R.id.btn_installment_void})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_service:
                //参数获取服务
                startActivity(new Intent(this, ServiceActivity.class));
                break;
            case R.id.btn_sale:
                //消费
                startActivity(new Intent(this, SaleActivity.class));
                break;
            case R.id.btn_voidsale:
                //消费撤销
                startActivity(new Intent(this, VoidSaleActivity.class));
                break;
            case R.id.btn_refund:
                startActivity(new Intent(this, RefundActivity.class));
                break;
            case R.id.btn_union_scan:
                //扫码支付
                startActivity(new Intent(this, ScanSaleActivity.class));
                break;
            case R.id.btn_union_void:
                //扫码撤销
                startActivity(new Intent(this, VoidScanPayActivity.class));
                break;
            case R.id.btn_union_refund:
                //扫码退货
                startActivity(new Intent(this, ScanRefundActivity.class));
                break;
            case R.id.btn_installment_sale:
                //分期消费
                startActivity(new Intent(this, InstallmentSaleActivity.class));
                break;
            case R.id.btn_installment_void:
                //分期撤销
                startActivity(new Intent(this, InstallmentVoidSaleActivity.class));
                break;
            default:
                break;
        }
    }
}
