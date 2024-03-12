package com.example.gurumandir.Rituals;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gurumandir.Donation.Donate1;
import com.example.gurumandir.Donation.Donate2;
import com.example.gurumandir.HomePage2;
import com.example.gurumandir.R;
import com.example.gurumandir.comman.Config;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class Pass1 extends AppCompatActivity {

    private Calendar calendar;
    private int year, month, day;

    TextView dateView;
    EditText et_name,et_mobile_no,et_address,rt_ritual_name,et_amount;
    Button btn_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass1);

        et_name = findViewById(R.id.et_name);
        et_mobile_no = findViewById(R.id.et_mobile_no);
        et_address = findViewById(R.id.et_address);
        rt_ritual_name = findViewById(R.id.et_ritual_name);
        et_amount = findViewById(R.id.et_amount);
        btn_done = findViewById(R.id.btn_done);
        dateView = findViewById(R.id.tv_date);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);


        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_name.getText().toString().isEmpty()) {
                    et_name.setError("Enter your name");
                } else if (et_mobile_no.getText().toString().isEmpty()) {
                    et_mobile_no.setError("Enter mobile no.");
                } else if (et_mobile_no.getText().toString().length() != 10) {
                    et_mobile_no.setError("Enter valid mobile no.");
                } else if (dateView.getText().toString().isEmpty()) {
                    dateView.setError("Select Date");
                } else if (et_address.getText().toString().isEmpty()) {
                    et_address.setError("Enter your Address");
                } else if (rt_ritual_name.getText().toString().isEmpty()) {
                    rt_ritual_name.setError("Enter Name of Ritual");
                } else if (et_amount.getText().toString().isEmpty()) {
                    et_amount.setError("Enter Your Amount");
                } else {
                    customer();
                }
            }

            private void customer() {
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();

                params.put("Name", et_name.getText().toString());
                params.put("Mobile_No", et_mobile_no.getText().toString());
                params.put("Date", dateView.getText().toString());
                params.put("Address", et_address.getText().toString());
                params.put("Ritual_Name", rt_ritual_name.getText().toString());
                params.put("Amount", et_amount.getText().toString());

                client.post(Config.customer, params, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                super.onSuccess(statusCode, headers, response);

                                try {
                                    String success = response.getString("success");
                                    if (success.equals("1")) {
                                        Toast.makeText(Pass1.this, "Register Successful", Toast.LENGTH_SHORT).show();

                                        Intent i = new Intent(Pass1.this, Pass2.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(Pass1.this, "Server Error", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                super.onFailure(statusCode, headers, throwable, errorResponse);

                                Toast.makeText(Pass1.this, "Could not connect", Toast.LENGTH_SHORT).show();
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

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


    public void next(View view) {
        Intent i = new Intent(Pass1.this, Pass2.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(Pass1.this, HomePage2.class);
        startActivity(i);
        finish();
    }
}