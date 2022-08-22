package com.example.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView countdownTextView;
    SeekBar timerSeekbar;
    boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;
    public void resetTimer(){countdownTextView.setText("00:30");
        timerSeekbar.setProgress(30);
        timerSeekbar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO!");
        counterIsActive = false;

    }
    public void buttonPressed(View view) {

        if (counterIsActive){
            resetTimer();
        }
        else{
            counterIsActive = true;
            timerSeekbar.setEnabled(false);
            goButton.setText("STOP!");

            countDownTimer = new CountDownTimer(timerSeekbar.getProgress() * 1000, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.hornsound);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }
    public void updateTimer(int secondsLeft){
        int mins = secondsLeft/60;
        int secs = secondsLeft - (mins*60);
        String secondSecs = Integer.toString(secs);

        if (secs<=9){
            secondSecs = "0"+secondSecs;
        }
        countdownTextView.setText(Integer.toString(mins) + ":"+ secondSecs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekbar = findViewById(R.id.timerSeekBar);
        countdownTextView = findViewById(R.id.countDownTextView);
        goButton = findViewById(R.id.goButton);
        timerSeekbar.setMax(600);
        timerSeekbar.setProgress(30);

        timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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