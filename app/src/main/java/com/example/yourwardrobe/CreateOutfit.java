package com.example.yourwardrobe;

import static com.example.yourwardrobe.SelectTopForOutfit.TOP_ID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yourwardrobe.data.DbHandler;
import com.example.yourwardrobe.model.Cloths;
import com.example.yourwardrobe.model.Outfit;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class CreateOutfit extends AppCompatActivity {

    MaterialButton pick_cover_button, select_top_button, select_bottom_button, select_jacket_button, select_shoes_button, select_accessories_button, create_outfit_button;
    ImageView outfit_cover, top_image_view, bottom_image_view, jacket_image_view, shoes_image_view, accessories_image_view;
    Uri cover_uri;
    TextInputEditText outfit_name;
    DbHandler db = new DbHandler(CreateOutfit.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_outfit);

        Intent intent = getIntent();

        outfit_cover = findViewById(R.id.outfit_cover);
        top_image_view = findViewById(R.id.top_image_view);
        bottom_image_view = findViewById(R.id.bottom_image_view);
        jacket_image_view = findViewById(R.id.jacket_image_view);
        shoes_image_view = findViewById(R.id.shoes_image_view);
        accessories_image_view = findViewById(R.id.accessory_image_view);

        Intent select_top_intent = new Intent(CreateOutfit.this.getBaseContext(), SelectTopForOutfit.class);
        Intent select_bottom_intent = new Intent(CreateOutfit.this.getBaseContext(), SelectBottomForOutfit.class);
        Intent select_jacket_intent = new Intent(CreateOutfit.this.getBaseContext(), SelectJacketForOutfit.class);
        Intent select_shoes_intent = new Intent(CreateOutfit.this.getBaseContext(), SelectShoesForOutfit.class);
        Intent select_accessories_intent = new Intent(CreateOutfit.this.getBaseContext(), SelectAccessoriesForOutfit.class);

        outfit_name = findViewById(R.id.outfit_name);
        pick_cover_button = findViewById(R.id.pick_cover_button);
        pick_cover_button.setOnClickListener(v -> ImagePicker.with(CreateOutfit.this)
                .crop()
                .start());

        select_top_button = findViewById(R.id.select_top_button);
        select_bottom_button = findViewById(R.id.select_bottom_button);
        select_jacket_button = findViewById(R.id.select_jacket_button);
        select_shoes_button = findViewById(R.id.select_shoes_button);
        select_accessories_button = findViewById(R.id.select_accessories_button);
        select_top_button.setOnClickListener(v -> startActivityForResult(select_top_intent, 25));
        select_bottom_button.setOnClickListener(v -> startActivityForResult(select_bottom_intent, 26));
        select_jacket_button.setOnClickListener(v -> startActivityForResult(select_jacket_intent, 27));
        select_shoes_button.setOnClickListener(v -> startActivityForResult(select_shoes_intent, 28));
        select_accessories_button.setOnClickListener(v -> startActivityForResult(select_accessories_intent, 29));

        create_outfit_button = findViewById(R.id.create_outfit_button);

        create_outfit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(outfit_name.getText().toString().equals("")){
                    Toast.makeText(CreateOutfit.this, "Add name for your outfit atleast", Toast.LENGTH_SHORT).show();
                }else{
                    Outfit outfit = new Outfit();
                    outfit.setOutfitName(outfit_name.getText().toString());
                    if (cover_uri != null){
                        outfit.setOutfitImagePath(cover_uri.getPath());
                    }else{
                        outfit.setOutfitImagePath(null);
                    }
                    outfit.setTopId(intent.getIntExtra(SelectTopForOutfit.TOP_ID, 0));
                    outfit.setBottomId(intent.getIntExtra(SelectBottomForOutfit.BOTTOM_ID, 0));
                    outfit.setJacketId(intent.getIntExtra(SelectJacketForOutfit.JACKET_ID, 0));
                    outfit.setShoesId(intent.getIntExtra(SelectShoesForOutfit.SHOES_ID, 0));
                    outfit.setAccessoriesId(intent.getIntExtra(SelectAccessoriesForOutfit.ACCESSORIES_ID, 0));
                    db.addOutfit(outfit);
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Cloths cloth = new Cloths();
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case 25:
                    int top_id = data.getIntExtra(SelectTopForOutfit.TOP_ID, 0);
                    cloth = db.getByID(top_id).get(0);
                    top_image_view.setImageURI(Uri.parse(cloth.getPath()));
                    break;
                case 26:
                    int bottom_id = data.getIntExtra(SelectBottomForOutfit.BOTTOM_ID, 0);
                    cloth = db.getByID(bottom_id).get(0);
                    bottom_image_view.setImageURI(Uri.parse(cloth.getPath()));
                    break;
                case 27:
                    int jacket_id = data.getIntExtra(SelectJacketForOutfit.JACKET_ID, 0);
                    cloth = db.getByID(jacket_id).get(0);
                    jacket_image_view.setImageURI(Uri.parse(cloth.getPath()));
                    break;
                case 28:
                    int shoes_id = data.getIntExtra(SelectShoesForOutfit.SHOES_ID, 0);
                    cloth = db.getByID(shoes_id).get(0);
                    shoes_image_view.setImageURI(Uri.parse(cloth.getPath()));
                    break;
                case 29:
                    int accessories_id = data.getIntExtra(SelectAccessoriesForOutfit.ACCESSORIES_ID, 0);
                    cloth = db.getByID(accessories_id).get(0);
                    accessories_image_view.setImageURI(Uri.parse(cloth.getPath()));
                    break;
                default:
                    cover_uri = data.getData();
                    outfit_cover.setImageURI(cover_uri);
                    break;
            }
        }else{
            Toast.makeText(this, "Something went wrong!!!", Toast.LENGTH_SHORT).show();
        }
    }
}