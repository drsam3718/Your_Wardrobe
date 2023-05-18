package com.example.yourwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.yourwardrobe.data.DbHandler;
import com.example.yourwardrobe.model.Cloths;

import java.util.ArrayList;
import java.util.List;

public class SelectShoesForOutfit extends AppCompatActivity {

    public static final String SHOES_ID = "com.example.yourwardrobe.TOP_ID";
    GridView select_shoes_grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_shoes_for_outfit);

        DbHandler db = new DbHandler(this);
        Intent intent = new Intent(SelectShoesForOutfit.this.getBaseContext(), CreateOutfit.class);

        select_shoes_grid = findViewById(R.id.select_shoes_grid);
        ArrayList<Cloths> clothsArrayList = new ArrayList<>();

        List<Cloths> clothsList = db.getByCategory("Shoes");
        for(Cloths cloths: clothsList){
            clothsArrayList.add(cloths);
        }

        ClothsAdapter clothsAdapter = new ClothsAdapter(this, clothsArrayList);
        select_shoes_grid.setAdapter(clothsAdapter);

        select_shoes_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cloths cloth = (Cloths) select_shoes_grid.getItemAtPosition(position);
                intent.putExtra(SHOES_ID, cloth.getId());

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}