package com.griffithcollege.runix;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MenuActivity extends MainActivity
{
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        {
            int id = item.getItemId();
            if (id == R.id.nav_home) // HOME
            {
                // From StatsActivity to MainActivity
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
            else if (id == R.id.nav_stats) // STATS
            {
                // From StatsActivity to StatsActivity
                Intent intent = new Intent(MenuActivity.this, StatsActivity.class);
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
                Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
                finish();
                startActivity(intent);
            }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);

            return true;
        }
    }
}