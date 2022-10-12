package com.test.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.test.R;
import com.test.ui.model.ImageItem;
import java.util.ArrayList;


public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {
    Context mContext;
    ArrayList<ImageItem> lists;

    public ImageListAdapter(Context context, ArrayList<ImageItem> list) {
        this.mContext=context;
        this.lists=list;
    }


    @NonNull
    @Override
    public ImageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_image_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageListAdapter.ViewHolder holder, int position) {
        ImageItem list=lists.get(position);
        holder.text.setText(list.getAuthor());
        Glide.with(mContext)
                .load(list.getDownload_url())
                .override(list.getWidth(), list.getHeight())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            text=itemView.findViewById(R.id.text);
        }
    }
}
