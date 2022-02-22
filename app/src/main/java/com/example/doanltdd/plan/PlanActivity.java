package com.example.doanltdd.plan;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanltdd.Database;
import com.example.doanltdd.R;
import com.example.doanltdd.function.BottomNavigationViewSetting;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PlanActivity extends AppCompatActivity {

    // Xử lí logic phần planActivity;

    private static final String TAG = "PlanActivity";
    private static final int ACTIVITY_NUMBER = 3;

    public static TextView txtngay1, txtngay2, txtgio1, txtgio2;
    public static EditText edt_title1, edt_title2, edt_note1,edt_note2;
    public static Switch swOnOff1, swOnOff2;

    // Khai báo, khỏi tạo database SQLite
    public Database database1, database2;

    public AlarmManager alarmManager;

    public PendingIntent pendingIntent1, pendingIntent2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        setupBottomNavigationView();


        edt_note1 = (EditText)findViewById(R.id.edt_note1);
        edt_note2 = (EditText)findViewById(R.id.edt_note2);
        edt_title1 = (EditText)findViewById(R.id.edt_title1);
        edt_title2 = (EditText)findViewById(R.id.edt_title2);

        txtngay1 = (TextView)findViewById(R.id.txt_ngay1);
        txtngay2 = (TextView)findViewById(R.id.txt_ngay2);
        txtgio1 = (TextView)findViewById(R.id.txt_gio1);
        txtgio2 = (TextView)findViewById(R.id.txt_gio2);

        swOnOff1 = (Switch)findViewById(R.id.sw_OnOff1);
        swOnOff2 = (Switch)findViewById(R.id.sw_OnOff2);

        //Khoi tao AlarmReceiver
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        final Intent intent = new Intent(PlanActivity.this, AlarmReceiver.class);
        final Intent intent2 = new Intent(PlanActivity.this, AlarmReceiver2.class);

        //Khởi tạo:
        database1 = new Database(this, "kehoach1.sqlite", null, 1);
        //Tạo bảng:
        database1.QuerryData("CREATE TABLE IF NOT EXISTS KeHoach1(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ChuDe VARCHAR(100), " +
                "Ngay VARCHAR(10), " +
                "Gio VARCHAR(5), " +
                "NoiDung VARCHAR(200))");

        database2 = new Database(this, "kehoach2.sqlite", null, 1);
        //Tạo băng:
        database2.QuerryData("CREATE TABLE IF NOT EXISTS KeHoach2(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ChuDe VARCHAR(100), " +
                "Ngay VARCHAR(10), " +
                "Gio VARCHAR(5), " +
                "NoiDung VARCHAR(200))");

        // database.QuerryData("INSERT INTO KeHoach VALUES(null, 'Chủ đề 22222', '01/01/2020', '00:00', 'Nội dung 2222') ");
        GetDataKeHoach1();
        GetDataKeHoach2();

        //createNotificationChannel();


        txtngay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay(txtngay1);
            }
        });

        txtngay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay(txtngay2);
            }
        });

        txtgio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonGio(txtgio1);
            }
        });

        txtgio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonGio(txtgio2);
            }
        });

        swOnOff1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    if (edt_title1.getText().toString() == "" || txtngay1.getText().toString() == "" || txtgio1.getText() == "") {
                        Toast.makeText(PlanActivity.this, "Thiếu dữ liệu!!!", Toast.LENGTH_SHORT).show();
                        swOnOff1.setChecked(false);
                    }
                    else {
                        database1.QuerryData("INSERT INTO KeHoach1 VALUES(null, '" + edt_title1.getText() + "', '" + txtngay1.getText() + "', '" + txtgio1.getText() + "', '" + edt_note1.getText() + "') ");
                        GetDataKeHoach1();


                        String i = txtngay1.getText().toString() + " " + txtgio1.getText().toString() + ":00";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        try {
                            simpleDateFormat.parse(i);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date date = new Date(i);
                        long milliseconds = date.getTime() - System.currentTimeMillis();
                        pendingIntent1 = PendingIntent.getBroadcast(
                                PlanActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
                        );
                        alarmManager.set(AlarmManager.RTC_WAKEUP, milliseconds, pendingIntent1);
                        Toast.makeText(PlanActivity.this,"Đã đặt lịch Kế hoạch 1:\n Sau: " + milliseconds + " milliseconds", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    DeleteAllData1();
                    pendingIntent1.getBroadcast(PlanActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT).cancel();
                }




            }
        });

        swOnOff2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    if (edt_title2.getText().toString() == "" || txtngay2.getText().toString() == "" || txtgio2.getText() == "") {
                        Toast.makeText(PlanActivity.this, "Thiếu dữ liệu!!!", Toast.LENGTH_SHORT).show();
                        swOnOff2.setChecked(false);
                    }
                    else {
                        database2.QuerryData("INSERT INTO KeHoach2 VALUES(null, '" + edt_title2.getText() + "', '" + txtngay2.getText() + "', '" + txtgio2.getText() + "', '" + edt_note2.getText() + "') ");
                        GetDataKeHoach2();
                        String i = txtngay2.getText().toString() + " " + txtgio2.getText().toString() + ":00";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        try {
                            simpleDateFormat.parse(i);


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date date = new Date(i);
                        long milliseconds = date.getTime()- System.currentTimeMillis();

                        pendingIntent2 = PendingIntent.getBroadcast(
                                PlanActivity.this, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT
                        );
                        alarmManager.set(AlarmManager.RTC_WAKEUP, milliseconds, pendingIntent2);
                        Toast.makeText(PlanActivity.this,"Đã đặt lịch Kế hoạch 2:\n Sau: " + milliseconds + " milliseconds", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    DeleteAllData2();
                    pendingIntent2.getBroadcast(PlanActivity.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT).cancel();

                }

            }
        });
    }

    public void chonNgay(final TextView txttext) {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                txttext.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }


    private void chonGio(final TextView txttext){
        final Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);


        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                calendar.set(0,0,0, hourOfDay, minute);
                txttext.setText(simpleDateFormat.format(calendar.getTime())) ;
            }
        }, gio, phut, true);
        timePickerDialog.show();
    }
    //
    private void GetDataKeHoach1() {
        Cursor dataKeHoach = database1.GetData("SELECT * FROM KeHoach1");
        while (dataKeHoach.moveToNext()){
            String chuDe = dataKeHoach.getString(1);
            String ngay = dataKeHoach.getString(2);
            String gio = dataKeHoach.getString(3);
            String noidung = dataKeHoach.getString(4);
            String a = "Đã đặt lịch:\n" + chuDe + " " + ngay + " "+ gio + " "+ noidung;
            Toast.makeText(this, a, Toast.LENGTH_LONG).show();
            edt_title1.setText(chuDe);
            edt_note1.setText(noidung);
            txtngay1.setText(ngay);
            txtgio1.setText(gio);
            if(txtgio1.getText().toString() != "")
                swOnOff1.setChecked(true);
        }
    }

    private void DeleteAllData1() {
        Cursor dataKeHoach = database1.GetData("SELECT * FROM KeHoach1");
        while (dataKeHoach.moveToNext()){
            String chuDe = dataKeHoach.getString(1);
            database1.QuerryData("DELETE FROM KeHoach1 WHERE ChuDe = '" + chuDe +"'");
        }
        edt_title1.setText("");
        txtgio1.setText("");
        txtngay1.setText("");
        edt_note1.setText("");
        Toast.makeText(this, "Đã huỷ Kế hoạch 1!", Toast.LENGTH_LONG).show();
    }

    private void GetDataKeHoach2() {
        Cursor dataKeHoach = database2.GetData("SELECT * FROM KeHoach2");
        while (dataKeHoach.moveToNext()){
            String chuDe = dataKeHoach.getString(1);
            String ngay = dataKeHoach.getString(2);
            String gio = dataKeHoach.getString(3);
            String noidung = dataKeHoach.getString(4);
            String a = "Đã đặt lịch:\n" + chuDe + " " + ngay + " "+ gio + " "+ noidung;
            Toast.makeText(this, a, Toast.LENGTH_LONG).show();
            edt_title2.setText(chuDe);
            edt_note2.setText(noidung);
            txtngay2.setText(ngay);
            txtgio2.setText(gio);
            if(txtgio2.getText().toString() != "")
                swOnOff2.setChecked(true);
        }
    }

    private void DeleteAllData2() {
        Cursor dataKeHoach = database2.GetData("SELECT * FROM KeHoach2");
        while (dataKeHoach.moveToNext()){
            String chuDe = dataKeHoach.getString(1);
            database2.QuerryData("DELETE FROM KeHoach2 WHERE ChuDe = '" + chuDe +"'");
        }
        Toast.makeText(this, "Đã huỷ Kế hoạch 2!", Toast.LENGTH_LONG).show();
        edt_title2.setText("");
        txtgio2.setText("");
        txtngay2.setText("");
        edt_note2.setText("");
    }


//    private void createNotificationChannel() {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            CharSequence name = getString(R.string.channel_name);
//            String description = getString(R.string.channel_description);
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel("Kế hoạch", name, importance);
//            channel.setDescription(description);
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }



    private void setupBottomNavigationView(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewSetting.enableNavigation(PlanActivity.this, bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUMBER);
        menuItem.setChecked(true);
    }


}
