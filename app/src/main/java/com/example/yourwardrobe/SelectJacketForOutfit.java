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

public class SelectJacketForOutfit extends AppCompatActivity {

    public static final String JACKET_ID = "com.example.yourwardrobe.JACKET_ID";
    GridView select_jacket_grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_jacket_for_outfir);


        DbHandler db = new DbHandler(this);
        Intent intent = new Intent(SelectJacketForOutfit.this.getBaseContext(), CreateOutfit.class);

        select_jacket_grid = findViewById(R.id.select_jacket_grid);
        ArrayList<Cloths> clothsArrayList = new ArrayList<>();

        List<Cloths> clothsList = db.getByCategory("Jackets");
        for(Cloths cloths: clothsList){
            clothsArrayList.add(cloths);
        }

        ClothsAdapter clothsAdapter = new ClothsAdapter(this, clothsArrayList);
        select_jacket_grid.setAdapter(clothsAdapter);

        select_jacket_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cloths cloth = (Cloths) select_jacket_grid.getItemAtPosition(position);
                intent.putExtra(JACKET_ID, cloth.getId());

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}