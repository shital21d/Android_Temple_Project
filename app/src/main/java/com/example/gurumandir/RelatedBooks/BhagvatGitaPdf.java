package com.example.gurumandir.RelatedBooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.gurumandir.R;
import com.github.barteksc.pdfviewer.PDFView;

public class BhagvatGitaPdf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhagvat_gita_pdf);

        PDFView pdfView=findViewById(R.id.pdf_bg);
        pdfView.fromAsset("bhagvat_gita.pdf").load();
    }
    public void onBackPressed() {
        Intent i=new Intent(BhagvatGitaPdf.this, Book1.class);
        startActivity(i);
        finish();
    }
}