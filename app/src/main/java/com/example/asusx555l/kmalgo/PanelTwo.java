package com.example.asusx555l.kmalgo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Vector;


public class PanelTwo extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    private TutorialThread _thread;
    public boolean flag = true;

    public PanelTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        _thread = new TutorialThread(getHolder(), context);
        setFocusable(true);
    }

    public TutorialThread getThread() {
        return _thread;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.setOnTouchListener(this);
        _thread.setRunning(true);
        _thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                _thread.make(x, y);
            case MotionEvent.ACTION_MOVE: // движение
            case MotionEvent.ACTION_UP: // отпускание
        }
        return true;
    }
}


class TutorialThread extends Thread {
    private PanelTwo panelTwo;
    private SurfaceHolder _surfaceHolder;
    private Context _mContext;
    private boolean _run = false;

    Vector<Float> stx = new Vector();
    Vector<Float> sty = new Vector();
    Vector<Float> Cox = new Vector();
    Vector<Float> Coy = new Vector();
    Paint p = new Paint();
    Paint c = new Paint();

    public static final boolean False = false;
    public static final boolean True = true;
    public  boolean mState = true;

    public float[] Coxx; public float[] Coyy;
    public float[] stxx; public float[] styy;


    public void setGameState(boolean flag) {
        synchronized (_surfaceHolder) {
            if (flag == False) {
                mState = False;
            } else if (flag == True) {
                mState = True;
            }
        }
    }

    public void setRes(boolean ress) {
        if (ress) {
            Coxx = new float[Cox.size()];
            Coyy = new float[Cox.size()];
            for (int i = 0; i < Cox.size(); i++) {
                Coxx[i] = Cox.get(i);
                Coyy[i] = Coy.get(i);
                System.out.println(Coxx[i]);
                System.out.println("PT");
            }
            stxx = new float[stx.size()];
            styy = new float[stx.size()];
            for (int i = 0; i < stx.size(); i++) {
                stxx[i] = stx.get(i);
                styy[i] = sty.get(i);
            }
        }
    }

    public void make(float x, float y) {
        if (mState) {
            stx.add(x);
            sty.add(y);
        } else {
            Cox.add(x);
            Coy.add(y);
        }
    }

    public TutorialThread(SurfaceHolder surfaceHolder, Context context) {
        _surfaceHolder = surfaceHolder;
        _mContext = context;
    }

    public void setRunning(boolean run) {
        _run = run;
    }

    @Override
    public void run() {
        Canvas c;
        while (_run) {
            c = null;
            try {
                c = _surfaceHolder.lockCanvas(null);
                synchronized (_surfaceHolder) {
                    if (c == null) {
                        return;
                    }
                    onDraw(c);
                }
            } finally {
                if (c != null)
                    _surfaceHolder.unlockCanvasAndPost(c);
            }
        }
    }

    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        p.setStrokeWidth(3*3*3);
        p.setColor(Color.GREEN);
        for (int i = 0; i < stx.size(); i++) {
            canvas.drawCircle(stx.get(i), sty.get(i), 5, p);
        }
        c.setStrokeWidth(3*3*3);
        c.setColor(Color.BLACK);
        for (int i = 0; i < Cox.size(); i++){
            canvas.drawPoint(Cox.get(i), Coy.get(i), c);
        }
    }

}
