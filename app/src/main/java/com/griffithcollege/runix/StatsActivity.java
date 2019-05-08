package com.griffithcollege.runix;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class StatsActivity extends MainActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        ToolbarStat(); // Generation of Stats' Toolbar

        //Floating Action Button
        FloatingActionButton fab = findViewById(R.id.share);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // Set up TextView & Data
        AverageSpeed();
        TotalDistance();
    }

    public void ToolbarStat()
    {
        Toolbar toolbar = findViewById(R.id.toolbarStat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_runix_icon);

        //Button Open Menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.stat_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Navigation View
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void AverageSpeed()
    {
        // Recuperation of speed values
        // TO DO

        // Calculation of average speed
        // TO DO

        // Change TextView
        String averagespeed_str = "Average Speed : 12 km/h";
        SpannableString averagespeed_str_4textview =  new SpannableString(averagespeed_str);
        averagespeed_str_4textview.setSpan(new RelativeSizeSpan(1.5f), 15, averagespeed_str.length(), 0); // Set the size
        //averagespeed_str_4textview.setSpan(new ForegroundColorSpan(Color.RED), 15, averagespeed_str.length(), 0); // Set the color
        TextView textView_AverageSpeed = findViewById(R.id.AverageSpeed);
        textView_AverageSpeed.setText(averagespeed_str_4textview);
    }

    public void TotalDistance()
    {
        // Recuperation of distance
        // TO DO

        // Calculation of total distance
        // TO DO

        // Change TextView
        String totaldistance_str = "Total Distance\n 42 km";
        SpannableString totaldistance_str_4textview =  new SpannableString(totaldistance_str);
        totaldistance_str_4textview.setSpan(new RelativeSizeSpan(2.1f), 16, totaldistance_str.length(), 0);
        TextView textView_TotalDistance = findViewById(R.id.TotalDistance);
        textView_TotalDistance.setText(totaldistance_str_4textview);
    }

    // MENU AREA
    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.stat_layout);
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
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected (MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.nav_home) // STATS -> HOME
        {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            finish();
            startActivity(intent);
        }
        else if (id == R.id.nav_stats) // STATS -> STATS
        {
            // Nothing
        }
        else if (id == R.id.nav_tips) // STATS -> TIPS
        {
            // To Do
        }
        else if (id == R.id.nav_maps) // STATS -> MAPS
        {
            // To Do
        }
        else if (id == R.id.nav_settings) // STATS -> SETTINGS
        {
            // To Do
        }
        else if (id == R.id.nav_about) // STATS -> ABOUT
        {
            // From StatsActivity to AboutActivity
            Intent intent = new Intent(getBaseContext(), AboutActivity.class);
            finish();
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.stat_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}