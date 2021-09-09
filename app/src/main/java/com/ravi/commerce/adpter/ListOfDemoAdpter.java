package com.ravi.commerce.adpter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ravi.commerce.R;

import java.util.List;

public class ListOfDemoAdpter extends RecyclerView.Adapter<ListOfDemoAdpter.ViewHolder> {
    private Context context;
    private List<String> classModelListList;
    String videoUrl = "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1";


    public ListOfDemoAdpter(Context homeActivity, List<String> classModelList) {
        context = homeActivity;
        classModelListList = classModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_of_demo, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri uri = Uri.parse(videoUrl);
        holder.videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(context);
        mediaController.setAnchorView(holder.videoView);
        mediaController.setMediaPlayer(holder.videoView);
        holder.videoView.setMediaController(mediaController);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.videoView.start();
            }
        });
        holder.videoView.getRotation();
    }

    @Override
    public int getItemCount() {
        return classModelListList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;

        public ViewHolder(@NonNull View rowView) {
            super(rowView);
            videoView = (VideoView) rowView.findViewById(R.id.videoView);

        }
    }
}
