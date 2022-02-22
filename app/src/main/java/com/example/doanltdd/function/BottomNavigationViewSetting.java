package com.example.doanltdd.function;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.doanltdd.R;
import com.example.doanltdd.Transaction.TransactionActivity;
import com.example.doanltdd.account.AccountActivity;
import com.example.doanltdd.add.AddActivity;
import com.example.doanltdd.plan.PlanActivity;
import com.example.doanltdd.report.ReportActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationViewSetting {

    private static final String TAG = "BottomNavigationViewSetting";

        public static void  enableNavigation( final Context context, BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.transaction:// ACTIVITY_NUMBER: 0
                        Intent intent_transaction = new Intent(context, TransactionActivity.class);
                        context.startActivity(intent_transaction);
                        break;
                    case R.id.report:// ACTIVITY_NUMBER: 1
                        Intent intent_report = new Intent(context, ReportActivity.class);
                        context.startActivity(intent_report);
                        break;
                    case R.id.add:// ACTIVITY_NUMBER: 2
                        Intent intent_add = new Intent(context, AddActivity.class);
                        context.startActivity(intent_add);
                        break;
                    case R.id.plan:// ACTIVITY_NUMBER: 3
                        Intent intent_plan = new Intent(context, PlanActivity.class);
                        context.startActivity(intent_plan);
                        break;
                    case R.id.account:// ACTIVITY_NUMBER: 4
                        Intent intent_account = new Intent(context, AccountActivity.class);
                        context.startActivity(intent_account);
                        break;
                }
                return false;
            }
        });
    }
}
