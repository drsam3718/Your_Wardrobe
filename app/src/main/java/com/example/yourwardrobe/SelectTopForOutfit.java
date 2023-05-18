package com.example.yourwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.yourwardrobe.data.DbHandler;
import com.example.yourwardrobe.model.Cloths;

import java.util.ArrayList;
import java.util.List;

public class SelectTopForOutfit extends AppCompatActivity {

    public static final String TOP_ID = "com.example.yourwardrobe.TOP_ID";
    GridView select_top_grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_top_for_outfit);

        DbHandler db = new DbHandler(this);
        Intent intent = new Intent(SelectTopForOutfit.this.getBaseContext(), CreateOutfit.class);

        select_top_grid = findViewById(R.id.select_top_grid);
        ArrayList<Cloths> clothsArrayList = new ArrayList<>();

        List<Cloths> clothsList = db.getByCategory("Tops");
        for(Cloths cloths: clothsList){
            clothsArrayList.add(cloths);
        }

        ClothsAdapter clothsAdapter = new ClothsAdapter(this, clothsArrayList);
        select_top_grid.setAdapter(clothsAdapter);

        select_top_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cloths cloth = (Cloths) select_top_grid.getItemAtPosition(position);
                intent.putExtra(TOP_ID, cloth.getId());

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}