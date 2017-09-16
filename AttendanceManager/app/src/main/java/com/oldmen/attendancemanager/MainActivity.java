package com.oldmen.attendancemanager;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.oldmen.attendancemanager.utils.DateFormatter;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private TabHost tabHost;
    private View unmarkedTab;
    private View intimeTab;
    private View latedTab;
    private View notCameTab;

    private TextView toolbarDate;
    private TextView unmarkedTabTitle;
    private TextView unmarkedNumber;

    private RecyclerView unmarkedRecycler;
    private RecyclerView intimeRecycler;
    private RecyclerView latedRecycler;
    private RecyclerView notCameRecycler;

    private RecyclerAdapter unmarkedAdapter;
    private RecyclerAdapter intimeAdapter;
    private RecyclerAdapter latedAdapter;
    private RecyclerAdapter notCameAdapter;

    private Realm realm;
    private RealmResults<Student> unmarkedStudents;
    private RealmResults<Student> intimeStudents;
    private RealmResults<Student> latedStudents;
    private RealmResults<Student> notCameStudents;

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

        realm = Realm.getDefaultInstance();

        if (realm.where(Student.class).findAll().size() == 0)
            addStudentsToDatabase();

        unmarkedStudents = realm.where(Student.class).contains("state", Const.UNMARKED).findAll();
        intimeStudents = realm.where(Student.class).contains("state", Const.IN_TIME).findAll();
        latedStudents = realm.where(Student.class).contains("state", Const.LATED).findAll();
        notCameStudents = realm.where(Student.class).contains("state", Const.NOT_CAME).findAll();


        unmarkedTab = LayoutInflater.from(this).inflate(R.layout.unmarked_tab, null);
        intimeTab = LayoutInflater.from(this).inflate(R.layout.intime_tab, null);
        latedTab = LayoutInflater.from(this).inflate(R.layout.late_tab, null);
        notCameTab = LayoutInflater.from(this).inflate(R.layout.x_tab, null);

        unmarkedRecycler = (RecyclerView) findViewById(R.id.unmarked_students);
        unmarkedRecycler.addItemDecoration(new SimpleDividerItemDecoration(this));
        intimeRecycler = (RecyclerView) findViewById(R.id.intime_students);
        intimeRecycler.addItemDecoration(new SimpleDividerItemDecoration(this));
        latedRecycler = (RecyclerView) findViewById(R.id.lated_students);
        latedRecycler.addItemDecoration(new SimpleDividerItemDecoration(this));
        notCameRecycler = (RecyclerView) findViewById(R.id.not_came_students);
        notCameRecycler.addItemDecoration(new SimpleDividerItemDecoration(this));

        tabHost = (TabHost) findViewById(R.id.tab_host);
        tabHost.setup();

        createtabHost();

        unmarkedTabTitle = (TextView) tabHost.getTabWidget().findViewById(R.id.all_students_number);
        unmarkedNumber = (TextView) tabHost.getTabWidget().findViewById(R.id.unmarked_students_number);

        int unmarkedStdNumber = unmarkedStudents.size();
        int intimeStdNumber = intimeStudents.size();
        int latedStdNumber = latedStudents.size();
        int notCameStdNumber = notCameStudents.size();
        int allStdNumber = unmarkedStdNumber + intimeStdNumber
                + latedStdNumber + notCameStdNumber;

        unmarkedNumber.setText(String.valueOf(unmarkedStdNumber));
        unmarkedTabTitle.setText("out of " + String.valueOf(allStdNumber));

        unmarkedRecycler.setLayoutManager(new LinearLayoutManager(this));
        unmarkedAdapter = new RecyclerAdapter(unmarkedStudents);
        unmarkedRecycler.setAdapter(unmarkedAdapter);
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

    private void addStudentsToDatabase() {

        Resources res = getResources();
        final String[] nameArray = res.getStringArray(R.array.students_names);
        final int[] imgIdArray = new int[]{
                R.drawable.student1,
                R.drawable.student2,
                R.drawable.student3,
                R.drawable.student4,
                R.drawable.student5,
                R.drawable.student6,
                R.drawable.student7,
                R.drawable.student8,
                R.drawable.student9
        };

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Student std1 = realm.createObject(Student.class);
                std1.setName(nameArray[0]);
                std1.setImgId(imgIdArray[0]);
                std1.setState(Const.UNMARKED);

                Student std2 = realm.createObject(Student.class);
                std2.setName(nameArray[1]);
                std2.setImgId(imgIdArray[1]);
                std2.setState(Const.UNMARKED);

                Student std3 = realm.createObject(Student.class);
                std3.setName(nameArray[2]);
                std3.setImgId(imgIdArray[2]);
                std3.setState(Const.UNMARKED);

                Student std4 = realm.createObject(Student.class);
                std4.setName(nameArray[3]);
                std4.setImgId(imgIdArray[3]);
                std4.setState(Const.UNMARKED);

                Student std5 = realm.createObject(Student.class);
                std5.setName(nameArray[4]);
                std5.setImgId(imgIdArray[4]);
                std5.setState(Const.UNMARKED);

                Student std6 = realm.createObject(Student.class);
                std6.setName(nameArray[5]);
                std6.setImgId(imgIdArray[5]);
                std6.setState(Const.UNMARKED);

                Student std7 = realm.createObject(Student.class);
                std7.setName(nameArray[6]);
                std7.setImgId(imgIdArray[6]);
                std7.setState(Const.UNMARKED);

                Student std8 = realm.createObject(Student.class);
                std8.setName(nameArray[7]);
                std8.setImgId(imgIdArray[7]);
                std8.setState(Const.UNMARKED);

                Student std9 = realm.createObject(Student.class);
                std9.setName(nameArray[8]);
                std9.setImgId(imgIdArray[8]);
                std9.setState(Const.UNMARKED);

            }
        });

    }

    private void createtabHost() {
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
        tabSpec.setIndicator(latedTab);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag4");
        tabSpec.setContent(R.id.tab4);
        tabSpec.setIndicator(notCameTab);
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);
    }
}
