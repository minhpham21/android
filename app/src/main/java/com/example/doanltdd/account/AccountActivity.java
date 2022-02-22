package com.example.doanltdd.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanltdd.R;
import com.example.doanltdd.function.BottomNavigationViewSetting;
import com.example.doanltdd.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AccountActivity extends AppCompatActivity {
    private static final String TAG = "AccountActivity";
    private static final int ACTIVITY_NUMBER = 4;


    public void onClick_infor(View view) {
        Toast.makeText(AccountActivity.this,"Thông tin cá nhân",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AccountActivity.this, InformationActivity.class);
        intent.putExtra("KeyUser",  value1);
        startActivity(intent);

    }
    public void onClick_tool(View view) {
        Toast.makeText(AccountActivity.this,"Chưa cài đặt thực thi",Toast.LENGTH_SHORT).show();
    }
    public void onClick_setting(View view) {
        Toast.makeText(AccountActivity.this,"Chưa cài đặt thực thi",Toast.LENGTH_SHORT).show();
    }
    public void onClick_help(View view) {
        Toast.makeText(AccountActivity.this,"Thông tin liên lạc",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AccountActivity.this, AboutActivity.class);
        startActivity(intent);
    }
    public void onClick_about(View view) {
        Toast.makeText(AccountActivity.this,"Click Giới thiệu",Toast.LENGTH_SHORT).show();
    }

    TextView txtaccount;
    String value1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        setupBottomNavigationView();

        txtaccount = (TextView) findViewById(R.id.txt_account);
        Intent intent = getIntent();
        value1 = intent.getStringExtra("Key1");
        txtaccount.setText("Xin chào:  "+value1 + " _<3_");

    }

    private void setupBottomNavigationView(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewSetting.enableNavigation(AccountActivity.this, bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUMBER);
        menuItem.setChecked(true);
    }
}
