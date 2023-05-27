package com.malw.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class GAdapter extends RecyclerView.Adapter<GAdapter.ViewHolder> {
    private final ArrayList<Image> images;
    private final Context context;

    public GAdapter(Context context, ArrayList<Image> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image, parent, false));
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        setImageFromPath(images.get(position).getPath(), viewHolder.img);
        viewHolder.img.setOnClickListener(v -> {
            context.startActivity(new Intent(context, FullscreenActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("image_index", position).putParcelableArrayListExtra("images", images));
        });

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.img);
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    private void setImageFromPath(String path, ImageView image) {
        File imgFile = new File(path);
        if (imgFile.exists()) {
            Bitmap myBitmap = ImageHelper.decodeSampleBitmapFromPath(imgFile.getAbsolutePath(), 200, 200);
            image.setImageBitmap(myBitmap);
        }
    }

}