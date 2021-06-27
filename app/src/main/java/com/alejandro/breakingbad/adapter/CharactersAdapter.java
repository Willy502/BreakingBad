package com.alejandro.breakingbad.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.alejandro.breakingbad.R;
import com.alejandro.breakingbad.activity.CharacterDetailActivity;
import com.alejandro.breakingbad.model.BBCharacter;
import com.bumptech.glide.Glide;

import java.util.List;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> {

    List<BBCharacter> BBCharacters;
    Activity activity;

    public CharactersAdapter(List<BBCharacter> BBCharacters, Activity activity) {
        this.BBCharacters = BBCharacters;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BBCharacter character = BBCharacters.get(position);
        holder.txtName.setText(character.getName());
        holder.txtNickname.setText(character.getNickname());
        Glide.with(activity).load(character.getImg()).into(holder.imgPhoto);
        holder.container.setOnClickListener(view -> {
            Intent intent = new Intent(activity, CharacterDetailActivity.class);
            intent.putExtra("character", character);
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return BBCharacters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName, txtNickname;
        public ImageView imgPhoto, imgFavorite;
        public ConstraintLayout container;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_name);
            txtNickname = itemView.findViewById(R.id.txt_nickname);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            imgFavorite = itemView.findViewById(R.id.img_favorite);
            container = itemView.findViewById(R.id.item_container);
        }
    }
}
