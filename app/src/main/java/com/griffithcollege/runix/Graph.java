package com.griffithcollege.runix;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

public class Graph extends View
{
    int max_time = 60; //example (1 min)

    public Graph(Context context)
    {
        super(context);
        init();
    }

    public Graph(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }
    public Graph(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    private Paint paint = new Paint();

    private void init()
    {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(34);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        paint.setStrokeWidth(3f);
        canvas.scale(1, -1, getWidth() / 2, 600 / 2);

        canvas.drawLine(0,0,0, 500, paint); //Y axis
        canvas.drawLine(0,0, getWidth(),0, paint); //X axis
        draw_xPoint(canvas, max_time);
        draw_yPoint(canvas);

        //canvas.drawPoint(500, getWidth(), paint);


        super.onDraw(canvas);
        //canvas.drawRect(600, 600, 0, 0, paint);

        int startX = 0;
        int startY = 0;
        int stopX = 100;
        int stopY = 100;

        //canvas.drawLine(startX, startY, stopX, stopY, paint);
        //canvas.drawLine(0,0,600,600, paint);

        //xAxis(canvas); // Axe des x
        //yAxis(canvas); // Axe des y

        setPoint(canvas, 100, 10);
    }

    public void setPoint(Canvas canvas, int time, int speed)
    {
        int position_x = time;
        int position_y = speed*50; //scaling for the y axis
        canvas.drawPoint(position_x, position_y, paint);
    }

    public void draw_xPoint(Canvas canvas, int maxtime)
    {
        int coeff = maxtime / getWidth();
        int time_scale = (coeff * getWidth()) / 10;

        int i = 0;
        while (i < maxtime)
        {
            String timestr = "|";
            if (i == 0)
                timestr = "0";
            canvas.drawText(timestr, 0, timestr.length(), i,0, paint);
            i += time_scale;
        }
        Toast.makeText(getContext(), "Width : "+getWidth() + "\n Time scale : "+time_scale, Toast.LENGTH_SHORT).show();
    }

    public void draw_yPoint(Canvas canvas)
    {
        canvas.drawText("_", 0, 50, paint);
        canvas.drawText("_", 0, 100, paint);
        canvas.drawText("_", 0, 150, paint);
        canvas.drawText("_", 0, 200, paint);
        canvas.drawText("_", 0, 250, paint);
        canvas.drawText("_", 0, 300, paint);
        canvas.drawText("_", 0, 350, paint);
        canvas.drawText("_", 0, 400, paint);
        canvas.drawText("_", 0, 450, paint);
        canvas.drawText("_ I0", 0, 500, paint);
    }
}