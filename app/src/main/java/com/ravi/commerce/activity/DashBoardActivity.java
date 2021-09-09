package com.ravi.commerce.activity;

import static com.ravi.commerce.common.CommonUtil.getImg;
import static com.ravi.commerce.common.CommonUtil.getName;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ravi.commerce.R;
import com.ravi.commerce.adpter.DashBoardAdpter;
import com.ravi.commerce.adpter.ViewPagerAdapter;
import com.ravi.commerce.fragment.HomeFragment;
import com.ravi.commerce.fragment.ProfileFragment;
import com.ravi.commerce.fragment.SettingFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DashBoardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    public static final String TAG = "DashBoardActivity";
    private ViewPager viewPager;
    private LinearLayout sliderDotspanel;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private int dotscount;
    Fragment newFragment;

    private ImageView[] dots;
    boolean doubleBackToExitPressedOnce = false;
    private GridView gridview;
    private List<String> nameList = new ArrayList<String>();
    private List<Integer> imgList = new ArrayList<Integer>();
    private Toolbar toolbar;
    private TextView tv_summury, tv_summury_view, tv_member, tv_member_view, tv_micro, tv_micro_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_dashboard);
        init();
        setValues();
    }

    private void setValues() {
        if (nameList != null && imgList != null) {
            nameList = getName();
            imgList = getImg();
        }
        DashBoardAdpter gridViewAdapter = new DashBoardAdpter(DashBoardActivity.this, nameList, imgList);
        gridview.setAdapter(gridViewAdapter);
    }

    private void init() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        bottomNavigationView.setSelectedItemId(R.id.id_home);
        gridview = findViewById(R.id.gridview);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];
        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_home:
                newFragment = new HomeFragment();
                switchFragment(newFragment);
                return true;

            case R.id.id_profile:
                newFragment = new ProfileFragment();
                switchFragment(newFragment);
                return true;

            case R.id.id_setting:
                newFragment = new SettingFragment();
                switchFragment(newFragment);
                return true;

            case R.id.id_dashboard:
                Intent intent = new Intent(this, DashBoardActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;
        }
        return false;
    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            DashBoardActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
        }

//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce = false;
//            }
//        }, 2000);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void switchFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment).addToBackStack("fragment");
        fragmentTransaction.commit();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
