package com.lightningfast.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.lightningfast.R;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.databinding.ActivityAddressBinding;
import com.lightningfast.view.AddressSelectPopWindow;

public class AddressActivity extends BaseActivity<ActivityAddressBinding> {
    private String mProvenceId;
    private String mCityId;
    private String mCountryId;
    private String address;
    private String mDetails="";
    private AddressSelectPopWindow mPopWindow;
    public static final int ADDRESS_REQUESTCODE=0x0000125;
    public static void actionStart(Activity activity, int resultCode) {
        Intent intent = new Intent(activity, AddressActivity.class);
        activity.startActivityForResult(intent,resultCode);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTextTitleView("编辑地址",DEFAULT_TITLE_TEXT_COLOR);
    }

    @Override
    protected void initViews() {
        mPopWindow = new AddressSelectPopWindow(AddressActivity.this);
        mDataBinding.common.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.addressReset();
                mPopWindow.showPopupWindow(mDataBinding.getRoot());
            }
        });
        mPopWindow.setWheelChangeListener(new AddressSelectPopWindow.WheelChangeListener() {

            @Override
            public void onWheelSelected(String provinceNo, String cityNo, String countyNo, String provinceData, String cityData, String countyData) {
                mDataBinding.common.setText(provinceData + cityData+countyData);
                address=provinceData + cityData+countyData;
                mProvenceId=provinceNo;
                mCityId=cityNo;
                mCountryId=countyNo;
            }
        });
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
        mDataBinding.btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(address)){
                    if (!TextUtils.isEmpty(mDataBinding.details.getText())){
                        mDetails=mDataBinding.details.getText().toString();
                    }else {
                        Toast.makeText(mContext, "请将地址填写完整", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("mProvence",mProvenceId);
                    intent.putExtra("mCity",mCityId);
                    intent.putExtra("mCountry",mCountryId);
                    intent.putExtra("mDetails",mDetails);
                    intent.putExtra("address",address+mDetails);
                    setResult(RESULT_OK,intent);
                    finish();
                }else {
                    Toast.makeText(mContext, "请将地址填写完整", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address;
    }
}
