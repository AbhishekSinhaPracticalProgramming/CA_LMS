package com.ravi.commerce.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ravi.commerce.R;
import com.ravi.commerce.common.CommonUtil;
import com.ravi.commerce.resp.login.LoginResponse;
import com.ravi.commerce.resp.register.RegisterResponse;
import com.ravi.commerce.rest.Rest;
import com.ravi.commerce.rest.RetrofitClient;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_register;
    private TextView tv_forgot_pass;
    private EditText edt_user_name, edt_mail, edt_password, edt_lst_name, edt_phone;
    private String strUserName, strMail, strPassword, strPhone, strLastName;
    private AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        setValues();
    }

    private void setValues() {
        btn_register.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        edt_mail.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        edt_password.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        edt_user_name.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        edt_lst_name.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        edt_phone.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        tv_forgot_pass.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        btn_register.setOnClickListener(this);
        dialog = new SpotsDialog.Builder().setContext(SignUpActivity.this).build();
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
    }

    private void init() {
        btn_register = findViewById(R.id.btn_register);
        edt_user_name = findViewById(R.id.edt_user_name);
        edt_mail = findViewById(R.id.edt_mail);
        edt_password = findViewById(R.id.edt_password);
        edt_phone = findViewById(R.id.edt_phone_no);
        edt_lst_name = findViewById(R.id.edt_lst_name);
        tv_forgot_pass = findViewById(R.id.tv_forgot_pass);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_register:
                isRegisterFuncCall();
                break;

            case R.id.tv_forgot_pass:
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
    }

    private void isRegisterFuncCall() {
        strUserName = edt_user_name.getText().toString().trim();
        strMail = edt_mail.getText().toString().trim();
        strPassword = edt_password.getText().toString().trim();
        strPhone = edt_phone.getText().toString().trim();
        strLastName = edt_lst_name.getText().toString().trim();

        if (!CommonUtil.isFullname(strUserName)) {
            Toast.makeText(SignUpActivity.this, "Please Enter Valid First Name", Toast.LENGTH_SHORT).show();

        } else if (!CommonUtil.emailValidate(strMail)) {
            Toast.makeText(SignUpActivity.this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();

        } else if (!CommonUtil.isPasswordValid(strPassword)) {
            Toast.makeText(SignUpActivity.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();

        } else if (!CommonUtil.validatePhoneNumber(strPhone)) {
            Toast.makeText(SignUpActivity.this, "Please Enter Valid Phone", Toast.LENGTH_SHORT).show();

        } else if (!CommonUtil.isFullname(strLastName)) {
            Toast.makeText(SignUpActivity.this, "Please Enter Valid Last Name", Toast.LENGTH_SHORT).show();
        } else {
            registerApiCalling();
        }
    }

    private void registerApiCalling() {
        if (CommonUtil.isNetworkConnected(this)) {
            dialog.show();
            Rest apiInterface = RetrofitClient.getClient().create(Rest.class);
            Call<RegisterResponse> call = apiInterface.getUserRegister(strUserName, strMail, strPassword, strPhone, strLastName);
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    Log.d("TAG", response.code() + "");
                    dialog.dismiss();
                    RegisterResponse model = response.body();
                    Log.e("TAG", "onResponse: " + model.isStatus());

                    if (model.isStatus()==true  && response.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    }else {
                        Toast.makeText(SignUpActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    dialog.dismiss();
                    Log.e("TAG", "onFailure: Message  " + t.getMessage());
                    Toast.makeText(SignUpActivity.this, "Please check your internet connection .", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            dialog.dismiss();
            Toast.makeText(this, "Please check your internet connection .", Toast.LENGTH_SHORT).show();
        }

    }
}
