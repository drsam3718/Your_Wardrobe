package com.example.yourwardrobe;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.yourwardrobe.data.DbHandler;
import com.example.yourwardrobe.model.Cloths;
import com.example.yourwardrobe.model.Outfit;

import java.util.ArrayList;

public class OutfitAdapter extends ArrayAdapter<Outfit> {
    public OutfitAdapter(@NonNull Context context, ArrayList<Outfit> outfit_list) {
        super(context, 0, outfit_list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.outfit_card_item, parent, false);
        }

        Outfit outfit = getItem(position);
        DbHandler db = new DbHandler(parent.getContext());

        ImageView outfit_cover = listItemView.findViewById(R.id.outfit_cover);
        ImageView top_image = listItemView.findViewById(R.id.top_image);
        ImageView bottom_image = listItemView.findViewById(R.id.bottom_image);
        ImageView jacket_image = listItemView.findViewById(R.id.jacket_image);
        ImageView shoes_image = listItemView.findViewById(R.id.shoes_image);
        ImageView accessory_image = listItemView.findViewById(R.id.accessory_image);

        if(outfit.getOutfitImagePath() != null)
        {
            outfit_cover.setImageURI(Uri.parse(outfit.getOutfitImagePath()));
        }
        if(outfit.getTopId() != 0){
            top_image.setImageURI(Uri.parse(db.getByID(outfit.getTopId()).toString()));
        }
        if(outfit.getBottomId() != 0){
            bottom_image.setImageURI(Uri.parse(db.getByID(outfit.getBottomId()).toString()));
        }
        if(outfit.getJacketId() != 0){
            jacket_image.setImageURI(Uri.parse(db.getByID(outfit.getJacketId()).toString()));
        }
        if(outfit.getShoesId() != 0){
            shoes_image.setImageURI(Uri.parse(db.getByID(outfit.getShoesId()).toString()));
        }
        if(outfit.getAccessoriesId() != 0){
            accessory_image.setImageURI(Uri.parse(db.getByID(outfit.getAccessoriesId()).toString()));
        }

        return listItemView;
    }
}
