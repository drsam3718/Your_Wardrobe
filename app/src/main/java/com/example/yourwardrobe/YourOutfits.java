package com.example.yourwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.yourwardrobe.data.DbHandler;
import com.example.yourwardrobe.model.Cloths;
import com.example.yourwardrobe.model.Outfit;

import java.util.ArrayList;
import java.util.List;

public class YourOutfits extends AppCompatActivity {

    Button create_outfit_button;
    GridView your_outfits_grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_outfits);
        create_outfit_button = findViewById(R.id.create_outfit_button);
        your_outfits_grid = findViewById(R.id.your_outfits_grid);

        DbHandler db = new DbHandler(this);

        ArrayList<Outfit> outfitArrayList = new ArrayList<>();

        List<Outfit> outfitList = db.getAllOutfits();
        for(Outfit outfit: outfitList){
//            Log.d("dbtest", "ID : " + cloths.getId() + "\nSubcategory : " +cloths.getSubcategory() + "\nNumber : " + cloths.getPath()+ "\n");
            outfitArrayList.add(outfit);
        }

        OutfitAdapter outfitAdapter = new OutfitAdapter(this, outfitArrayList);
        your_outfits_grid.setAdapter(outfitAdapter);

        Intent intent = new Intent(YourOutfits.this, CreateOutfit.class);
        create_outfit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}