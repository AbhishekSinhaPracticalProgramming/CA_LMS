package com.ravi.commerce.adpter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ravi.commerce.R;

import java.util.List;

public class DashBoardAdpter extends BaseAdapter {
    private Context context;
    private List<Integer> imagesLIST;
    private List<String> names;
    private static LayoutInflater inflater = null;

    public DashBoardAdpter(Context homeActivity, List<String> nameList, List<Integer> imgList) {
        context = homeActivity;
        names = nameList;
        imagesLIST = imgList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class Holder {
        TextView tvName;
        ImageView image;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.row_dashboard, null);
        holder.tvName = (TextView) rowView.findViewById(R.id.tv_name);
        holder.tvName.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/trebuc.ttf"));
        holder.tvName.setText(names.get(position));
        holder.image = rowView.findViewById(R.id.image);
        holder.image.setImageResource(imagesLIST.get(position));

//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (names.get(position).contains("Doctor")) {
//                    Activity activity = (Activity) context;
//                    Intent intent = new Intent(context, DoctorActivity.class);
//                    activity.startActivity(intent);
//                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//
//                }
//                if (names.get(position).contains("Lab")) {
//                    Activity activity = (Activity) context;
//                    Intent intent = new Intent(context, LabActivity.class);
//                    activity.startActivity(intent);
//                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                }
//                if (names.get(position).contains("Medical")) {
//                    Activity activity = (Activity) context;
//                    Intent intent = new Intent(context, MedicalActivity.class);
//                    activity.startActivity(intent);
//                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//
//                }
//                if (names.get(position).contains("Appointment")) {
//                    Activity activity = (Activity) context;
//                    Intent intent = new Intent(context, AppoinmentActivity.class);
//                    activity.startActivity(intent);
//                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                }
//            }
//        });
        return rowView;
    }
}
