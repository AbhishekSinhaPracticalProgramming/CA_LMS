package com.ravi.commerce.activity;

import static com.ravi.commerce.common.CommonUtil.imgList;
import static com.ravi.commerce.common.CommonUtil.nameList;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ravi.commerce.R;
import com.ravi.commerce.adpter.DashBoardAdpter;
import com.ravi.commerce.adpter.YourSubjectAdpter;
import com.ravi.commerce.bean.CourseModel;

import java.util.ArrayList;

public class YourSubjectActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private GridView gridview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.your_subject_activity);
        init();
        setValues();
    }

    private void init() {
        gridview = findViewById(R.id.gridview);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
        toolbar.setTitle("Your Subject");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setValues() {
        ArrayList<CourseModel> courseModelArrayList = new ArrayList<CourseModel>();
        courseModelArrayList.add(new CourseModel("DSA", R.drawable.gallery));
        courseModelArrayList.add(new CourseModel("JAVA", R.drawable.gallery));
        courseModelArrayList.add(new CourseModel("C++", R.drawable.gallery));
        courseModelArrayList.add(new CourseModel("Python", R.drawable.gallery));
        courseModelArrayList.add(new CourseModel("Javascript", R.drawable.gallery));
        courseModelArrayList.add(new CourseModel("DSA", R.drawable.gallery));

        YourSubjectAdpter adapter = new YourSubjectAdpter(this, courseModelArrayList);
        gridview.setAdapter(adapter);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
