package com.example.gurumandir.Event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gurumandir.HomePage2;
import com.example.gurumandir.R;

import java.util.concurrent.TimeUnit;

public class Event2 extends AppCompatActivity {

    private TextView tv_currenttime,tv_totaltime;
    private ImageView im_back,im_backward,im_play,im_forward,im_next;
    private SeekBar seekbar_time;
    private MediaPlayer mPlayer;
    private AudioManager aManager;
    private int currentIndex =0;

    private static int openingTime=0,startTime=0,endTime=0,currentTime=0,backwardTime=5000,forward=5000;
    private Handler hd= new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event2);

        aManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);


        tv_currenttime= findViewById(R.id.tv_current_time);
        tv_totaltime= findViewById(R.id.tv_total_time);


        im_backward= findViewById(R.id.img_backward);
        im_play= findViewById(R.id.img_play);
        im_forward= findViewById(R.id.img_forward);


        seekbar_time= findViewById(R.id.seek_bar_song);


        mPlayer= MediaPlayer.create(this,R.raw.digambara);
        seekbar_time.setClickable(false);

        im_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                endTime = mPlayer.getDuration();
                currentTime = mPlayer.getCurrentPosition();

                if(mPlayer!=null && mPlayer.isPlaying())
                {
                    mPlayer.pause();
                    im_play.setImageResource(R.drawable.play);
                    Toast.makeText(Event2.this, "Paused Audio...",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mPlayer.start();
                    Toast.makeText(Event2.this, "Playing Audio...",Toast.LENGTH_SHORT).show();
                    im_play.setImageResource(R.drawable.pause);
                }

                if (openingTime == 0)
                {
                    seekbar_time.setMax(endTime);
                    openingTime=1;
                }

                tv_totaltime.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(endTime),
                        TimeUnit.MILLISECONDS.toSeconds(endTime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(endTime))));

                tv_currenttime.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(currentTime),
                        TimeUnit.MILLISECONDS.toSeconds(currentTime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(currentTime))));

                seekbar_time.setProgress(currentTime);
                hd.postDelayed(UpdateSongTime,100);


            }
        });

        im_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((currentTime+forward)<=endTime){
                    currentTime= currentTime+forward;
                    mPlayer.seekTo(currentTime);
                }else{
                    Toast.makeText(Event2.this,"cann't jump forward..",Toast.LENGTH_SHORT).show();
                }
            }
        });

        im_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((currentTime-backwardTime)>=0){
                    currentTime= currentTime-backwardTime;
                    mPlayer.seekTo(currentTime);
                }else{
                    Toast.makeText(Event2.this,"cann't jump backward..",Toast.LENGTH_SHORT).show();
                }
            }
        });

        seekbar_time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b){
                    mPlayer.seekTo(i);
                    seekbar_time.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private Runnable UpdateSongTime= new Runnable() {
        @Override
        public void run() {
            currentTime= mPlayer.getCurrentPosition();
            tv_currenttime.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(currentTime),
                    TimeUnit.MILLISECONDS.toSeconds(currentTime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(currentTime))));

            seekbar_time.setProgress(currentTime);
            hd.postDelayed(this,100);
        }
    };

    public void onBackPressed() {
        Intent i=new Intent(Event2.this, Event1.class);
        startActivity(i);
        finish();
    }
}