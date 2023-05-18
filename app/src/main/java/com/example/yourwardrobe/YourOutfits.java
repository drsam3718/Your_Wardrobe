package com.example.yourwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class YourOutfits extends AppCompatActivity {

    Button create_outfit_button;
    GridView your_outfits_grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_outfits);
        create_outfit_button = findViewById(R.id.create_outfit_button);
        your_outfits_grid = findViewById(R.id.your_outfits_grid);

        Intent intent = new Intent(YourOutfits.this, CreateOutfit.class);
        create_outfit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}