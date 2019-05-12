package com.griffithcollege.runix;

import android.app.AlertDialog;
import android.content.ContentProvider;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class StatsActivity extends MainActivity
{
    String filename;
    GPXParser parser;
    DataGPS data;
    Statistics stats;
    DecimalFormat df;

    // TEST
    //private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        ToolbarStat(); // Generation of Stats' Toolbar

        FloatingActionButton share_button = findViewById(R.id.share);
        share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                share();
            }
        });

        Intent intent = getIntent();
        filename = intent.getStringExtra("filename");

        parser = new GPXParser();
        data = parser.readGPX(filename);
        if (data.size() == 0){
            try{
                alertGPS("GPS","The result depends on the quality of the gps signal, the app also need access to your storage and location");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                data.add(53.3533665d,-6.29624396d,98.47039794921875d,4.2479997f,format.parse("2019-05-12 22:07:21"));
                data.add(53.35303003d,-6.29668523d,101.43804931640625d, 3.852f,format.parse("2019-05-12 22:07:39"));
                data.add(53.35290649d,-6.29686894d,97.27880859375d,4.536f,format.parse("2019-05-12 22:07:50"));
                data.add(53.35281574d,-6.29706957d,81.79315185546875d,4.428f,format.parse("2019-05-12 22:08:09"));
                data.add(53.35270876d,-6.29722956d,76.174560546875d,2.52f,format.parse("2019-05-12 22:08:19"));
                data.add(53.35252274d,-6.29751481d,89.8995361328125d,5.436f,format.parse("2019-05-12 22:08:33"));
                data.add(53.35246597d,-6.29774328d,84.3958740234375d,4.824f,format.parse("2019-05-12 22:08:43"));
                data.add(53.35225235d,-6.29788676d,85.1923828125d,5.256f,format.parse("2019-05-12 22:08:53"));
                data.add(53.35219646d,-6.29797545d,73.5633544921875d,4.1759996f,format.parse("2019-05-12 22:09:03"));
            } catch (ParseException e){
                e.printStackTrace();
            }
        } //in case of the GPS not working
        stats = new Statistics(data);
        
        final View graph = (View) findViewById(R.id.GraphCustomView);
        graph.setTag(R.id.timePoint,stats.timePoint());
        graph.setTag(R.id.speedPoint,stats.speedPoint());
        graph.setTag(R.id.maxTime,stats.timeTaken());

        Canvas canvas = new Canvas();

        graph.draw(canvas);

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
        String averagespeed = df.format(stats.averageSpeed());
        //----//
        String averagespeed_str = "Average Speed : "+averagespeed+" Km/h";
        SpannableString averagespeed_str_4textview =  new SpannableString(averagespeed_str);
        averagespeed_str_4textview.setSpan(new RelativeSizeSpan(1.5f), 15, averagespeed_str.length(), 0); // Set the size
        //averagespeed_str_4textview.setSpan(new ForegroundColorSpan(Color.RED), 15, averagespeed_str.length(), 0); // Set the color
        TextView textView_AverageSpeed = findViewById(R.id.AverageSpeed);
        textView_AverageSpeed.setText(averagespeed_str_4textview);
    }

    public void TotalDistance()
    {
        String totaldistance = String.valueOf(stats.totalDistance().intValue());
        //----//
        String totaldistance_str = "Total Distance\n "+totaldistance+" m";
        SpannableString totaldistance_str_4textview =  new SpannableString(totaldistance_str);
        totaldistance_str_4textview.setSpan(new RelativeSizeSpan(2.1f), 16, totaldistance_str.length(), 0);
        TextView textView_TotalDistance = findViewById(R.id.TotalDistance);
        textView_TotalDistance.setText(totaldistance_str_4textview);
    }

    public void TimeTaken()
    {
        String timetaken = stats.timeTaken().toString();
        //----//
        TextView textView_TimeTaken = findViewById(R.id.TimeTaken);
        TextView time_x = findViewById(R.id.time_x);
        textView_TimeTaken.setText("Time taken\n"+timetaken+" s");
        time_x.setText(timetaken);
    }

    public void Altitude()
    {
        String alti_max = df.format(stats.maximumAltitude());
        String alti_min = df.format(stats.minimumAltitude());
        //----//
        TextView altitude = findViewById(R.id.Altitude);
        altitude.setText("Altitude max :"+alti_max+" m"+"\nAltitude min :"+alti_min+" m");
    }

    public void share()
    {
        String time = stats.timeTaken().toString();
        String distance = String.valueOf(stats.totalDistance().intValue());

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Yeah! With Runix, I measured that I had run for "+time+" s on more than "+distance+"m.");
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
            share();
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

    public void alertGPS(String title, String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(message);


        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alert.show();
    }
}