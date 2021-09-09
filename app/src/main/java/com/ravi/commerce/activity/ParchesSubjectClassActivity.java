package com.ravi.commerce.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ravi.commerce.R;
import com.squareup.picasso.Picasso;

public class ParchesSubjectClassActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView tv_name, tv_price, tv_actual_price;
    private Button btn_demo, btn_perches;
    private int parchesValue = 1;
    private String strImg, strDiscPrice, strPrice, strNameOfProduct;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.one_subject_class_details_activity);
        init();
        setValues();
    }

    private void setValues() {
        parchesValue=2;
        btn_perches.setOnClickListener(this);
        btn_demo.setOnClickListener(this);
        btn_perches.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        btn_demo.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        tv_name.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        tv_price.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        tv_actual_price.setTypeface(Typeface.createFromAsset(getAssets(), "font/trebuc.ttf"));
        if (parchesValue == 1) {
            btn_perches.setEnabled(false);
        }
        Intent intent = getIntent();
        if (intent != null) {
            strPrice = intent.getStringExtra("price");
            strDiscPrice = intent.getStringExtra("DiscPrice");
            strImg = intent.getStringExtra("img");
            strNameOfProduct = intent.getStringExtra("Name");
            tv_price.setText(strPrice);
            tv_actual_price.setText(strDiscPrice);
            tv_name.setText(strNameOfProduct);
            Picasso.get().load(strImg).into(image);
        }
        toolbar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
        toolbar.setTitle("Details Of Subject");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        image = findViewById(R.id.image);
        toolbar = findViewById(R.id.tool);
        btn_demo = findViewById(R.id.btn_demo);
        btn_perches = findViewById(R.id.btn_perches);
        tv_name = findViewById(R.id.tv_name);
        tv_price = findViewById(R.id.tv_price);
        tv_price.setPaintFlags(tv_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tv_actual_price = findViewById(R.id.tv_actual_price);
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

            case R.id.btn_demo:
                Intent intent = new Intent(ParchesSubjectClassActivity.this, ListOfDemoActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.btn_perches:
//                Intent intent1 = new Intent(ParchesSubjectClassActivity.this, PaymentScreenActivity.class);
//                startActivity(intent1);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


                Intent intent2 = new Intent(ParchesSubjectClassActivity.this, YourSubjectActivity.class);
                startActivity(intent2);
//            finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
    }
}
