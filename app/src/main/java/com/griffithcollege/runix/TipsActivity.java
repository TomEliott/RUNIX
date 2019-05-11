package com.griffithcollege.runix;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class TipsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        CoordinatorLayout layout = findViewById(R.id.ActivityTipsID);
        layout.setBackgroundColor(Color.WHITE);

        tipsGenerator(); //first tip

        FloatingActionButton new_tip = findViewById(R.id.newtip);
        new_tip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                tipsGenerator();
                colorGenerator();
            }
        });

        FloatingActionButton returnhome = findViewById(R.id.tips_returnhome);
        returnhome.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(TipsActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        Toast.makeText(getApplicationContext(), "Source : runnersworld.com", Toast.LENGTH_LONG).show();
    }

    public void colorGenerator()
    {
        int color_number = Random(0, 4);
        CoordinatorLayout layout = findViewById(R.id.ActivityTipsID);
        switch (color_number)
        {
            case(1):
                layout.setBackgroundColor(Color.RED);
            case(2):
                layout.setBackgroundColor(Color.BLUE);
            case(3):
                layout.setBackgroundColor(Color.YELLOW);
            case(4):
                layout.setBackgroundColor(Color.GREEN);
            case(5):
                layout.setBackgroundColor(Color.CYAN);
            default:
                layout.setBackgroundColor(Color.WHITE);
        }
    }

    public void tipsGenerator()
    {
        int tips = Random(0, 19);
        String titles[] = getResources().getStringArray(R.array.tips_titles);
        String messages[] = getResources().getStringArray(R.array.tips_messages);
        String tips_title = titles[tips];
        String tips_message = messages[tips];
        tipsSet(tips_title, tips_message, tips);
    }

    public int Random(int min, int max)
    {
        Random random = new Random();
        int tips_number = random.nextInt(max - min + 1) + min;
        return tips_number;
    }

    public void tipsSet(String title, String message, int number)
    {
        TextView TextView_tips_title = findViewById(R.id.tipsTitle);
        TextView TextView_tips_message = findViewById(R.id.tipsMessage);
        TextView TextView_tips_number = findViewById(R.id.tipsNumber);
        number += 1;
        TextView_tips_number.setText("Tip #"+number);
        TextView_tips_title.setText(title);
        TextView_tips_message.setText(message);
    }
}
