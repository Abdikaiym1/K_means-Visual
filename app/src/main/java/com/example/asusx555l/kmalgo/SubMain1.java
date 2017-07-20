package com.example.asusx555l.kmalgo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Vector;
import java.math.*;

import static java.lang.Math.min;
import static java.lang.StrictMath.sqrt;

public class SubMain1 extends Activity {
    public float[] CX; public float[] CY;
    public float[] SX; public float[] SY;
    public Vector<Float> SX1 = new Vector();
    public Vector<Float> SY1 = new Vector();
    public Vector<Float> SX2 = new Vector();
    public Vector<Float> SY2 = new Vector();
    public Vector<Float> SX3 = new Vector();
    public Vector<Float> SY3 = new Vector();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new DrawView(this));
        Intent intent = getIntent();
        CX = intent.getFloatArrayExtra("CX");
        CY = intent.getFloatArrayExtra("CY");
        SX = intent.getFloatArrayExtra("SX");
        SY = intent.getFloatArrayExtra("SY");

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DefMainSurf.class);
        startActivity(intent);
    }

    public void Algo() {
        float cx1 = CX[0], cy1 = CY[0],
                cx2 = CX[1], cy2 = CY[1],
                cx3 = CX[2], cy3 = CY[2], m1, m2, m3,
                sumX1 = 0, sumY1 = 0,
                sumX2 = 0, sumY2 = 0,
                sumX3 = 0, sumY3 = 0;
        int lenS = SX.length;
        for (int l = 0; l < 100; l++) {
            SX1.clear();
            SX2.clear();
            SX3.clear();
            SY1.clear();
            SY2.clear();
            SY3.clear();

            for (int i = 0; i < lenS; i++) {
                m1 = (float) sqrt((cx1 - SX[i]) * (cx1 - SX[i]) + (cy1 - SY[i]) * (cy1 - SY[i]));
                m2 = (float) sqrt((cx2 - SX[i]) * (cx2 - SX[i]) + (cy2 - SY[i]) * (cy2 - SY[i]));
                m3 = (float) sqrt((cx3 - SX[i]) * (cx3 - SX[i]) + (cy3 - SY[i]) * (cy3 - SY[i]));
                if (m1 == min(min(m1, m2), m3)) {
                    SX1.add(SX[i]);
                    SY1.add(SY[i]);
                } else if (m2 == min(min(m1, m2), m3)) {
                    SX2.add(SX[i]);
                    SY2.add(SY[i]);
                } else if (m3 == min(min(m1, m2), m3)) {
                    SX3.add(SX[i]);
                    SY3.add(SY[i]);
                }
            }

            for (int i = 0; i < SX1.size(); i++) {
                sumX1 = sumX1 + SX1.get(i);
                sumY1 = sumY1 + SY1.get(i);
            }
            cx1 = sumX1 / SX1.size();
            cy1 = sumY1 / SY1.size();
            sumX1 = 0;
            sumY1 = 0;
            for (int i = 0; i < SX2.size(); i++) {
                sumX2 = sumX2 + SX2.get(i);
                sumY2 = sumY2 + SY2.get(i);
            }
            cx2 = sumX2 / SX2.size();
            cy2 = sumY2 / SY2.size();
            sumX2 = 0;
            sumY2 = 0;
            for (int i = 0; i < SX3.size(); i++) {
                sumX3 = sumX3 + SX3.get(i);
                sumY3 = sumY3 + SY3.get(i);
            }
            cx3 = sumX3 / SX3.size();
            cy3 = sumY3 / SY3.size();
            sumX3 = 0;
            sumY3 = 0;
        }
        CX[0] = cx1; CY[0] = cy1;
        CX[1] = cx2; CY[1] = cy2;
        CX[2] = cx3; CY[2] = cy3;
    }


    class DrawView extends View {
        Paint p1 = new Paint();
        Paint p2 = new Paint();
        Paint p3 = new Paint();
        Paint c1 = new Paint();
        Paint c2 = new Paint();
        Paint c3 = new Paint();
        boolean flag = false;

        public DrawView(Context context) {
            super(context);
            setFocusable(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            if (!flag) {
                Algo();
                flag = true;
            }

            canvas.drawColor(Color.GRAY);
            p1.setColor(Color.GREEN);
            p2.setColor(Color.RED);
            p3.setColor(Color.BLUE);
            for (int i = 0; i < SX1.size(); i++) {
                canvas.drawCircle(SX1.get(i), SY1.get(i), 10, p1);
            }
            for (int i = 0; i < SX2.size(); i++) {
                canvas.drawCircle(SX2.get(i), SY2.get(i), 10, p2);
            }
            for (int i = 0; i < SX3.size(); i++) {
                canvas.drawCircle(SX3.get(i), SY3.get(i), 10, p3);
            }

            c1.setStrokeWidth(3*3*3);
            c1.setColor(Color.GREEN);
            c2.setStrokeWidth(3*3*3);
            c2.setColor(Color.RED);
            c3.setStrokeWidth(3*3*3);
            c3.setColor(Color.BLUE);
            canvas.drawPoint(CX[0], CY[0], c1);
            canvas.drawPoint(CX[1], CY[1], c2);
            canvas.drawPoint(CX[2], CY[2], c3);
        }
    }
}
