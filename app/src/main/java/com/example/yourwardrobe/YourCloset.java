package com.example.yourwardrobe;

import static java.util.Arrays.asList;
import static java.util.Arrays.sort;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Spinner;

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

        Spinner sort_by_spinner = (Spinner) findViewById(R.id.sort_by_spinner);
        ArrayList<String> categories = new ArrayList<>(asList(getResources().getStringArray(R.array.categories_array_for_sorting)));
        ArrayAdapter<String> adapter1= new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, categories);
        sort_by_spinner.setAdapter(adapter1);


        Button add_cloth_button = (Button) findViewById(R.id.add_cloth_button);
        GridView mainClothsGrid = (GridView) findViewById(R.id.main_cloths_grid);

        ArrayList<Cloths> clothsArrayList = new ArrayList<>();

        List<Cloths> clothsList = db.getAllCloths();
        for(Cloths cloths: clothsList){
            Log.d("dbtest", "ID : " + cloths.getId() + "\nSubcategory : " +cloths.getSubcategory() + "\nNumber : " + cloths.getPath()+ "\n");
            clothsArrayList.add(cloths);
        }

        ClothsAdapter clothsAdapter = new ClothsAdapter(this, clothsArrayList);
        mainClothsGrid.setAdapter(clothsAdapter);
        add_cloth_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_cloth_intent = new Intent(YourCloset.super.getBaseContext(), AddNewCloth.class);
                startActivity(add_cloth_intent);
            }
        });
                ArrayList<Cloths> clothsArrayListByCategory = new ArrayList<>();

        sort_by_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (sort_by_spinner.getSelectedItem().toString().equals("All Category")){
                    mainClothsGrid.setAdapter(clothsAdapter);
                }else{
                    clothsArrayListByCategory.clear();
                    for (Cloths cloths : clothsList) {
                        if (cloths.getCategory().equals(sort_by_spinner.getSelectedItem().toString())){
                            clothsArrayListByCategory.add(cloths);
                        }
                    }
                    ClothsAdapter clothsAdapterByCategory = new ClothsAdapter(YourCloset.this, clothsArrayListByCategory);
                    mainClothsGrid.setAdapter(clothsAdapterByCategory);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
