package com.example.yourwardrobe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yourwardrobe.data.DbHandler;
import com.example.yourwardrobe.model.Outfit;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;

public class CreateOutfit extends AppCompatActivity {

    ImageView cover_image_view, top_image_view, bottom_image_view, jacket_image_view, shoes_image_view, accessories_image_view;
    Button pick_cover_image_button, select_top_button, select_bottom_button, select_jacket_button, select_shoes_button, select_accessories_button, create_outfit_button;
    TextInputEditText outfit_name;

    Uri img_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_outfit);

        DbHandler db = new DbHandler(CreateOutfit.this.getBaseContext());

        outfit_name = findViewById(R.id.outfit_name);

        cover_image_view = (ImageView) findViewById(R.id.outfit_cover);
        top_image_view = (ImageView) findViewById(R.id.top_image_view);
        bottom_image_view = (ImageView) findViewById(R.id.bottom_image_view);
        jacket_image_view = (ImageView) findViewById(R.id.jacket_image_view);
        shoes_image_view = (ImageView) findViewById(R.id.shoes_image_view);
        accessories_image_view = (ImageView) findViewById(R.id.accessory_image_view);

        pick_cover_image_button = findViewById(R.id.pick_cover_button);
        select_top_button = findViewById(R.id.select_top_button);
        select_bottom_button = findViewById(R.id.select_bottom_button);
        select_jacket_button = findViewById(R.id.select_jacket_button);
        select_shoes_button = findViewById(R.id.select_shoes_button);
        select_accessories_button = findViewById(R.id.select_accessories_button);
        create_outfit_button = findViewById(R.id.create_outfit_button);

        Intent your_outfits_intent = new Intent(CreateOutfit.this.getBaseContext(), YourOutfits.class);

        Intent select_top_intent = new Intent(CreateOutfit.this.getBaseContext(), SelectTopForOutfit.class);

        Intent select_bottom_intent = new Intent(CreateOutfit.this.getBaseContext(), SelectBottomForOutfit.class);

        Intent select_jacket_intent = new Intent(CreateOutfit.this.getBaseContext(), SelectJacketForOutfit.class);

        Intent select_shoes_intent = new Intent(CreateOutfit.this.getBaseContext(), SelectShoesForOutfit.class);

        Intent select_accessories_intent = new Intent(CreateOutfit.this.getBaseContext(), SelectAccessoriesForOutfit.class);

        Intent intent = getIntent();

        pick_cover_image_button.setOnClickListener(v -> ImagePicker.with(CreateOutfit.this).crop().start());

        select_top_button.setOnClickListener(v -> startActivity(select_top_intent));
        select_bottom_button.setOnClickListener(v -> startActivity(select_bottom_intent));
        select_jacket_button.setOnClickListener(v -> startActivity(select_jacket_intent));
        select_shoes_button.setOnClickListener(v -> startActivity(select_shoes_intent));
        select_accessories_button.setOnClickListener(v -> startActivity(select_accessories_intent));


        create_outfit_button.setOnClickListener(v -> {
            if (outfit_name.getText().toString().equals("")){
                Toast.makeText(CreateOutfit.this, "Please enter the name of this outfit", Toast.LENGTH_SHORT).show();
            }else{
                Outfit outfit = new Outfit();
                outfit.setOutfitName(outfit_name.getText().toString());
                outfit.setTopId(Integer.parseInt(intent.getStringExtra(SelectTopForOutfit.TOPS_ID)));
                outfit.setBottomId(Integer.parseInt(intent.getStringExtra(SelectBottomForOutfit.BOTTOM_ID)));
                outfit.setJacketId(Integer.parseInt(intent.getStringExtra(SelectJacketForOutfit.JACKET_ID)));
                outfit.setShoesId(Integer.parseInt(intent.getStringExtra(SelectShoesForOutfit.SHOES_ID)));
                outfit.setAccessoriesId(Integer.parseInt(intent.getStringExtra(SelectAccessoriesForOutfit.ACCESSORIES_ID)));
                outfit.setOutfitImagePath(img_uri.toString());
                db.addOutfit(outfit);
                startActivity(your_outfits_intent);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        img_uri = data.getData();
        cover_image_view.setImageURI(img_uri);

    }
}