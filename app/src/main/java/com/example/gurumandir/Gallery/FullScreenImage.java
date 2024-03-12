package com.example.gurumandir.Gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.gurumandir.ImageAdapter;
import com.example.gurumandir.R;

public class FullScreenImage extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        imageView=findViewById(R.id.image_view);

        setTitle("Image");

        Intent intent =getIntent();
        int position= intent.getExtras().getInt("id");

        ImageAdapter imageAdapter= new ImageAdapter(this);

        imageView.setImageResource(imageAdapter.imageArray[position]);
    }
}