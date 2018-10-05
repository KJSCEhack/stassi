package me.myrhy.stassi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.IntStream;


class AttendanceEntry {
    public String name;
    public double percentAttended;
    public boolean attended;

    public AttendanceEntry(String name, double percentAttended, boolean attended) {
        this.name = name;
        this.percentAttended = percentAttended;
        this.attended = attended;
    }
}


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CalendarView view = (CalendarView) findViewById(R.id.calendar_view);

        Date today = new Date();
        final int thisMonth = today.getMonth();
        int thisYear = today.getYear();

        Date firstDate = new Date();
        firstDate.setDate(1);
        firstDate.setMonth(thisMonth);
        firstDate.setYear(thisYear);

        Date lastDate = new Date();
        lastDate.setMonth(thisMonth);
        lastDate.setYear(thisYear);


        int[] day31 = {1, 3, 5, 7, 8, 10, 12};
        boolean is31 = false;

        for(int i = 0; i < day31.length; i++) {
            if (day31[i] == thisMonth + 1) {
                is31 = true;
                break;
            }
        }


        if (is31) {
            lastDate.setDate(31);
        }
        else {
            if (thisMonth != 2) {
                lastDate.setDate(30);
            }
            else {
                lastDate.setDate(28);
            }
        }


        view.setMaxDate(lastDate.getTime());
        view.setMinDate(firstDate.getTime());

        AttendanceEntry[] entries = new AttendanceEntry[] {
            new AttendanceEntry("CG", 52.3, true),
            new AttendanceEntry("DCN", 55.3, true),
            new AttendanceEntry("AD", 23.3, true)
        };

        TableLayout tl = (TableLayout) findViewById(R.id.table_layout);

        for (int i = 0; i < entries.length; i++) {
            TableRow tr = new TableRow(this);

            AttendanceEntry entry = entries[i];

            TextView label = new TextView(this);
            label.setText(entry.name);
            tr.addView(label);

            TextView price = new TextView(this);
            price.setText(entry.percentAttended + "%");
            tr.addView(price);

            TextView offer = new TextView(this);
            offer.setText(String.valueOf(entry.attended));
            tr.addView(offer);

            tl.addView(tr);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
