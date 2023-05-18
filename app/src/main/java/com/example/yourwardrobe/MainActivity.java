package com.example.yourwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent your_closet_intent = new Intent(MainActivity.super.getBaseContext(), YourCloset.class);
        Intent outfit_maker_intent = new Intent(MainActivity.super.getBaseContext(), YourOutfits.class);
        Intent brief_case_intent = new Intent(MainActivity.super.getBaseContext(), BriefCase.class);
        Intent laundry_basket_intent = new Intent(MainActivity.super.getBaseContext(), LaundryBasket.class);
        Intent market_place_intent = new Intent(MainActivity.super.getBaseContext(), MarketPlace.class);
        Intent calender_intent = new Intent(MainActivity.super.getBaseContext(), Calender.class);

        setListeners(R.id.closet_button, your_closet_intent);
        setListeners(R.id.laundry_basket_button, laundry_basket_intent);
        setListeners(R.id.brief_case_button, brief_case_intent);
        setListeners(R.id.outfit_maker_button, outfit_maker_intent);
        setListeners(R.id.calendar_button, calender_intent);
        setListeners(R.id.market_place_button, market_place_intent);
    }
    void setListeners(int id, Intent intent){
        ImageButton img_btn = (ImageButton) findViewById(id);
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

}