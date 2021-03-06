package com.griffithcollege.runix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;

public class Graph extends View
{
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
        super.onDraw(canvas);
        paint.setStrokeWidth(3f);
        canvas.scale(1, -1, getWidth() / 2, 600 / 2);

        canvas.drawLine(0,0,0, 500, paint); //Y axis
        canvas.drawLine(0,0, getWidth(),0, paint); //X axis

        draw_xPoint(canvas);
        draw_yPoint(canvas);

        addLink(canvas,(LinkedList<Float>) getTag(R.id.timePoint),(LinkedList<Float>) getTag(R.id.speedPoint),(float) getTag(R.id.maxTime));
    }



    public void addLink(Canvas canvas, LinkedList<Float> Times, LinkedList<Float> Speeds, float max_time)
    {
        // Times and Speeds must be the same size !
        setPoint(canvas, Times.get(0), Speeds.get(0), max_time); // Draw the first point

        int size = Times.size();
        for (int p = 1; p < size; p++)
        {
            setPoint(canvas, Times.get(p), Speeds.get(p), max_time);
            setLinkAB(canvas, Times.get(p - 1), Speeds.get(p - 1), Times.get(p), Speeds.get(p), max_time);
        }
    }

    public void setLinkAB(Canvas canvas, float timeA, float speedA, float timeB, float speedB, float max_time)
    {
        float coeff = getWidth() / max_time;
        canvas.drawLine(timeA*coeff, speedA*50, timeB*coeff, speedB*50, paint);
    }

    public void setPoint(Canvas canvas, float time, float speed, float max_time)
    {
        float coeff = getWidth() / max_time;
        float position_x = time*coeff;
        float position_y = speed*50; //scaling for the y axis
        canvas.drawPoint(position_x, position_y, paint);
    }

    public void draw_xPoint(Canvas canvas)
    {
        float intervalle = getWidth() / 5;
        float x = 0;
        while (x < getWidth())
        {
            String timestr = "|";
            if (x == 0)
                timestr = "0";
            canvas.drawText(timestr, 0, timestr.length(), x,0, paint);
            x += intervalle;
        }

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