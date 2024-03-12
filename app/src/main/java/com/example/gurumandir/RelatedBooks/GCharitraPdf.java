package com.example.gurumandir.RelatedBooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.gurumandir.R;
import com.github.barteksc.pdfviewer.PDFView;

public class GCharitraPdf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcharitra_pdf);

        PDFView pdfView=findViewById(R.id.pdf_gcm);
        pdfView.fromAsset("guru_charitra_in_marathi.pdf").load();
    }
    public void onBackPressed() {
        Intent i=new Intent(GCharitraPdf.this, Book1.class);
        startActivity(i);
        finish();
    }
}