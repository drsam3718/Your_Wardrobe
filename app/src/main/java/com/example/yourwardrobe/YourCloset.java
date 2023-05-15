package com.example.yourwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;

import com.example.yourwardrobe.data.DbHandler;
import com.example.yourwardrobe.model.Cloths;

import java.util.ArrayList;
import java.util.List;

public class YourCloset extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_closet);

        DbHandler db = new DbHandler(YourCloset.this);

        Button add_cloth_button = (Button) findViewById(R.id.add_cloth_button);
        GridView mainClothsGrid = (GridView) findViewById(R.id.main_cloths_grid);
        add_cloth_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_cloth_intent = new Intent(YourCloset.super.getBaseContext(), AddNewCloth.class);
                startActivity(add_cloth_intent);
            }
        });

        ArrayList<Cloths> clothsArrayList = new ArrayList<>();

        List<Cloths> clothsList = db.getAllContact();
        for(Cloths cloths: clothsList){
            Log.d("dbtest", "ID : " + cloths.getId() + "\nSubcategory : " +cloths.getSubcategory() + "\nNumber : " + cloths.getPath()+ "\n");
            clothsArrayList.add(cloths);
        }

        ClothsAdapter clothsAdapter = new ClothsAdapter(this, clothsArrayList);
        mainClothsGrid.setAdapter(clothsAdapter);

    }
}