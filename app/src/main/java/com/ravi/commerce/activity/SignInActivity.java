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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.ravi.commerce.R;
import com.ravi.commerce.common.CommonUtil;
import com.ravi.commerce.pref.SharedpreferenceUtility;
import com.ravi.commerce.resp.login.LoginResponse;
import com.ravi.commerce.rest.Rest;
import com.ravi.commerce.rest.RetrofitClient;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    public static final String TAG = "SignInActivity";
    private Button btn_login;
    private TextView tv_forgot_pass, tv_sing_up, tv_dont_have_account;
    private EditText edt_mail, edt_password;
    private String strMail, strPaassword;
    private AlertDialog dialog;
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        init();
        setValues();
    }

    private void setValues() {
        tv_dont_have_account.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        tv_sing_up.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        btn_login.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        edt_mail.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        edt_password.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        tv_forgot_pass.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        tv_forgot_pass.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        tv_sing_up.setOnClickListener(this);
    }

    private void init() {
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        tv_forgot_pass = findViewById(R.id.tv_forgot_pass);
        edt_mail = findViewById(R.id.edt_mail);
        edt_password = findViewById(R.id.edt_password);
        tv_dont_have_account = findViewById(R.id.tv_dont_have_account);
        tv_sing_up = findViewById(R.id.tv_sing_up);

        dialog = new SpotsDialog.Builder().setContext(SignInActivity.this).build();
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);


//        // Configure Google Sign In
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();

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
            case R.id.btn_login:
                isLoginFunc();
                break;

            case R.id.tv_forgot_pass:
                forgotPassFuncCall();
                break;

            case R.id.tv_sing_up:
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
    }

    private void forgotPassFuncCall() {
        Toast.makeText(SignInActivity.this, "Work In Progress", Toast.LENGTH_SHORT).show();
    }

    private void isLoginFunc() {
        strMail = edt_mail.getText().toString().trim();
        strPaassword = edt_password.getText().toString().trim();

        if (!CommonUtil.emailValidate(strMail)) {
            Toast.makeText(SignInActivity.this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();

        } else if (strPaassword.equals("")) {
            Toast.makeText(SignInActivity.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
        } else {
            loginApiCalling();
        }
    }


    private void loginApiCalling() {
        if (CommonUtil.isNetworkConnected(this)) {
            dialog.show();
            Rest apiInterface = RetrofitClient.getClient().create(Rest.class);
            Call<LoginResponse> call = apiInterface.getUserLogin( strMail, strPaassword);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Log.d("TAG", response.code() + "");
                    dialog.dismiss();
                    LoginResponse model = response.body();

                    Log.e(TAG, "onResponse: "+model.isStatus());
                    Log.e(TAG, "onResponse: "+model.getMessage());

                    if (model.isStatus()==true  && response.isSuccessful()) {

                        Toast.makeText(SignInActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();

                        SharedpreferenceUtility.getInstance(SignInActivity.this).putString(CommonUtil.LOGIN, strMail);
                        SharedpreferenceUtility.getInstance(SignInActivity.this).putString(CommonUtil.PASS, strPaassword);

                        Intent intent = new Intent(SignInActivity.this, DashBoardActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }else {
                        Toast.makeText(SignInActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    dialog.dismiss();
                    Log.e(TAG, "onFailure: Message  " + t.getMessage());
                    Toast.makeText(SignInActivity.this, "Please check your internet connection .", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            dialog.dismiss();
            Toast.makeText(this, "Please check your internet connection .", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately

            }
        }
    }

}
