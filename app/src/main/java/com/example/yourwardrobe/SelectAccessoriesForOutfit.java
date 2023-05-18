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

public class SelectAccessoriesForOutfit extends AppCompatActivity {

    GridView accessoriesGrid;
    public static final String ACCESSORIES_ID = "com.example.yourwardrobe.ACCESSORIES_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_accessories_for_outfit);

        DbHandler db = new DbHandler(this);
        accessoriesGrid = findViewById(R.id.accessories_grid);

        ArrayList<Cloths> clothsArrayListByCategory = new ArrayList<>();
        List<Cloths> clothsList = db.getByCategory("Tops");
        for(Cloths cloths: clothsList){
            clothsArrayListByCategory.add(cloths);
        }
        ClothsAdapter clothsAdapterByCategory = new ClothsAdapter(SelectAccessoriesForOutfit.this, clothsArrayListByCategory);
        accessoriesGrid.setAdapter(clothsAdapterByCategory);


        Intent intent = new Intent(this, CreateOutfit.class);

        accessoriesGrid.setOnItemClickListener((parent, view, position, id) -> intent.putExtra(ACCESSORIES_ID, ((TextView)findViewById(R.id.cloth_id)).getText().toString()));
        startActivity(intent);
    }
}