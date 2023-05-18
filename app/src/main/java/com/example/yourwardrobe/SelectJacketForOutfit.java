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

public class SelectJacketForOutfit extends AppCompatActivity {

    GridView jacketGrid;
    public static final String JACKET_ID = "com.example.yourwardrobe.JACKET_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_jacket_for_outfit);

        DbHandler db = new DbHandler(this);
        jacketGrid = findViewById(R.id.jacket_grid);

        ArrayList<Cloths> clothsArrayListByCategory = new ArrayList<>();
        List<Cloths> clothsList = db.getByCategory("Jacket");
        for(Cloths cloths: clothsList){
            clothsArrayListByCategory.add(cloths);
        }
        ClothsAdapter clothsAdapterByCategory = new ClothsAdapter(SelectJacketForOutfit.this, clothsArrayListByCategory);
        jacketGrid.setAdapter(clothsAdapterByCategory);


        Intent intent = new Intent(this, CreateOutfit.class);

        jacketGrid.setOnItemClickListener((parent, view, position, id) -> intent.putExtra(JACKET_ID, ((TextView)findViewById(R.id.cloth_id)).getText().toString()));
        startActivity(intent);
    }
}