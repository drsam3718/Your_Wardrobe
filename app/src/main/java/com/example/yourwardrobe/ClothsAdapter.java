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

import com.example.yourwardrobe.model.Cloths;

import java.util.ArrayList;

public class ClothsAdapter extends ArrayAdapter<Cloths> {

    public ClothsAdapter(@NonNull Context context, ArrayList<Cloths> cloths_list) {
        super(context, 0, cloths_list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.cloth_card_item, parent, false);
        }

        Cloths cloth = getItem(position);
        TextView courseTV = listItemView.findViewById(R.id.card_text);
        ImageView courseIV = listItemView.findViewById(R.id.card_image);

        courseTV.setText(cloth.getSubcategory());
        courseIV.setImageURI(Uri.parse(cloth.getPath()));
        return listItemView;
    }
}