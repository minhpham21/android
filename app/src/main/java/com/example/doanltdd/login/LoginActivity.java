package com.example.doanltdd.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanltdd.Database;
import com.example.doanltdd.R;
import com.example.doanltdd.Transaction.TransactionActivity;
import com.example.doanltdd.account.AccountActivity;

public class LoginActivity extends AppCompatActivity {

    Button btndangnhap, btndangki, btndangnhapkhongtk;
    EditText edt_user, edt_password;


    String ten, mk, keyUser;
    int keyStatus;
    Database databaselogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btndangnhap = (Button) findViewById(R.id.button_login);
        btndangki = (Button) findViewById(R.id.button_sign_up);
        btndangnhapkhongtk = (Button) findViewById(R.id.button_loginNoUser);

        edt_user = (EditText) findViewById(R.id.textUser);
        edt_password = (EditText) findViewById(R.id.textPass);


        databaselogin = new Database(this, "login.sqlite", null, 1);

        databaselogin.QuerryData("CREATE TABLE IF NOT EXISTS Login1(" +
                "User VARCHAR(20) PRIMARY KEY, " +
                "Pass VARCHAR(20), " +
                "Status INT)");

//        Intent intent = getIntent();
//        String str_status = intent.getStringExtra("KeyStatus");
//        keyUser = intent.getStringExtra("KeyUser");
//
//        keyStatus = Integer.parseInt(str_status);
//        GetLoginSignIn(keyUser, 0);

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // xét trường hợp cho 1 tài khoản mặc định
                if (edt_user.getText().toString().equals("admin") && edt_password.getText().toString().equals("admin")) {
                    Toast.makeText(LoginActivity.this, "Bạn đã đăng nhập thành công!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                    intent.putExtra("Key1",  edt_user.getText().toString() );
                    startActivity(intent);
                    LoginActivity.this.onStop();

                }
                //Xét trường hợp cho Tài khoản mật khẩu mới tạo:
                else if (edt_user.getText().toString().equals(ten) && edt_password.getText().toString().equals(mk)) {
                    Toast.makeText(LoginActivity.this, "Bạn đã đăng nhập thành công!!", Toast.LENGTH_SHORT).show();
                    UpdateStatus1(edt_user);
                    UpdateStatus0(edt_user);
                    Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                    intent.putExtra("Key1",  edt_user.getText().toString() );
                    startActivity(intent);
                    LoginActivity.this.onStop();
                }
                // Xét trường hợp cho tài khoản có trong Database;
                else if (TestSignIn(edt_user, edt_password)) {
                    Toast.makeText(LoginActivity.this, "Bạn đã đăng nhập thành công!!", Toast.LENGTH_SHORT).show();
                    UpdateStatus1(edt_user);
                    UpdateStatus0(edt_user);
                    Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                    intent.putExtra("Key1",  edt_user.getText().toString());
                    startActivity(intent);
                    LoginActivity.this.onStop();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại\nKiểm tra lại tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(LoginActivity.this);
                dialog.setTitle("Hộp thoại xử lí");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.activity_sign_up);

                final EditText edt_newUser, edt_newPass, edt_testPass;
                final Button btnSignUp, btnCancel;

                edt_newUser = (EditText) dialog.findViewById(R.id.newUser);
                edt_newPass = (EditText) dialog.findViewById(R.id.newPass);
                edt_testPass = (EditText) dialog.findViewById(R.id.testPass);

                btnSignUp = (Button) dialog.findViewById(R.id.btn_SingUp);
                btnCancel = (Button) dialog.findViewById(R.id.btn_Cancel);
                dialog.show();
                btnSignUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edt_newUser.getText().toString().equals("admin")) {
                            Toast.makeText(LoginActivity.this, "Tên đăng nhập bị trùng", Toast.LENGTH_SHORT).show();
                            edt_newUser.setText("");
                            edt_newUser.requestFocus();
                        } else if(edt_newUser.getText().toString().equals("")){
                            Toast.makeText(LoginActivity.this, "Chưa nhập Tên đăng nhập mới", Toast.LENGTH_SHORT).show();
                        }
                        else if (edt_newPass.getText().toString().trim().equals("") || edt_testPass.getText().toString().trim().equals("")) {
                            Toast.makeText(LoginActivity.this, "Chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
                            edt_newPass.requestFocus();
                        } else if (TestSignUp(edt_newUser)) {
                            if (edt_newPass.getText().toString().trim().equals(edt_testPass.getText().toString().trim())) {
                                ten = edt_newUser.getText().toString().trim();
                                mk = edt_newPass.getText().toString().trim();
                                edt_user.setText(ten);
                                edt_password.setText(mk);
                                Toast.makeText(LoginActivity.this, "Đăng kí thành công!!", Toast.LENGTH_SHORT).show();
                                databaselogin.QuerryData("INSERT INTO Login1 VALUES('" + edt_newUser.getText().toString().trim() + "', '" + edt_newPass.getText().toString().trim() + "', 0)");
                                dialog.cancel();
                            } else {
                                Toast.makeText(LoginActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                                edt_newPass.setText("");
                                edt_testPass.setText("");
                                edt_newPass.requestFocus();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Tên đăng nhập bị trùng", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }
        });

        btndangnhapkhongtk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Đã dăng nhập Không tài khoản", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, TransactionActivity.class);
                //DeleteAllData();
                startActivity(intent);

            }
        });
    }

    private boolean TestSignUp(EditText editText) {
        Cursor readData = databaselogin.GetData("SELECT * FROM Login1");
        while (readData.moveToNext()) {
            String user = readData.getString(0);
            if (editText.getText().toString().equals(user))
                return false;
        }
        return true;
    }

    private boolean TestSignIn(EditText edituser, EditText edtpassword) {
        Cursor readData = databaselogin.GetData("SELECT * FROM Login1");
        while (readData.moveToNext()) {
            String user = readData.getString(0);
            String pass = readData.getString(1);
            if (edituser.getText().toString().equals(user) && edtpassword.getText().toString().equals(pass) )
                return true;
        }
        return false;
    }

    private void DeleteAllData() {
        Cursor readData = databaselogin.GetData("SELECT * FROM Login1");
        while (readData.moveToNext()) {
            String user = readData.getString(0);
            databaselogin.QuerryData("DELETE FROM Login1 WHERE User = '" + user + "'");
        }
    }

    private void UpdateStatus1(EditText editText) {
        Cursor readData = databaselogin.GetData("SELECT * FROM Login1");
        while (readData.moveToNext()) {
            databaselogin.QuerryData("UPDATE Login1 SET Status = 1 WHERE User = '" + editText.getText().toString() + "';");
        }
    }
    private void UpdateStatus0(EditText editText) {
        Cursor readData = databaselogin.GetData("SELECT * FROM Login1");
        while (readData.moveToNext()) {
            String user = readData.getString(0);
            if (user.equals(editText.getText().toString())== false) {
                databaselogin.QuerryData("UPDATE Login1 SET Status = 0 WHERE User = '" + user + "';");
            }
        }
    }

    private void GetLoginSignIn(String keyuser, int keystatus) {

        Cursor readData = databaselogin.GetData("SELECT * FROM Login1");
        databaselogin.QuerryData("UPDATE Login1 SET Status ="+ keystatus +" WHERE User = '" + keyuser + "';");
        while (readData.moveToNext()) {
            String user = readData.getString(0);
            int status = readData.getInt(2);
            if (status == 1) {
                Toast.makeText(LoginActivity.this, "Bạn đã đăng nhập thành công!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                intent.putExtra("Key1",  user);
                startActivity(intent);
                LoginActivity.this.onStop();
            }
        }
        return;

    }
}