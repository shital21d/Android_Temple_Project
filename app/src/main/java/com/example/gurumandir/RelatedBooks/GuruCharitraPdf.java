package com.example.gurumandir.RelatedBooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.gurumandir.HomePage2;
import com.example.gurumandir.R;
import com.github.barteksc.pdfviewer.PDFView;

public class GuruCharitraPdf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru_charitra_pdf);

        PDFView pdfView=findViewById(R.id.pdf_gc);
        pdfView.fromAsset("guru_charitra.pdf").load();
    }
    public void onBackPressed() {
        Intent i=new Intent(GuruCharitraPdf.this, Book1.class);
        startActivity(i);
        finish();
    }
}