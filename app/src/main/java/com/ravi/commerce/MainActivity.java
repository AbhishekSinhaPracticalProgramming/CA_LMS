package com.ravi.commerce;

import static com.ravi.commerce.common.CommonUtil.SPLASH_TIME;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ravi.commerce.activity.SignInActivity;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private TextView typeWriterView;

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
    }

    private void moveToNextPage() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

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