package com.ravi.commerce.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ravi.commerce.R;
import com.ravi.commerce.adpter.OneSubjectClassAdpter;
import com.ravi.commerce.bean.OneSubjectClassModel;

import java.util.ArrayList;
import java.util.List;

public class OneSubjectClassActivity extends AppCompatActivity {
    private RecyclerView recylc;
    private List<OneSubjectClassModel> classModelList;
    private OneSubjectClassAdpter adpter;
    private String urlOfImg = "https://www.freepik.com/free-photo/book-with-green-board-background_2244882.htm#page=1&query=math%20book&position=9";
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_one_subject_class);
        init();
        setValues();
    }

    private void init() {
        recylc = findViewById(R.id.recylc);
        classModelList = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar);
    }

    private void setValues() {
        toolbar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
        toolbar.setTitle("List Of Subject");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        OneSubjectClassModel model = new OneSubjectClassModel("book", "120 Rs", urlOfImg);
        for (int i = 0; i < 4; i++) {
            classModelList.add(model);
        }
        recylc.setLayoutManager(new LinearLayoutManager(this));
        adpter = new OneSubjectClassAdpter(this, classModelList);
        recylc.setAdapter(adpter);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
