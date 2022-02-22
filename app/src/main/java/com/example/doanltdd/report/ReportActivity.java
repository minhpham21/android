package com.example.doanltdd.report;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.doanltdd.R;
import com.example.doanltdd.Transaction.DayFragment;
import com.example.doanltdd.function.BottomNavigationViewSetting;
import com.example.doanltdd.function.SectionsPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;
import java.util.Date;


@RequiresApi(api = Build.VERSION_CODES.N)

public class ReportActivity extends AppCompatActivity {

    private static final String TAG = "ReportActivity";
    private static final int ACTIVITY_NUMBER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setupBottomNavigationView();
        setupViewPager();

    }

    private void setupBottomNavigationView(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewSetting.enableNavigation(ReportActivity.this, bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUMBER);
        menuItem.setChecked(true);
    }

    private void setupViewPager() {

        // Tạo fragment dữ cập nhật dữ liệu ngày tháng
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        sectionsPagerAdapter.addFragment(new DayReportActivity()); // Ngày hôm kia
        sectionsPagerAdapter.addFragment(new DayReportActivity()); // Ngày hôm qua
        sectionsPagerAdapter.addFragment(new DayReportActivity()); // ngay hôm nay

        ViewPager viewPager = (ViewPager) findViewById(R.id.content);
        viewPager.setAdapter(sectionsPagerAdapter);

        // Thanh tabs ngày tháng
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs_report);
        tabs.setupWithViewPager(viewPager);

        // sử lý ngày tháng
        Date date = new Date();

        //Định dạng ngày tháng
        String strDateFormat = "dd/MM/yyyy";
        Calendar calendar_1 = Calendar.getInstance();
        Calendar calendar_2 = Calendar.getInstance();
        Calendar calendar_3 = Calendar.getInstance();

        calendar_1.setTime(date);
        calendar_2.setTime(date);
        calendar_3.setTime(date);

        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);

        // Lùi 1 ngày
        calendar_2.roll(Calendar.DATE, -1);

        // Lùi 2 ngày
        calendar_3.roll(Calendar.DATE, -2);

        tabs.getTabAt(2).setText("Ngày hôm nay " + sdf.format(calendar_1.getTime()));
        tabs.getTabAt(1).setText("Ngày hôm qua " + sdf.format(calendar_2.getTime()));
        tabs.getTabAt(0).setText("Ngày hôm kia " + sdf.format(calendar_3.getTime()));
    }

}
