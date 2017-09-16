package com.oldmen.attendancemanager;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.oldmen.attendancemanager.utils.DateFormatter;

public class MainActivity extends AppCompatActivity {

    private TextView toolbarDate;
    private TextView unmarkedTabTitle;
    private TextView unmarkedNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (toolbar != null) {
            getSupportActionBar().setTitle(null);
        }

        toolbarDate = (TextView) findViewById(R.id.toolbar_date);

        String date = DateFormatter.changeFormat(System.currentTimeMillis());
        toolbarDate.setText(date);

        View unmarkedTab = LayoutInflater.from(this).inflate(R.layout.unmarked_tab, null);
        View intimeTab = LayoutInflater.from(this).inflate(R.layout.intime_tab, null);
        View lateTab = LayoutInflater.from(this).inflate(R.layout.late_tab, null);
        View xTab = LayoutInflater.from(this).inflate(R.layout.x_tab, null);

        unmarkedTabTitle = unmarkedTab.findViewById(R.id.all_students_number);
        unmarkedNumber = unmarkedTab.findViewById(R.id.unmarked_students_number);

        TabHost tabHost = (TabHost) findViewById(R.id.tab_host);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");

        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator(unmarkedTab);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator(intimeTab);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setContent(R.id.tab3);
        tabSpec.setIndicator(lateTab);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag4");
        tabSpec.setContent(R.id.tab4);
        tabSpec.setIndicator(xTab);
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addStudentsToDatabase(){

        Resources res = getResources();
        String[] categoriesArray = res.getStringArray(R.array.students_names);


    }
}
