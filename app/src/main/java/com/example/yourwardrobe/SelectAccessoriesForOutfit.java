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

public class SelectAccessoriesForOutfit extends AppCompatActivity {

    public static final String ACCESSORIES_ID = "com.example.yourwardrobe.ACCESSORIES_ID";
    GridView select_accessories_grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_accessories_for_outfit);


        DbHandler db = new DbHandler(this);
        Intent intent = new Intent(SelectAccessoriesForOutfit.this.getBaseContext(), CreateOutfit.class);

        select_accessories_grid = findViewById(R.id.select_accessories_grid);
        ArrayList<Cloths> clothsArrayList = new ArrayList<>();

        List<Cloths> clothsList = db.getByCategory("Accessories");
        for(Cloths cloths: clothsList){
            clothsArrayList.add(cloths);
        }

        ClothsAdapter clothsAdapter = new ClothsAdapter(this, clothsArrayList);
        select_accessories_grid.setAdapter(clothsAdapter);

        select_accessories_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cloths cloth = (Cloths) select_accessories_grid.getItemAtPosition(position);
                intent.putExtra(ACCESSORIES_ID, cloth.getId());

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}