package com.example.doanltdd.add;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanltdd.Database;
import com.example.doanltdd.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private static final String TAG = "AddActivity";
    private static final int ACTIVITY_NUMBER = 2;

    EditText edt_num, edt_content ;
    TextView edt_calendar;
    Button btn_save,btn_reset;
    ImageView back_add;
    RadioButton rd_thu, rd_chi;

    Database databaseTransaction;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edt_num = (EditText) findViewById(R.id.edt_num);
        rd_chi = (RadioButton) findViewById(R.id.rd_chi);
        rd_thu = (RadioButton) findViewById(R.id.rd_thu);
        edt_content = (EditText) findViewById(R.id.edt_content);
        edt_calendar = (TextView) findViewById(R.id.edt_calendar);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_reset = (Button)findViewById(R.id.btn_reset);

        edt_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay();
            }
        });

        back_add = (ImageView) findViewById(R.id.back_add);
        back_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Su kien click btn_Save...
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rd_chi.isChecked()) {
                    if(testSetData()) {
                        String text = "Đã thêm! GIAO DỊCH CHI: \n - Số tiền: " + edt_num.getText().toString() + "\n - Nội dung: " + edt_content.getText().toString() + "\n - Ngày: " + edt_calendar.getText().toString();
                        Toast.makeText(AddActivity.this, text, Toast.LENGTH_SHORT).show();
                        resetAcctivity();
                    }

                }
                else if (rd_thu.isChecked()){
                    if(testSetData()) {
                        String text = "Đã thêm! GIAO DỊCH THU: \n - Số tiền: " + edt_num.getText().toString() + "\n - Nội dung: " + edt_content.getText().toString() + "\n - Ngày: " + edt_calendar.getText().toString();
                        Toast.makeText(AddActivity.this, text, Toast.LENGTH_SHORT).show();
                        resetAcctivity();
                    }
                }
                else
                    Toast.makeText(AddActivity.this,"Chưa lựa chọn loại Giao dịch!!",Toast.LENGTH_SHORT).show();

            }
        });

        //Su kien click btn_Reset...
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAcctivity();
            }
        });


    }

    private void chonNgay() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edt_calendar.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private boolean testSetData() {
        if (edt_num.getText().toString().equals("") || edt_content.getText().toString().equals("") ||edt_calendar.getText().toString().equals("")) {
            Toast.makeText(AddActivity.this,"Thiếu dữ liệu nhập!!",Toast.LENGTH_SHORT).show();
            return false;

        }
        else {
            return true;
        }
    }
    private void resetAcctivity() {
        edt_num.setText("");
        edt_calendar.setText("");
        edt_content.setText("");
        rd_thu.isChecked();
        edt_num.requestFocus();
    }




}
