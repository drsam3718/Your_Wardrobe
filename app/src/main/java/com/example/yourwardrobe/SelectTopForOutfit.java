package com.example.yourwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.yourwardrobe.data.DbHandler;
import com.example.yourwardrobe.model.Cloths;

import java.util.ArrayList;
import java.util.List;

public class SelectTopForOutfit extends AppCompatActivity {

    public static final String TOPS_ID = "com.example.yourwardrobe.TOPS_ID";
    GridView topsGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_top_for_outfit);

        DbHandler db = new DbHandler(this);
        topsGrid = findViewById(R.id.tops_grid);

        ArrayList<Cloths> clothsArrayListByCategory = new ArrayList<>();
        List<Cloths> clothsList = db.getByCategory("Tops");
        for(Cloths cloths: clothsList){
            clothsArrayListByCategory.add(cloths);
        }
        ClothsAdapter clothsAdapterByCategory = new ClothsAdapter(SelectTopForOutfit.this, clothsArrayListByCategory);
        topsGrid.setAdapter(clothsAdapterByCategory);

        Intent intent = new Intent(this, CreateOutfit.class);

        topsGrid.setOnItemClickListener((parent, view, position, id) -> intent.putExtra(TOPS_ID, ((TextView)findViewById(R.id.cloth_id)).getText().toString()));
        startActivity(intent);
    }
}