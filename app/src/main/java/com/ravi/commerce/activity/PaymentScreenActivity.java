package com.ravi.commerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentScreenActivity extends AppCompatActivity implements PaymentResultListener, View.OnClickListener {
    private Button btn_perches;
    private Toolbar tool;
    private ImageView image;
    private TextView tv_name, tv_price, tv_actual_price;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.payment_details_activity);
        init();
        setVales();
    }

    private void setVales() {
        btn_perches.setOnClickListener(this);
    }

    private void init() {
        Checkout.preload(getApplicationContext());
        btn_perches = findViewById(R.id.btn_perches);
        tv_actual_price = findViewById(R.id.tv_actual_price);
        image = findViewById(R.id.image);
        tv_name = findViewById(R.id.tv_name);
        tv_price = findViewById(R.id.tv_price);
        tool = findViewById(R.id.tool);
        tool.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
        tool.setTitle("Payments Details");
        tool.setTitleTextColor(getResources().getColor(android.R.color.white));
        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        try {
            Toast.makeText(this, "Payment Successful  :   " + s, Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(PaymentScreenActivity.this, YourSubjectActivity.class);
//            startActivity(intent);
////            finish();
//            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        } catch (Exception e) {
            Log.e("TAG", "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(this, "Payment failed: " + i + " " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("TAG", "Exception in onPaymentError", e);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.btn_perches:
                callPayment();
                break;
        }
    }

    private void callPayment() {
        String fpaise = "2";
        // rounding off the amount.
        int amount = Math.round(Float.parseFloat(fpaise) * 100);
        // initialize Razorpay account.
        Checkout checkout = new Checkout();
        // set your id as below
        checkout.setKeyID("rzp_test_xGHDOPMlYrXf4t");
        // set image
//        checkout.setImage(R.drawable.gfgimage);
        // initialize json object
        JSONObject object = new JSONObject();
        try {
            object.put("name", "RaviEcommerce");
            object.put("description", "Test payment");
            object.put("theme.color", "");
            object.put("currency", "INR");
            object.put("amount", amount);
            object.put("prefill.contact", "9755771985");
            object.put("prefill.email", "softwaredeveloperkkt@gmail.com");
            checkout.open(PaymentScreenActivity.this, object);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
//    }

    @Override
    public void onBackPressed() {
        //remove call to the super class
        //super.onBackPressed();
    }
}
