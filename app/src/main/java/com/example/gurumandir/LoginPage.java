package com.example.gurumandir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gurumandir.comman.Config;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginPage extends AppCompatActivity {

    ImageView img_iogo;
    EditText et_Username,et_Password;
    CheckBox chk_show_hide;
    Button btn_login,btn_register;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        getSupportActionBar().hide();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        if(preferences.getBoolean("islogin",false))
        {
            startActivity(new Intent(LoginPage.this,HomePage2.class));
            finish();
        }

        img_iogo=findViewById(R.id.iv_logo);
        et_Username=findViewById(R.id.et_username);
        et_Password=findViewById(R.id.et_password);
        chk_show_hide=findViewById(R.id.chk_login_password_show_hide);
        btn_login=findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        chk_show_hide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b)
                {
                    et_Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    et_Password.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_Username.getText().toString().isEmpty())
                {
                    et_Username.setError("Enter Your Username");
                }
                else if(et_Username.getText().toString().length()<8)
                {
                    et_Username.setError("Enter Username greater than 8");
                }
                else if(et_Password.getText().toString().isEmpty())
                {
                    et_Password.setError("Enter Your Password");
                }
                else if(et_Password.getText().toString().length()<8)
                {
                    et_Password.setError("Enter Password greater than 8");
                }

                else
                {
                    checkLoginUser();
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(LoginPage.this,RegistrationPage.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void checkLoginUser() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("Username",et_Username.getText().toString());
        params.put("Password",et_Password.getText().toString());

        client.post(Config.loginUser,params,new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode,headers,response);
                        try {
                            String success =response.getString("success");

                            if (success.equals("1"))
                            {
                                editor.putBoolean("islogin",true).commit();
                                editor.putString("Username",et_Username.getText().toString()).commit();

                                Toast.makeText(LoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(LoginPage.this,HomePage2.class);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(LoginPage.this, "Enter correct credentials", Toast.LENGTH_SHORT).show();
                            }
                        }

                        catch (JSONException e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(LoginPage.this,"Could not connect",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}