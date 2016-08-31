package com.befiring.easypicture.adapter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.befiring.easypicture.R;
import com.befiring.easypicture.bean.Image;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/31.
 */
public class FirstGridAdapter extends RecyclerView.Adapter<FirstGridAdapter.FirstViewHolder> {

    List<Image> images;

    @Override
    public FirstViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_gird, parent, false);
        return new FirstViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FirstViewHolder holder, int position) {
        Image image = images.get(position);
        Glide.with(holder.imageView.getContext()).load(image.getImage_url()).into(holder.imageView);
        holder.textView.setText(image.getDescription());
    }

    @Override
    public int getItemCount() {
        return images==null?0:images.size();
    }

    public void setImages(List<Image> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    class FirstViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.first_item_image)
        ImageView imageView;
        @Bind(R.id.first_item_text)
        TextView textView;

        public FirstViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
