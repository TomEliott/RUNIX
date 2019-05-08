package com.griffithcollege.runix;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final FloatingActionButton returnhome = findViewById(R.id.settings_returnhome);
        final TextView username = findViewById(R.id.UsernameDisplay);
        final EditText new_name = findViewById(R.id.Username);
        final Button save_new_name = findViewById(R.id.save_username);

        returnhome.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        save_new_name.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String username_fixed = "Welcome, " + new_name.getText().toString();
                username.setText(username_fixed);
            }
        });
    }
}