package com.alejandro.breakingbad.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.alejandro.breakingbad.R;
import com.alejandro.breakingbad.model.BBCharacter;
import com.bumptech.glide.Glide;

public class CharacterDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView txtNickname, txtOccupation, txtStatus, txtPortrayed;
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        connectView();

        BBCharacter character = (BBCharacter) getIntent().getSerializableExtra("character");

        toolbar.setTitle(character.getName());
        setActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back, null));
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Setting data
        txtNickname.setText(character.getNickname());
        String fullOccupation = "";
        for (String occupation : character.getOccupation()) {
            fullOccupation += occupation + ", ";
        }
        fullOccupation = fullOccupation.substring(0, fullOccupation.length() - 2);
        txtOccupation.setText(fullOccupation);
        txtStatus.setText(character.getStatus());
        txtPortrayed.setText(character.getPortrayed());
        Glide.with(this).load(character.getImg()).into(imgPhoto);
    }

    private void connectView() {
        toolbar = findViewById(R.id.toolbar);
        txtNickname = findViewById(R.id.txt_name);
        imgPhoto = findViewById(R.id.img_photo);
        txtOccupation = findViewById(R.id.txt_occupation);
        txtStatus = findViewById(R.id.txt_status);
        txtPortrayed = findViewById(R.id.txt_portrayed);
    }
}