package com.example.gurumandir.Room;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gurumandir.HomePage2;
import com.example.gurumandir.R;

import java.util.Calendar;

public class Room1 extends AppCompatActivity {

    private Calendar calendar;
    private int year, month, day;

    EditText et_customer_name,et_customer_mo_no,et_customer_adhar_no,et_customer_days;
    Spinner sp_customer_room_type;
    TextView tv_customer_date_from,tv_customer_date_to;
    Button btn_customer_done;
    ImageView iv_date_to;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room1);

        et_customer_name = findViewById(R.id.et_customer_name);
        et_customer_mo_no = findViewById(R.id.et_customer_mo_no);
        et_customer_adhar_no = findViewById(R.id.et_customer_adhar_no);
        sp_customer_room_type = findViewById(R.id.sp_customer_room_type);
        tv_customer_date_from = findViewById(R.id.tv_customer_date_from);
        tv_customer_date_to = findViewById(R.id.tv_customer_date_to);
        btn_customer_done = findViewById(R.id.btn_customer_done);
        iv_date_to = findViewById(R.id.iv_date_to);
        et_customer_days = findViewById(R.id.et_customer_days);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);



        btn_customer_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_customer_name.getText().toString().isEmpty())
                {
                    et_customer_name.setError("Enter your name");
                }
                else if (et_customer_mo_no.getText().toString().isEmpty())
                {
                    et_customer_mo_no.setError("Enter mobile no.");
                }
                else if (et_customer_mo_no.getText().toString().length() != 10)
                {
                    et_customer_mo_no.setError("Enter valid mobile no.");
                }
                else if (et_customer_adhar_no.getText().toString().isEmpty())
                {
                    et_customer_adhar_no.setError("Enter your Adhar no.");
                }
                else if (et_customer_adhar_no.getText().toString().length() != 12)
                {
                    et_customer_adhar_no.setError("Enter valid Adhar no.");
                }
                else if (tv_customer_date_from.getText().toString().isEmpty())
                {
                    tv_customer_date_from.setError("Enter date");
                }
                else if (tv_customer_date_to.getText().toString().isEmpty())
                {
                    tv_customer_date_to.setError("Enter date");
                }
                else if (et_customer_days.getText().toString().isEmpty())
                {
                    et_customer_days.setError("Enter no. of days");
                }
                else
                {
                    Intent i=new Intent(Room1.this, Room2.class);
                    i.putExtra("name",et_customer_name.getText().toString());
                    i.putExtra("mobileNo",et_customer_mo_no.getText().toString());
                    i.putExtra("adharNo",et_customer_adhar_no.getText().toString());
                    i.putExtra("roomType",sp_customer_room_type.getSelectedItem().toString());
                    i.putExtra("dateFrom",tv_customer_date_from.getText().toString());
                    i.putExtra("dateTo",tv_customer_date_to.getText().toString());
                    i.putExtra("days",et_customer_days.getText().toString());

                    startActivity(i);
                    finish();
                }
            }
        });

        iv_date_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Room1.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        tv_customer_date_to.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDateFrom(View view) {
        showDialog(999);

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
        tv_customer_date_from.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void onBackPressed() {
        Intent i=new Intent(Room1.this, HomePage2.class);
        startActivity(i);
        finish();
    }
}