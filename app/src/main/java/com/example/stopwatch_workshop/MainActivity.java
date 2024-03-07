package com.example.stopwatch_workshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    boolean running = false;
    long pause;
    TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        timer = findViewById(R.id.timer);
    }

    public void OnClickStart(View view) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pause);
            chronometer.start();
            running = true;
        }
    }

    public void OnClickPause(View view) {
        if (running) {
            chronometer.stop();
            pause = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void OnClickReset(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pause = 0;
    }

    public void OnClickBackAgain(View view) {
        TakeTime();
    }

    public void TakeTime() {
        long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
        int hours = (int) (elapsedMillis / 3600000);
        int minutes = (int) (elapsedMillis / 60000) % 60;
        int seconds = (int) (elapsedMillis / 1000) % 60;

        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timer.setText(timeString);
    }
}