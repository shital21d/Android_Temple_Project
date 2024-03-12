package com.example.gurumandir.Gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.gurumandir.HomePage2;
import com.example.gurumandir.ImageAdapter;
import com.example.gurumandir.R;
import com.example.gurumandir.Room.Room1;

public class Photos extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

       gridView= findViewById(R.id.grid_view);
       gridView.setAdapter(new ImageAdapter(this));

       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent i1= new Intent(getApplicationContext(),FullScreenImage.class);
               i1.putExtra("id",i);
               startActivity(i1);
           }
       });
    }
    public void onBackPressed() {
        Intent i=new Intent(Photos.this, HomePage2.class);
        startActivity(i);
        finish();
    }
}