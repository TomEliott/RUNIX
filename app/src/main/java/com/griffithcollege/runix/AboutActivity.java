package com.griffithcollege.runix;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AboutActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Buttons declaration
        final FloatingActionButton returnhome = findViewById(R.id.about_returnhome);
        //final FloatingActionButton github = findViewById(R.id.github);
        //final FloatingActionButton website = findViewById(R.id.website);

        // Button that will return to MainActivity
        returnhome.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        // Button that will open my profile on the GitHub website (for credits)
        /*github.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String GitHubWebsite = "https://www.github.com/TomEliott"; // My GitHub profile
                Intent git = new Intent(Intent.ACTION_VIEW);
                git.setData(Uri.parse(GitHubWebsite));
                startActivity(git);
            }
        });*/

        // Button that will open my personal website (for credits)
        /*website.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String TomEliottWebsite = "https://www.tomeliott.com"; // My website
                Intent website = new Intent(Intent.ACTION_VIEW);
                website.setData(Uri.parse(TomEliottWebsite));
                startActivity(website);
            }
        });*/
    }
}
