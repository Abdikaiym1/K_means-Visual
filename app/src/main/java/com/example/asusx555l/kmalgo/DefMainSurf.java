package com.example.asusx555l.kmalgo;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.example.asusx555l.kmalgo.R;


public class DefMainSurf extends Activity {
    Button buttonL;
    Button buttonM;
    Button buttonR;
    PanelTwo panelTwo;
    TutorialThread _thread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainn);
        panelTwo = (PanelTwo) findViewById(R.id.PanelTwo);
        _thread = panelTwo.getThread();
        buttonL = (Button) findViewById(R.id.button4);
        buttonM = (Button) findViewById(R.id.button);
        buttonR = (Button) findViewById(R.id.button5);
    }

    public void buttonL(View v) {
        _thread.setGameState(true);
    }

    public void buttonR(View v) {
        _thread.setGameState(false);
    }

    public void button(View v) {
        Intent intent = new Intent(this, SubMain1.class);
        _thread.setRes(true);
        intent.putExtra("CX", _thread.Coxx);
        for (int i = 0; i < _thread.Coxx.length; i++) {
            System.out.println(_thread.Coxx[i]);
            System.out.println("DMS");
        }
        intent.putExtra("CY", _thread.Coyy);
        intent.putExtra("SX", _thread.stxx);
        intent.putExtra("SY", _thread.styy);
        startActivity(intent);
    }
}

