package com.example.yourwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.example.yourwardrobe.data.DbHandler;
import com.example.yourwardrobe.model.Cloths;

import java.util.ArrayList;
import java.util.List;

public class SelectShoesForOutfit extends AppCompatActivity {


    GridView shoesGrid;
    public static final String SHOES_ID = "com.example.yourwardrobe.SHOES_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_shoes_for_outfit);
        DbHandler db = new DbHandler(this);
        shoesGrid = findViewById(R.id.shoes_grid);

        ArrayList<Cloths> clothsArrayListByCategory = new ArrayList<>();
        List<Cloths> clothsList = db.getByCategory("Tops");
        for(Cloths cloths: clothsList){
            clothsArrayListByCategory.add(cloths);
        }
        ClothsAdapter clothsAdapterByCategory = new ClothsAdapter(SelectShoesForOutfit.this, clothsArrayListByCategory);
        shoesGrid.setAdapter(clothsAdapterByCategory);


        Intent intent = new Intent(this, CreateOutfit.class);

        shoesGrid.setOnItemClickListener((parent, view, position, id) -> intent.putExtra(SHOES_ID, ((TextView)findViewById(R.id.cloth_id)).getText().toString()));
        startActivity(intent);

    }
}