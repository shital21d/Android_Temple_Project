package com.example.gurumandir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation anim_fade_in;
    TextView tv_lady;
    ImageView img_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Guru Mandir Sansthan");

        tv_lady = findViewById(R.id.tv_welcome);
        img_welcome = findViewById(R.id.img_welcome);

        new Thread()
        {
            public void run()
            {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally{
                    //intent is use to jump from one activity to another
                    Intent i=new Intent(MainActivity.this,LoginPage.class);
                    startActivity(i);
                    finish();
                }
            }
        }.start();

        anim_fade_in = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);
        img_welcome.startAnimation(anim_fade_in);
        tv_lady.startAnimation(anim_fade_in);
    }
}