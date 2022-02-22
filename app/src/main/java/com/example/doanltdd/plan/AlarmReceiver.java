package com.example.doanltdd.plan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.text.BreakIterator;


public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String value = PlanActivity.edt_title1.getText().toString();
        Toast.makeText(context, "THÔNG BÁO KẾ HOẠCH: " + value, Toast.LENGTH_LONG).show();
        PlanActivity.swOnOff1.setChecked(false);
    }

    public static void addNotification1() {
        String strTitle = PlanActivity.edt_title1.getText().toString();
        String strMsg = PlanActivity.edt_title1.getText().toString();
        //Intent notificationIntent = new Intent(PlanActivity.class, )
    }




}


