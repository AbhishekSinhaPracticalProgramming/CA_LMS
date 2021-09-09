package com.ravi.commerce.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ravi.commerce.R;
import com.ravi.commerce.adpter.ListOfDemoAdpter;

import java.util.ArrayList;
import java.util.List;

public class ListOfDemoActivity extends AppCompatActivity {
    private RecyclerView recylc;
    private List<String> classModelList;
    private ListOfDemoAdpter adpter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        toolbar.setTitle("List Of Demo Subject");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        classModelList.add("https://www.youtube.com/watch?v=d2XlOkP4qCY");
        recylc.setLayoutManager(new LinearLayoutManager(this));
        adpter = new ListOfDemoAdpter(ListOfDemoActivity.this, classModelList);
        recylc.setAdapter(adpter);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
