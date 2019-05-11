package com.griffithcollege.runix;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class StatsActivity extends MainActivity
{
    final String distance = "";
    final String time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        ToolbarStat(); // Generation of Stats' Toolbar

        FloatingActionButton share_button = findViewById(R.id.share);
        share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                share(distance, time);
            }
        });

        // Set up TextView & Data
        AverageSpeed();
        TotalDistance();
        TimeTaken();
        Altitude();
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
        String averagespeed = ""; // TO FIX
        //----//
        String averagespeed_str = "Average Speed : "+averagespeed;
        SpannableString averagespeed_str_4textview =  new SpannableString(averagespeed_str);
        averagespeed_str_4textview.setSpan(new RelativeSizeSpan(1.5f), 15, averagespeed_str.length(), 0); // Set the size
        //averagespeed_str_4textview.setSpan(new ForegroundColorSpan(Color.RED), 15, averagespeed_str.length(), 0); // Set the color
        TextView textView_AverageSpeed = findViewById(R.id.AverageSpeed);
        textView_AverageSpeed.setText(averagespeed_str_4textview);
    }

    public void TotalDistance()
    {
        String totaldistance = ""; // TO FIX
        //----//
        String totaldistance_str = "Total Distance\n "+totaldistance;
        SpannableString totaldistance_str_4textview =  new SpannableString(totaldistance_str);
        totaldistance_str_4textview.setSpan(new RelativeSizeSpan(2.1f), 16, totaldistance_str.length(), 0);
        TextView textView_TotalDistance = findViewById(R.id.TotalDistance);
        textView_TotalDistance.setText(totaldistance_str_4textview);
    }

    public void TimeTaken()
    {
        String timetaken = ""; // TO FIX
        //----//
        TextView textView_TimeTaken = findViewById(R.id.TimeTaken);
        TextView time_x = findViewById(R.id.time_x);
        textView_TimeTaken.setText("Time taken\n"+timetaken);
        time_x.setText(timetaken);
    }

    public void Altitude()
    {
        String alti_max = ""; // TO FIX
        String alti_min = ""; // TO FIX
        //----//
        TextView altitude = findViewById(R.id.Altitude);
        altitude.setText("Altitude max :"+alti_max+"\nAltitude min :"+alti_min);
    }

    public void share(String distance, String time)
    {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Yeah! With Runix, I measured that I had run for "+time+" on more than "+distance+"km.");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
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
        if (id == R.id.SettingsMenu)
        {
            Toast.makeText(getApplicationContext(), "Please complete a new " +
                    "running activity before changing your " +
                    "username.", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.AboutMenu)
        {
            Intent intent = new Intent(getBaseContext(), AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) // HOME
        {
            // From StatsActivity to MainActivity
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            finish();
            startActivity(intent);
        }
        else if (id == R.id.nav_stats) // STATS
        {
            // From MainActivity to StatsActivity
            Toast.makeText(getApplicationContext(), "You are already " +
                    "in the statistics dashboard", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.nav_tips) // TIPS
        {
            // From MainActivity to TipsActivity
            Intent intent = new Intent(getBaseContext(), TipsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_share) // SHARE
        {
            share(distance, time);
            return true;
        }
        else if (id == R.id.nav_settings) // SETTINGS
        {
            Toast.makeText(getApplicationContext(), "Please complete a new " +
                    "running activity before changing your " +
                    "username.", Toast.LENGTH_SHORT).show();
            return true;
        }
        else //if (id == R.id.nav_about) // ABOUT
        {
            // From MainActivity to AboutActivity
            Intent intent = new Intent(getBaseContext(), AboutActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}