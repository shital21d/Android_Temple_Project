package com.example.gurumandir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.gurumandir.Donation.Donate1;
import com.example.gurumandir.Event.Event1;
import com.example.gurumandir.Gallery.Photos;
import com.example.gurumandir.Rituals.Pass1;
import com.example.gurumandir.RelatedBooks.Book1;
import com.example.gurumandir.Room.Room1;

public class HomePage2 extends AppCompatActivity {

    Boolean doubletap=false;
    CardView cd_donation,cd_pass,cd_books,cd_room,cd_event,cd_gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);

        setTitle("Guru Mandir Sansthan");

        cd_donation= findViewById(R.id.cd_donation);
        cd_pass= findViewById(R.id.cd_pass);
        cd_books= findViewById(R.id.cd_books);
        cd_room= findViewById(R.id.cd_room);
        cd_event= findViewById(R.id.cd_event);
        cd_gallery= findViewById(R.id.cd_gallery);

        cd_donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage2.this, Donate1.class));
                finish();
            }
        });
        cd_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage2.this, Pass1.class));
                finish();
            }
        });
        cd_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage2.this, Book1.class));
                finish();
            }
        });
        cd_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage2.this, Room1.class));
                finish();
            }
        });
        cd_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage2.this, Photos.class));
                finish();
            }
        });
        cd_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage2.this, Event1.class));
                finish();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_home_my_profile:
                Intent i = new Intent(HomePage2.this, Profile.class);
                startActivity(i);
                finish();
                return true;

            case R.id.menu_home_location:
                Intent i1 = new Intent(HomePage2.this, Map.class);
                startActivity(i1);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public void onBackPressed()
    {
        if(doubletap)
        {
            super.onBackPressed();
        }
        else
        {
            Toast.makeText(HomePage2.this,"Please Press Back Again..",Toast.LENGTH_SHORT).show();
            doubletap=true;
            Handler handler=new Handler();
            handler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    doubletap=false;
                }
            },2000);
        }
    }
}