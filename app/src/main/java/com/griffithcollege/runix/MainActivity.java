package com.griffithcollege.runix;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    LocationManager lm;
    GPSLocationListener gps;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        lm = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        String GPStracks = "GPStracks";

        File f = new File(Environment.getExternalStorageDirectory(), GPStracks);
        if (!f.exists()) {
            f.mkdirs();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.start);
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
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
    // MAIN MENU
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) // HOME
        {
            // From StatsActivity to MainActivity
            // Already in MainActivity
        }
        else if (id == R.id.nav_stats) // STATS
        {
            // From StatsActivity to StatsActivity
            Intent intent = new Intent(MainActivity.this, StatsActivity.class);
            finish();
            startActivity(intent);
        }
        else if (id == R.id.nav_tips) // TIPS (feature)
        {
            // From StatsActivity to TipsActivity
            // TO DO
        }
        else if (id == R.id.nav_maps) // MAPS (feature)
        {
            // From StatsActivity to MapsActivity
            // TO DO
        }
        else if (id == R.id.nav_settings) // SETTINGS
        {
            // From StatsActivity to SettingsActivity
            // TO DO
        }
        else if (id == R.id.nav_about) // ABOUT
        {
            // From StatsActivity to AboutActivity
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            finish();
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void gpsStart(View view) {
        gps = new GPSLocationListener();
        try {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 5, gps);
        } catch (SecurityException se) {
            se.printStackTrace();
        }
    }

    public void gpsStop(View view){
        lm.removeUpdates(gps);
        gps = null;
    }
}
