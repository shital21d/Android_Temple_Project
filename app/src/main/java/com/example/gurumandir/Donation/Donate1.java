package com.example.gurumandir.Donation;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gurumandir.HomePage2;
import com.example.gurumandir.R;
import com.example.gurumandir.comman.Config;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Donate1 extends AppCompatActivity {

    private Calendar calendar;
    private int year, month, day;

    TextView date;
    EditText et_donar_name,et_donar_mobile_no,et_donar_address,et_donar_city,et_donar_amount;
    Button btn_donar_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate1);
        setTitle("Donation");

        et_donar_name = findViewById(R.id.et_donar_name);
        et_donar_mobile_no = findViewById(R.id.et_donar_mobile_no);
        et_donar_address = findViewById(R.id.et_donar_address);
        et_donar_city = findViewById(R.id.et_donar_city);
        et_donar_amount = findViewById(R.id.et_donar_amount);
        btn_donar_done = findViewById(R.id.btn_donar_done);
        date = findViewById(R.id.tv_donar_date);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);


        btn_donar_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_donar_name.getText().toString().isEmpty()) {
                    et_donar_name.setError("Enter your name");
                } else if (et_donar_mobile_no.getText().toString().isEmpty()) {
                    et_donar_mobile_no.setError("Enter mobile no.");
                } else if (et_donar_mobile_no.getText().toString().length() != 10) {
                    et_donar_mobile_no.setError("Enter valid mobile no.");
                } else if (date.getText().toString().isEmpty()) {
                    date.setError("Select Date");
                } else if (et_donar_address.getText().toString().isEmpty()) {
                    et_donar_address.setError("Enter your Address");
                } else if (et_donar_city.getText().toString().isEmpty()) {
                    et_donar_city.setError("Enter Your City");
                } else if (et_donar_amount.getText().toString().isEmpty()) {
                    et_donar_amount.setError("Enter Your Amount");
                } else {
                    donar();
                }
            }

            private void donar() {
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();

                params.put("Name", et_donar_name.getText().toString());
                params.put("Mobile_No", et_donar_mobile_no.getText().toString());
                params.put("Date", date.getText().toString());
                params.put("Address", et_donar_address.getText().toString());
                params.put("City", et_donar_city.getText().toString());
                params.put("Amount", et_donar_amount.getText().toString());

                client.post(Config.donar, params, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                super.onSuccess(statusCode, headers, response);

                                try {
                                    String success = response.getString("success");
                                    if (success.equals("1")) {
                                        Toast.makeText(Donate1.this, "Register Successful", Toast.LENGTH_SHORT).show();

                                        Intent i = new Intent(Donate1.this, Donate2.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(Donate1.this, "Server Error", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                super.onFailure(statusCode, headers, throwable, errorResponse);

                                Toast.makeText(Donate1.this, "Could not connect", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "select date",Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    public void showDate(int year, int month, int day) {
        date.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(Donate1.this, HomePage2.class);
        startActivity(i);
        finish();
    }
}