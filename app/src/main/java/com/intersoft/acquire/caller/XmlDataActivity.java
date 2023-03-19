package com.intersoft.acquire.caller;
//CR# 15.09.2021 add file
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.SwitchCompat;

import android.widget.TextView;

import com.intersoft.acquire.caller.common.Consts;
import com.intersoft.acquire.caller.common.ThirdTag;

public class XmlDataActivity extends AppCompatActivity {


    private  String strXMLReceive;
    TextView tvXML;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_data);

          tvXML = (TextView)findViewById(R.id.tvXMLData);

        iniData();


    }

    private void iniData() {
        int RespCode=-1;
        if (MainActivity.xml_Type==MainActivity.LAST_TXN)
        {
            setTitle("LAST TRANSACTION");

        }
        else  if (MainActivity.xml_Type==MainActivity.LAST_REC)
        {
            setTitle("LAST RECONCILIATION");
        }




        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setAction(Consts.CARD_ACTION);
        intent.putExtra(ThirdTag.CHANNEL_ID,"acquire");
        intent.putExtra(ThirdTag.OUT_ORDERNO, "Last");
        intent.putExtra(ThirdTag.IS_OPEN_ADMIN, 1);





        if (MainActivity.xml_Type==MainActivity.LAST_TXN)
        {
            intent.putExtra(ThirdTag.TRANS_TYPE,  8001);
            RespCode =8001;

        }
        else  if (MainActivity.xml_Type==MainActivity.LAST_REC)
        {
            intent.putExtra(ThirdTag.TRANS_TYPE,  8002);
            RespCode =8002;
        }




        try {
            startActivityForResult(intent,  RespCode );
        }
       catch (Exception e)
       {

       }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


       // data.getStringExtra("123");

        if ( requestCode== 8001)
        {
            tvXML.setText(resultCode+data.getStringExtra(ThirdTag.XML_DATA));
        }
        else   if ( requestCode== 8002)
        {

            tvXML.setText(resultCode+data.getStringExtra(ThirdTag.XML_DATA));
        }

    }

}