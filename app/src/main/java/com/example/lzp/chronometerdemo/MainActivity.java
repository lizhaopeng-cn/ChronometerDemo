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

    private Button button1;
    private Button button2;
    private Button button3;

    private long pauseTime;
    private boolean tag = true;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        chronometer.setFormat("已用时：%s");

        button1.setOnClickListener(new View.OnClickListener() {
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

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.stop();
                pauseTime = SystemClock.elapsedRealtime();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
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
