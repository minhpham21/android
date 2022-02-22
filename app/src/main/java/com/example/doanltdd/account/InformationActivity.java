package com.example.doanltdd.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doanltdd.R;
import com.example.doanltdd.login.LoginActivity;

public class InformationActivity extends AppCompatActivity {

    TextView txtUser;
    Button btn_changepassword, btn_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        txtUser = (TextView) findViewById(R.id.txtUser);
        btn_changepassword = (Button) findViewById(R.id.btn_ChangePassword);
        btn_logout = (Button) findViewById(R.id.btn_LogOut);


        Intent intent = getIntent();
        final String valueUser = intent.getStringExtra("KeyUser");
        final String valueStatus = "0";
        txtUser.setText(valueUser);

        btn_changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationActivity.this, LoginActivity.class);
                startActivity(intent);
                //intent.putExtra("KeyPass", )
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationActivity.this, LoginActivity.class);
                startActivity(intent);
//                intent.putExtra("KeyUser", valueUser );
//                intent.putExtra("KeyStatus", valueStatus);
            }
        });

    }
}