package com.example.gurumandir.Event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gurumandir.Donation.Donate1;
import com.example.gurumandir.HomePage2;
import com.example.gurumandir.R;
import com.example.gurumandir.RelatedBooks.Book1;
import com.example.gurumandir.RelatedBooks.NSCharitra;

public class Event1 extends AppCompatActivity {
    Button shloka,utsav_pdf;
    TextView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event1);

        getSupportActionBar().hide();

        shloka= findViewById(R.id.shloka);
        utsav_pdf= findViewById(R.id.utsav_pdf);
        web= findViewById(R.id.web);

        shloka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Event1.this, Event2.class);
                startActivity(i);
            }
        });

        utsav_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Event1.this, UtsavPdf.class);
                startActivity(i);
            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.gurumandir.org/index.html"));
                startActivity(i);
            }
        });
    }
    public void onBackPressed() {
        Intent i=new Intent(Event1.this, HomePage2.class);
        startActivity(i);
        finish();
    }
}