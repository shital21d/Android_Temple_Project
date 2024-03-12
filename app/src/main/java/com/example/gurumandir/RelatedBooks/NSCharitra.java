package com.example.gurumandir.RelatedBooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.gurumandir.R;
import com.github.barteksc.pdfviewer.PDFView;

public class NSCharitra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nscharitra);

        PDFView pdfView=findViewById(R.id.pdf_nsc);
        pdfView.fromAsset("nrusimha_sarasvati_charitra.pdf").load();
    }
    public void onBackPressed() {
        Intent i=new Intent(NSCharitra.this, Book1.class);
        startActivity(i);
        finish();
    }
}