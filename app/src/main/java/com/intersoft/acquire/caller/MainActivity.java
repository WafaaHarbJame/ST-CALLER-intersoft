package com.intersoft.acquire.caller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.intersoft.acquire.caller.common.Consts;
import com.intersoft.acquire.caller.common.ThirdTag;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //CR# 15.09.2021
    public static final int   LAST_TXN=0;
    public static final int   LAST_REC=1;
    public static int xml_Type;
    //=====================
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

   // CR# 15.09.2021
    @OnClick({R.id.btn_service, R.id.btn_sale, R.id.btn_voidsale, R.id.btn_printimage, R.id.btn_last_txn, R.id.btn_last_recon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_service:
                //get service
                startActivity(new Intent(this, ServiceActivity.class));
                break;
            case R.id.btn_sale:
                //Sale
                startActivity(new Intent(this, SaleActivity.class));
                break;
            case R.id.btn_voidsale:
                //Void Sale
                startActivity(new Intent(this, VoidSaleActivity.class));
                break;
            case R.id.btn_printimage:
                //Print Image
                printImage();
                break;
            case R.id.btn_refund:
                startActivity(new Intent(this, RefundActivity.class));
                break;
            case R.id.btn_last_txn:
                xml_Type = LAST_TXN;
              startActivity(new Intent(this, XmlDataActivity.class));
                break;
            case R.id.btn_last_recon:
                xml_Type = LAST_REC;
                startActivity(new Intent(this, XmlDataActivity.class));
                break;

            default:
                break;
        }
    }

    public void printImage()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setAction(Consts.CARD_ACTION);
        intent.putExtra(ThirdTag.CHANNEL_ID,"acquire");
        intent.putExtra(ThirdTag.TRANS_TYPE, 9000);
        intent.putExtra(ThirdTag.OUT_ORDERNO, "printImage");
        intent.putExtra(ThirdTag.IS_OPEN_ADMIN, 1);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.sample_receipt);
        intent.putExtra("imagePath", uri.toString());
        try
        {
            startActivityForResult(intent, 12);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        data.getStringExtra("123");
     }


}
