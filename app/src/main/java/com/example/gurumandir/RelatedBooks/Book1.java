package com.example.gurumandir.RelatedBooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.gurumandir.Donation.Donate1;
import com.example.gurumandir.HomePage2;
import com.example.gurumandir.R;

public class Book1 extends AppCompatActivity {
    ImageView iv_guru_charitra,iv_n_s_charitra,iv_aarti,iv_bhagvat_gita,iv_g_charitra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book1);
        setTitle("Library");

        iv_guru_charitra=findViewById(R.id.iv_guru_charitra);
        iv_n_s_charitra=findViewById(R.id.iv_n_s_charitra);
        iv_aarti=findViewById(R.id.iv_aarti);
        iv_bhagvat_gita=findViewById(R.id.iv_bhagvat_gita);
        iv_g_charitra=findViewById(R.id.iv_g_charitra);

        iv_guru_charitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Book1.this,GuruCharitraPdf.class);
                startActivity(i);
            }
        });
        iv_n_s_charitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Book1.this,NSCharitra.class);
                startActivity(i);
            }
        });
        iv_aarti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Book1.this,AartiPdf.class);
                startActivity(i);
            }
        });
        iv_bhagvat_gita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Book1.this,BhagvatGitaPdf.class);
                startActivity(i);
            }
        });
        iv_g_charitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Book1.this,GCharitraPdf.class);
                startActivity(i);
            }
        });
    }
    public void onBackPressed() {
        Intent i=new Intent(Book1.this, HomePage2.class);
        startActivity(i);
        finish();
    }
}