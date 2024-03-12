package com.example.gurumandir.Room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gurumandir.HomePage2;
import com.example.gurumandir.R;
import com.example.gurumandir.Rituals.Pass1;
import com.example.gurumandir.Rituals.Pass2;
import com.example.gurumandir.comman.Config;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Room2 extends AppCompatActivity {

    TextView tv_room_name, tv_room_mo_no, tv_room_adhar_no ,tv_room_type ,tv_room_date_from
            ,tv_room_date_to , tv_room_days ,tv_room_amt;
    Button btn_ok;

    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room2);

        tv_room_name = findViewById(R.id.tv_room_name);
        tv_room_mo_no = findViewById(R.id.tv_room_mo_no);
        tv_room_adhar_no = findViewById(R.id.tv_room_adhar_no);
        tv_room_type = findViewById(R.id.tv_room_type);
        tv_room_date_from= findViewById(R.id.tv_room_date_from);
        tv_room_date_to = findViewById(R.id.tv_room_date_to);
        tv_room_days = findViewById(R.id.tv_room_days);
        tv_room_amt = findViewById(R.id.tv_room_amt);
        btn_ok = findViewById(R.id.btn_ok);

        Intent i = getIntent();
        String name1 = i.getStringExtra("name");
        String mono1 = i.getStringExtra("mobileNo");
        String adharno1 = i.getStringExtra("adharNo");
        String roomtype1 = i.getStringExtra("roomType");
        String dateFrom = i.getStringExtra("dateFrom");
        String dateTo = i.getStringExtra("dateTo");
        String days = i.getStringExtra("days");


        tv_room_name.setText(name1);
        tv_room_mo_no.setText(mono1);
        tv_room_adhar_no.setText(adharno1);
        tv_room_type.setText(roomtype1);
        tv_room_date_from.setText(dateFrom);
        tv_room_date_to.setText(dateTo);
        tv_room_days.setText(days);

        if(tv_room_type.getText().toString().equals("Single")){
            a=500;
            tv_room_amt.setText(Double.toString(a* (Integer.parseInt(tv_room_days.getText().toString()))));
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    room();
                }
            });
        }
        else{
            a=800;
            tv_room_amt.setText(Double.toString(a* (Integer.parseInt(tv_room_days.getText().toString()))));
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    room();
                }
            });
        }
    }

    private void room(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("Name", tv_room_name.getText().toString());
        params.put("Mobile_No", tv_room_mo_no.getText().toString());
        params.put("Adhar_No", tv_room_adhar_no.getText().toString());
        params.put("Room_Type", tv_room_type.getText().toString());
        params.put("Date_From", tv_room_date_from.getText().toString());
        params.put("Date_To", tv_room_date_to.getText().toString());
        params.put("Days", tv_room_days.getText().toString());
        params.put("Amount", tv_room_amt.getText().toString());

        client.post(Config.room, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        try {
                            String success = response.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(Room2.this, "Register Successful", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(Room2.this, Room3.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(Room2.this, "Server Error", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);

                        Toast.makeText(Room2.this, "Could not connect", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void onBackPressed() {
        Intent i=new Intent(Room2.this, Room1.class);
        startActivity(i);
        finish();
    }
}