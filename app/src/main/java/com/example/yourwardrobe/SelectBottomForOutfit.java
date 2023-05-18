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

public class SelectBottomForOutfit extends AppCompatActivity {

    GridView bottomGrid;
    public static final String BOTTOM_ID = "com.example.yourwardrobe.BOTTOM_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bottom_for_outfit);

        DbHandler db = new DbHandler(this);
        bottomGrid = findViewById(R.id.bottom_grid);

        ArrayList<Cloths> clothsArrayListByCategory = new ArrayList<>();
        List<Cloths> clothsList = db.getByCategory("Tops");
        for(Cloths cloths: clothsList){
            clothsArrayListByCategory.add(cloths);
        }
        ClothsAdapter clothsAdapterByCategory = new ClothsAdapter(SelectBottomForOutfit.this, clothsArrayListByCategory);
        bottomGrid.setAdapter(clothsAdapterByCategory);


        Intent intent = new Intent(this, CreateOutfit.class);

        bottomGrid.setOnItemClickListener((parent, view, position, id) -> intent.putExtra(BOTTOM_ID, ((TextView)findViewById(R.id.cloth_id)).getText().toString()));
        startActivity(intent);
    }
}