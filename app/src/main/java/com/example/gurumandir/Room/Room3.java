package com.example.gurumandir.Room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gurumandir.Donation.Donate2;
import com.example.gurumandir.HomePage2;
import com.example.gurumandir.R;
import com.example.gurumandir.comman.Config;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Room3 extends AppCompatActivity {

    TextView tv_r;
    EditText et_name,et_tid;
    Button btn_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room3);
        tv_r= findViewById(R.id.tv);
        et_name= findViewById(R.id.et_verify_name);
        et_tid= findViewById(R.id.et_tid);
        btn_done= findViewById(R.id.btn_tid_done);

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_tid.getText().toString().isEmpty()){
                    et_tid.setError("enter transection id");
                } else if (et_name.getText().toString().isEmpty()){
                    et_name.setError("enter name");
                } else {
                    trverify();
                }
            }
            private void trverify() {
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();

                params.put("Id", tv_r.getText().toString());
                params.put("Name", et_name.getText().toString());
                params.put("T_id", et_tid.getText().toString());

                client.post(Config.trVerify, params, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                super.onSuccess(statusCode, headers, response);

                                try {
                                    String success = response.getString("success");
                                    if (success.equals("1")) {
                                        Toast.makeText(Room3.this, "Payment Done", Toast.LENGTH_SHORT).show();

                                        Intent i = new Intent(Room3.this, HomePage2.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(Room3.this, "Server Error", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                super.onFailure(statusCode, headers, throwable, errorResponse);

                                Toast.makeText(Room3.this, "Could not connect", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
    }
}