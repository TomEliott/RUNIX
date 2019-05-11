package com.griffithcollege.runix;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    LocationManager lm;
    GPSLocationListener gps;
    boolean isReady = false;
    boolean usernameChanged = false;
    String user = "Anonymous";
    String filename;
    GPXParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        parser = new GPXParser();
        lm = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        String GPStracks = "GPStracks";

        File f = new File(Environment.getExternalStorageDirectory(), GPStracks);
        if (!f.exists())
        { f.mkdirs(); }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Chronometer RunixChrono = findViewById(R.id.Chrono);

        ToolbarMain(); // Generation of Main's Toolbar

        final FloatingActionButton start = (FloatingActionButton) findViewById(R.id.start);
        final FloatingActionButton stop = (FloatingActionButton) findViewById(R.id.stop);
        final TextView status = findViewById(R.id.Status);

        start.setClickable(true);
        stop.setClickable(false);

        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!usernameChanged)
                {
                    ChangeUsername("One More Thing",
                            "Before starting a new running activity, please enter your "
                                    + "name to personalize RUNIX's experience.");
                    usernameChanged = true;
                }
                // Chrono + Status
                RunixChrono.setBase(SystemClock.elapsedRealtime());
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                String formattedDate = df.format(c);
                filename = formattedDate + ".gpx";
                gpsStart();
                RunixChrono.start();
                start.setClickable(false);
                stop.setClickable(true);
                status.setText("Running activity in progress...");

                isReady = false;
            }
        });

        stop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Status + Chrono + Buttons
                gpsStop();
                start.setClickable(true);
                stop.setClickable(false);
                RunixChrono.stop();
                String time = RunixChrono.getText().toString();
                RunixChrono.setBase(SystemClock.elapsedRealtime());
                start.setImageResource(R.drawable.ic_restart); // For the next restart

                // Last update (from navigation tab)
                TextView last_training = findViewById(R.id.LastTrainingTextView);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm");
                String current_time = sdf.format(new Date());
                last_training.setText("Last running : "+current_time);
                status.setText("Last running time : "+time);

                isReady = true;
            }
        });
    }

    public void ToolbarMain()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_runix_icon);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void ChangeUsername(String title, String message)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(message);

        final EditText name = new EditText(this);
        alert.setView(name);
        final TextView username = findViewById(R.id.UsernameDisplay);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                user = name.getText().toString();
                username.setText("Welcome, "+user+" !");
                usernameChanged = true;
            }
        });

        alert.setNegativeButton("Anonymous Mode", new DialogInterface.OnClickListener()
        {
            // Anonymous mode is used when the user does not want to communicate his name
            public void onClick(DialogInterface dialog, int whichButton)
            {
                user = "Anonymous";
                username.setText("Welcome, "+user+" user !");
                usernameChanged = true;
            }
        });
        alert.show();
    }

    // MENU AREA
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
        if (id == R.id.SettingsMenu)
        {
            ChangeUsername("Settings", "Personalize RUNIX's experience by " +
                    "indicating your name.");
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
            // From MainActivity to MainActivity
            // Nothing to do
        }
        else if (id == R.id.nav_stats) // STATS
        {
            // From MainActivity to StatsActivity
            if (isReady)
            {
                Intent intent = new Intent(getBaseContext(), StatsActivity.class);
                finish();
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Please complete the current " +
                        "running activity before accessing your " +
                        "statistics.", Toast.LENGTH_SHORT).show();
            }
        }
        else if (id == R.id.nav_tips) // TIPS
        {
            // From MainActivity to TipsActivity
            Intent intent = new Intent(getBaseContext(), TipsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_settings) // SETTINGS
        {
            ChangeUsername("Settings", "Personalize RUNIX's experience by " +
                    "indicating your name.");
        }
        else if (id == R.id.nav_about) // ABOUT
        {
            // From MainActivity to AboutActivity
            Intent intent = new Intent(getBaseContext(), AboutActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void gpsStart() {
        gps = new GPSLocationListener();
        try {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 5, gps);
        } catch (SecurityException se) {
            se.printStackTrace();
        }
    }

    public void gpsStop(){
        lm.removeUpdates(gps);
        parser.writeGPX(gps.getData(),filename);
        gps = null;
    }
}