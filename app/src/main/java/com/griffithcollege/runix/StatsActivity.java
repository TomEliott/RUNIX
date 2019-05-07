package com.griffithcollege.runix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class StatsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    // MAIN MENU
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.nav_home) // HOME
        {
            // From StatsActivity to MainActivity
            Intent intent = new Intent(StatsActivity.this, MainActivity.class);
            finish();
            startActivity(intent);
        }
        else if (id == R.id.nav_stats) // STATS
        {
            // From StatsActivity to StatsActivity
            Intent intent = new Intent(StatsActivity.this, StatsActivity.class);
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
            Intent intent = new Intent(StatsActivity.this, AboutActivity.class);
            finish();
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
