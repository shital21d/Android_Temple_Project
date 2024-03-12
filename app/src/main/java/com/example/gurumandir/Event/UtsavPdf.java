package com.example.gurumandir.Event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gurumandir.R;
import com.github.barteksc.pdfviewer.PDFView;

public class UtsavPdf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utsav_pdf);
        setTitle("Utsav Schedule");

        PDFView pdfView=findViewById(R.id.pdf_utsav);
        pdfView.fromAsset("utsav.pdf").load();
    }
}