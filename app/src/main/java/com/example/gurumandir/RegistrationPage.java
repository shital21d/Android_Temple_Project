package com.example.gurumandir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gurumandir.comman.Config;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RegistrationPage extends AppCompatActivity {

    EditText et_name,et_mobileNo,et_email,et_username,et_password;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        setTitle("Registration Page");

        et_name = findViewById(R.id.et_registration_name);
        et_mobileNo = findViewById(R.id.et_registration_mobileno);
        et_email = findViewById(R.id.et_registration_email);
        et_username = findViewById(R.id.et_registration_username);
        et_password = findViewById(R.id.et_registration_password);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (et_name.getText().toString().isEmpty())
                {
                    et_name.setError("Enter your name");
                }
                else if (et_mobileNo.getText().toString().isEmpty())
                {
                    et_mobileNo.setError("Enter mobile no.");
                }
                else if (et_mobileNo.getText().toString().length() != 10)
                {
                    et_mobileNo.setError("Enter valid mobile no.");
                }
                else if (et_email.getText().toString().isEmpty())
                {
                    et_email.setError("Enter your E-mail");
                }
                else if (!et_email.getText().toString().contains("@") ||
                        !et_email.getText().toString().contains(".com") ||
                        et_email.getText().toString().contains(" "))
                {
                    et_email.setError("Enter valid e-mail");
                }
                else if (et_username.getText().toString().isEmpty())
                {
                    et_username.setError("Enter Your Username");
                }
                else if (et_username.getText().toString().length() < 8)
                {
                    et_username.setError("Enter Username greater than 8");
                }
                else if (et_password.getText().toString().isEmpty())
                {
                    et_password.setError("Enter Your Password");
                }
                else if (et_password.getText().toString().length() < 8)
                {
                    et_password.setError("Enter Password greater than 8");
                }
                else
                {
                    addRegisterUser();
                }
            }
        });
    }

    private void addRegisterUser() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("Name",et_name.getText().toString());
        params.put("Mobile_No",et_mobileNo.getText().toString());
        params.put("Email",et_email.getText().toString());
        params.put("Username",et_username.getText().toString());
        params.put("Password",et_password.getText().toString());

        client.post(Config.newUser,params,new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode,headers,response);
                        try {
                            String success =response.getString("success");

                            if (success.equals("1"))
                            {
                                Toast.makeText(RegistrationPage.this, "Register Successful", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(RegistrationPage.this,LoginPage.class);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(RegistrationPage.this, "Server Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        catch (JSONException e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(RegistrationPage.this,"Could not connect",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(RegistrationPage.this,LoginPage.class);
        startActivity(i);
        finish();

    }
}