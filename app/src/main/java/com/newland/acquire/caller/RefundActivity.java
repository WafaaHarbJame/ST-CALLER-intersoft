package com.newland.acquire.caller;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.widget.EditText;
import android.widget.TextView;

import com.newland.acquire.caller.common.Consts;
import com.newland.acquire.caller.common.ThirdTag;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RefundActivity extends AppCompatActivity {
    final static int TYPE = 6;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_channel)
    TextView tvChannel;
    @BindView(R.id.tv_action)
    TextView tvAction;
    @BindView(R.id.tv_outOrderNo)
    TextView tvOutOrderNo;
    @BindView(R.id.switchisAdminPin)
    SwitchCompat switchisAdminPin;
    @BindView(R.id.tv_response)
    TextView tvResponse;

    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.et_oriReferenceNo)
    EditText etOriReferenceNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);
        ButterKnife.bind(this);

        tvAction.setText(Consts.CARD_ACTION);
        tvType.setText(TYPE + "");
        tvChannel.setText("acquire");
        //自定义外部订单号，作为查找索引
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

        if (etOriReferenceNo.getText().length()>0){
            intent.putExtra(ThirdTag.OLD_REFERENCE_NO, etOriReferenceNo.getText().toString());
        }


        intent.putExtra(ThirdTag.IS_OPEN_ADMIN, switchisAdminPin.isChecked());
        startActivityForResult(intent, 12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        data.getStringExtra("123");
        tvResponse.setText("返回："+data.getExtras().toString());
    }
}
