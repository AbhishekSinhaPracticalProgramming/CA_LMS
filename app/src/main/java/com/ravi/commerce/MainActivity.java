package com.ravi.commerce;

import static com.ravi.commerce.common.CommonUtil.SPLASH_TIME;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ravi.commerce.activity.DashBoardActivity;
import com.ravi.commerce.activity.SignInActivity;
import com.ravi.commerce.common.CommonUtil;
import com.ravi.commerce.pref.SharedpreferenceUtility;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private TextView typeWriterView;
    private String strUserName, strPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setValues();
        moveToNextPage();
    }

    private void setValues() {
        typeWriterView.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        strUserName = SharedpreferenceUtility.getInstance(MainActivity.this).getString(CommonUtil.LOGIN);
        strPass = SharedpreferenceUtility.getInstance(MainActivity.this).getString(CommonUtil.PASS);
        Log.e(TAG, "run:    " + strUserName );
    }

    private void moveToNextPage() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!(strUserName.equals("") && strPass.equals(""))){
                    Intent intent = new Intent(MainActivity.this, DashBoardActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                } else {

                    Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        }, SPLASH_TIME);
    }

    private void init() {
        typeWriterView = findViewById(R.id.typeWriterView);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}