package com.intersoft.acquire.caller;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.intersoft.acquire.caller.common.Consts;
import com.intersoft.acquire.caller.common.ThirdTag;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SaleActivity extends AppCompatActivity {
    final static int TYPE = 2;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_channel)
    TextView tvChannel;
    @BindView(R.id.tv_action)
    TextView tvAction;
    @BindView(R.id.tv_outOrderNo)
    TextView tvOutOrderNo;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.switchInsert)
    SwitchCompat switchInsert;
    @BindView(R.id.switchRfPsw)
    SwitchCompat switchRfPsw;
    @BindView(R.id.tv_response)
    TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        ButterKnife.bind(this);
        tvAction.setText(Consts.CARD_ACTION);
        tvType.setText(TYPE + "");
        tvChannel.setText("acquire");
        //        //External order number ,using for Search index
        tvOutOrderNo.setText(System.currentTimeMillis() + "");
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setPackage(Consts.PACKAGE);
        intent.setAction(Consts.CARD_ACTION);
        intent.putExtra(ThirdTag.CHANNEL_ID,"acquire");
        intent.putExtra(ThirdTag.TRANS_TYPE, Integer.parseInt(tvType.getText().toString()));
        intent.putExtra(ThirdTag.OUT_ORDERNO, tvOutOrderNo.getText());
        if (etAmount.getText().length()>0){
            intent.putExtra(ThirdTag.AMOUNT, Long.parseLong(etAmount.getText().toString()));
        }
        intent.putExtra(ThirdTag.INSERT_SALE, switchInsert.isChecked());
        intent.putExtra(ThirdTag.RF_FORCE_PSW, switchRfPsw.isChecked());
        startActivityForResult(intent, 12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        data.getStringExtra("123");
        Log.d("DEBUG","11111111111111111111111111111111111");
        Log.d("DEBUG",data.getStringExtra(ThirdTag.JSON_DATA));
        tvResponse.setText("Sale Response："+data.getStringExtra(ThirdTag.JSON_DATA));

       // tvResponse.setText("Response："+data.getExtras().toString());
    }
}
