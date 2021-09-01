package com.ravi.commerce.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ravi.commerce.R;
import com.ravi.commerce.common.CommonUtil;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_register;
    private TextView tv_forgot_pass;
    private EditText edt_user_name, edt_mail, edt_password, edt_cnf_password;
    private String strUserName, strMail, strPassword, strCnfPassword;

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
        edt_cnf_password.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        tv_forgot_pass.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        btn_register.setOnClickListener(this);
    }

    private void init() {
        btn_register = findViewById(R.id.btn_register);
        edt_user_name = findViewById(R.id.edt_user_name);
        edt_mail = findViewById(R.id.edt_mail);
        edt_password = findViewById(R.id.edt_password);
        edt_cnf_password = findViewById(R.id.edt_cnf_password);
        tv_forgot_pass = findViewById(R.id.tv_forgot_pass);
    }


//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
//    }

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
                //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
        }
    }

    private void isRegisterFuncCall() {
        strUserName = edt_user_name.getText().toString().trim();
        strMail = edt_mail.getText().toString().trim();
        strPassword = edt_password.getText().toString().trim();
        strCnfPassword = edt_cnf_password.toString().trim();
//
//        if (strUserName.equals("")) {
//            Toast.makeText(SignUpActivity.this, "User name must be fill", Toast.LENGTH_SHORT).show();
//        } else if (strMail.equals("")) {
//            Toast.makeText(SignUpActivity.this, "Mail must be fill", Toast.LENGTH_SHORT).show();
//        } else if (strPassword.equals("")) {
//            Toast.makeText(SignUpActivity.this, "Password must be fill", Toast.LENGTH_SHORT).show();
//        } else if (strCnfPassword.equals("")) {
//            Toast.makeText(SignUpActivity.this, "Confirm password must be fill", Toast.LENGTH_SHORT).show();
//        } else {
//            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
//            startActivity(intent);
//            finish();
//            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
//        }
//    }


        if (!CommonUtil.isValidUsername(strUserName)) {
            Toast.makeText(SignUpActivity.this, "Please Enter Valid User Name", Toast.LENGTH_SHORT).show();
            edt_user_name.setError("Please Enter Valid User Name");

        } else if (!CommonUtil.emailValidate(strMail)) {
            Toast.makeText(SignUpActivity.this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
            edt_mail.setError("Please Enter Valid Email");

        } else if (!CommonUtil.isPasswordValid(strPassword)) {
            Toast.makeText(SignUpActivity.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
            edt_password.setError("Please Enter Valid Password");

        } else if (!CommonUtil.isPasswordValid(strCnfPassword)) {
            Toast.makeText(SignUpActivity.this, "Please Enter Valid Confirm Password", Toast.LENGTH_SHORT).show();
            edt_cnf_password.setError("Please Enter Valid Confirm Password");

        } else if (!strPassword.equals(strCnfPassword)) {
            Toast.makeText(SignUpActivity.this, "Password not match", Toast.LENGTH_SHORT).show();
            edt_cnf_password.setError("Password not match");

        } else {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
          //  overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }
}
