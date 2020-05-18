package com.example.dogfinder.presentation.BreedList.List;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dogfinder.R;

import java.util.List;

import data.model.Breed;

public class BreedListAdapter extends RecyclerView.Adapter<BreedListAdapter.ViewHolder> {

    private List<Breed> items;
    private OnBreedClickListener listener;

    public BreedListAdapter(OnBreedClickListener listener) {
        this.listener = listener;
    }

    public void setItems(List<Breed> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_breed, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Breed breed = items.get(position);
        holder.tvBreed.setText(breed.getBreedName());

        Glide.with(holder.itemView.getContext())
                .load(Uri.parse(breed.getImageUrl()))
                .into(holder.ivPicture);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onBreedClick(breed);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(items == null) {
            return 0;
        }
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPicture;
        TextView tvBreed;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            tvBreed = itemView.findViewById(R.id.tvBreed);
        }
    }

}
