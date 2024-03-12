package com.example.gurumandir.RelatedBooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.gurumandir.R;
import com.github.barteksc.pdfviewer.PDFView;

public class AartiPdf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aarti_pdf);

        PDFView pdfView=findViewById(R.id.pdf_aarti);
        pdfView.fromAsset("aarati_sangraha.pdf").load();
    }
    public void onBackPressed() {
        Intent i=new Intent(AartiPdf.this, Book1.class);
        startActivity(i);
        finish();
    }
}