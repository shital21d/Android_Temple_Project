package com.example.gurumandir;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gurumandir.comman.Config;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Profile extends AppCompatActivity {

    TextView tv_name,tv_mobileNo,tv_email,tv_username,tv_no_record,tv_logout;
    ProgressBar prog;
    ImageView qr_code;

    String Username;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    Bitmap bitmap;
    public final static int qr_height=500;
    public final static int qr_width=500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setTitle("My Profile");

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        tv_name = findViewById(R.id.tv_registration_name);
        tv_mobileNo = findViewById(R.id.tv_registration_mobileno);
        tv_email = findViewById(R.id.et_registration_email);
        tv_username = findViewById(R.id.tv_registration_username);
        tv_no_record = findViewById(R.id.tv_no_record);
        tv_logout = findViewById(R.id.tv_logout);

        qr_code = findViewById(R.id.qr_code);
        prog = findViewById(R.id.progress_bar);

        Username =preferences.getString("Username","");

        try {
            bitmap=TextToEncodeImage(Username);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        qr_code.setImageBitmap(bitmap);

        getMydetails();

        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                logout();
            }
        });
    }

   Bitmap TextToEncodeImage(String value) throws WriterException {
       BitMatrix bitMatrix;
       try {
           bitMatrix=new MultiFormatWriter().encode(value, BarcodeFormat.QR_CODE,qr_width,qr_height,null);
       }
       catch (IllegalArgumentException illegalArgumentException) {
           return null;
       }
       int bitMatrixWidth=bitMatrix.getWidth();
       int bitMatrixHeight=bitMatrix.getHeight();

       int[] pixels=new int[bitMatrixWidth * bitMatrixHeight];
       for(int y=0; y<bitMatrixHeight; y++)
       {
           int offset =y*bitMatrixHeight;

           for(int x=0; x<bitMatrixWidth; x++) {
               pixels[offset + x]=bitMatrix.get(x,y)?
                       getResources().getColor(R.color.white):getResources().getColor(R.color.black);
           }
       }
       Bitmap bitmap=Bitmap.createBitmap(bitMatrixWidth,bitMatrixHeight,Bitmap.Config.ARGB_4444);
       bitmap.setPixels(pixels,0,500,0,0,bitMatrixWidth,bitMatrixHeight);
       return bitmap;
   }

    private void logout()
    {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);

        ab.setTitle("Logout");
        ab.setMessage("Are you sure to logout..?");
        ab.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        ab.setNegativeButton("Logout", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                editor.putBoolean("islogin",false).commit();
                startActivity(new Intent(Profile.this,LoginPage.class));
                finish();
            }
        }).create().show();
    }

    private void getMydetails() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("Username",Username);
        client.post(Config.getMyData,params,new JsonHttpResponseHandler()
                {
                    @Override
                    public void onStart() {
                        super.onStart();
                        prog.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        prog.setVisibility(View.GONE);

                        try {
                            JSONArray jsonArray = response.getJSONArray("getMyDetails");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject =jsonArray.getJSONObject(i);
                                String id1 = jsonObject.getString("Id");
                                String name1 = jsonObject.getString("Name");
                                String mobile_no1 = jsonObject.getString("Mobile_No");
                                String email1 = jsonObject.getString("Email");
                                String username1 = jsonObject.getString("Username");

                                tv_name.setText(name1);
                                tv_mobileNo.setText(mobile_no1);
                                tv_email.setText(email1);
                                tv_username.setText(username1);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);

                        Toast.makeText(Profile.this,"Could not connect",Toast.LENGTH_SHORT).show();
                        prog.setVisibility(View.GONE);
                    }
                }
        );
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(Profile.this,HomePage2.class);
        startActivity(i);
        finish();
    }
}