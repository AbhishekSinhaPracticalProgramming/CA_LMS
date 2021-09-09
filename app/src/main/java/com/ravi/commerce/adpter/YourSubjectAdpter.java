package com.ravi.commerce.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.ravi.commerce.R;
import com.ravi.commerce.bean.CourseModel;

import java.util.ArrayList;

public class YourSubjectAdpter extends ArrayAdapter<CourseModel> {
    private int no = 26;
    private TextView progressText;


    public YourSubjectAdpter(@NonNull Context context, ArrayList<CourseModel> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        int i = 0;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.row_your_subject, parent, false);
        }

        CourseModel courseModel = getItem(position);
        TextView courseTV = listitemView.findViewById(R.id.tv_name);
         CircleProgressBar mLineProgressBar= listitemView.findViewById(R.id.solid_progress);

        // ImageView courseIV = listitemView.findViewById(R.id.image);
        courseTV.setText(courseModel.getCourse_name());
        //  courseIV.setImageResource(courseModel.getImgid());
        mLineProgressBar.setProgress(40);

        return listitemView;
    }
}
