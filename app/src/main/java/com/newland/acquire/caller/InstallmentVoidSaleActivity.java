package com.newland.acquire.caller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.widget.EditText;
import android.widget.TextView;

import com.newland.acquire.caller.common.Consts;
import com.newland.acquire.caller.common.ThirdTag;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InstallmentVoidSaleActivity extends AppCompatActivity {
    final static int TYPE = 13;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_channel)
    TextView tvChannel;
    @BindView(R.id.tv_action)
    TextView tvAction;
    @BindView(R.id.tv_outOrderNo)
    TextView tvOutOrderNo;
    @BindView(R.id.et_oriVoucherNo)
    EditText etOriVoucherNo;
    @BindView(R.id.tv_response)
    TextView tvResponse;

    @BindView(R.id.switchisAdminPin)
    SwitchCompat switchisAdminPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installment_voidsale);
        ButterKnife.bind(this);

        tvType.setText(TYPE + "");
        tvAction.setText(Consts.INSTALLMENT_ACTION);
        tvChannel.setText("installment");
        //自定义外部订单号，作为查找索引
        tvOutOrderNo.setText(System.currentTimeMillis() + "");
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setPackage(Consts.PACKAGE);
        intent.setAction(Consts.INSTALLMENT_ACTION);
        intent.putExtra(ThirdTag.CHANNEL_ID,"installment");
        intent.putExtra(ThirdTag.TRANS_TYPE, Integer.parseInt(tvType.getText().toString()));
        intent.putExtra(ThirdTag.OUT_ORDERNO, tvOutOrderNo.getText());
        if (etOriVoucherNo.getText().length()>0){
            intent.putExtra(ThirdTag.OLD_TRACE_NO, etOriVoucherNo.getText().toString());
        }

        intent.putExtra(ThirdTag.IS_OPEN_ADMIN, switchisAdminPin.isChecked());
        startActivityForResult(intent, 12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        data.getStringExtra("123");
        tvResponse.setText("Response："+data.getExtras().toString());
    }
}
