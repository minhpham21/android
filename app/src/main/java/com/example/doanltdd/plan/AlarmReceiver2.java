package com.example.doanltdd.plan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String value = PlanActivity.edt_title2.getText().toString();
        Toast.makeText(context, "THÔNG BÁO KẾ HOẠCH: " + value, Toast.LENGTH_LONG).show();
        PlanActivity.swOnOff2.setChecked(false);
    }
}
