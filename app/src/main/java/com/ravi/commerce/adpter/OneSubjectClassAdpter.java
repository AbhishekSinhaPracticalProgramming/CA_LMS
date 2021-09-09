package com.ravi.commerce.adpter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ravi.commerce.R;
import com.ravi.commerce.activity.OneSubjectClassActivity;
import com.ravi.commerce.activity.ParchesSubjectClassActivity;
import com.ravi.commerce.bean.OneSubjectClassModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OneSubjectClassAdpter extends RecyclerView.Adapter<OneSubjectClassAdpter.ViewHolder> {
    private Context context;
    private List<OneSubjectClassModel> classModelListList;

    public OneSubjectClassAdpter(OneSubjectClassActivity homeActivity, List<OneSubjectClassModel> classModelList) {
        context = homeActivity;
        classModelListList = classModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_one_subject_class, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_price.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/trebuc.ttf"));
        holder.tv_name.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/trebuc.ttf"));
        holder.tv_actual_price.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/trebuc.ttf"));
        holder.tv_actual_price.setText(classModelListList.get(position).getPrice());
        holder.tv_price.setText("500 Rs");
        holder.tv_name.setText(classModelListList.get(position).getName());
        holder.tv_price.setPaintFlags(holder.tv_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Picasso.get().load(classModelListList.get(position).getImage()).into(holder.image);
        holder.rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity) context;
                Intent intent = new Intent(context, ParchesSubjectClassActivity.class);
                intent.putExtra("Name", classModelListList.get(position).getName());
                intent.putExtra("img", classModelListList.get(position).getImage());
                intent.putExtra("DiscPrice", classModelListList.get(position).getPrice());
                intent.putExtra("price", "500 Rs");
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    @Override
    public int getItemCount() {
        return classModelListList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_price, tv_actual_price;
        ImageView image;
        RelativeLayout rel;

        public ViewHolder(@NonNull View rowView) {
            super(rowView);
            tv_actual_price = (TextView) rowView.findViewById(R.id.tv_actual_price);
            tv_price = (TextView) rowView.findViewById(R.id.tv_price);
            tv_name = (TextView) rowView.findViewById(R.id.tv_name);
            image = rowView.findViewById(R.id.image);
            rel = rowView.findViewById(R.id.rel);
        }
    }
}
