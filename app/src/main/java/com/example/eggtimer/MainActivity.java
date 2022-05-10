package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    ImageView timerImageView;
    TextView timeCountTextView;
    Button startButton;
    CountDownTimer countDownTimer;
    boolean counterIsActive = false;

    public void resetCounter() {
        timeCountTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        startButton.setText("GO!");
        countDownTimer.cancel();
        counterIsActive = false;

    }


    public void buttonOnClick(View view) {

        if(counterIsActive) {
            resetCounter();
        } else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            startButton.setText("STOP!");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100
                    , 1000) {
                @Override
                public void onTick(long l) {

                    updateTimer((int) l / 1000);

                }

                @Override
                public void onFinish() {

                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.air_horn_sound);
                    mPlayer.start();
                    resetCounter();

                }
            }.start();
        }
    }

    public void updateTimer(int i) {
        int minutes = i / 60 ;
        int seconds = i  - (minutes * 60);


        String secondsString = Integer.toString(seconds);
        if (seconds <= 9){
            secondsString = "0" + secondsString;
        }
        timeCountTextView.setText(Integer.toString(minutes) + ":" + secondsString);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerImageView = findViewById(R.id.timerImageView);
        timeCountTextView = findViewById(R.id.timeCountTextView);
        startButton = findViewById(R.id.startButton);

        timerSeekBar.setMax(3600);
        timerSeekBar.setProgress(30);


        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    updateTimer(i);

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }
}