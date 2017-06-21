package com.example.lzp.chronometerdemo;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;

    private Button btnStart;
    private Button btnPause;
    private Button btnReset;

    private long pauseTime;
    private boolean tag = true;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnPause = (Button) findViewById(R.id.btn_pause);
        btnReset = (Button) findViewById(R.id.btn_reset);

        chronometer.setFormat("已用时：%s");

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pauseTime == 0){
                    chronometer.setBase(SystemClock.elapsedRealtime());
//                    chronometer.setBase(SystemClock.elapsedRealtime()-1000*10); //从10秒处开始计时
                }else{
                    chronometer.setBase(chronometer.getBase() + (SystemClock.elapsedRealtime() - pauseTime));
                }
                chronometer.start();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.stop();
                pauseTime = SystemClock.elapsedRealtime();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseTime = 0;
                tag = true;
            }
        });

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(tag && SystemClock.elapsedRealtime() - chronometer.getBase() >= 1000 * 5){
                    Log.e("time","show");
                    tag = false;
                }
            }
        });
    }
}
