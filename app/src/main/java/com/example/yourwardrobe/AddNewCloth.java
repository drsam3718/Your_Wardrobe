package com.example.yourwardrobe;

import static java.util.Arrays.asList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yourwardrobe.data.DbHandler;
import com.example.yourwardrobe.model.Cloths;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AddNewCloth extends AppCompatActivity {

    ImageView image_view;
    Uri img_uri;
    MaterialButton pickImageButton, addClothButton;
    Spinner category, subcategory;
//    AutoCompleteTextView autocategory, autosubcategory;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_cloth);

        image_view = (ImageView)findViewById(R.id.image_view);
        pickImageButton = (MaterialButton) findViewById(R.id.pick_image_button);
        addClothButton = (MaterialButton) findViewById(R.id.add_cloth_button);

        Intent your_closet_intent = new Intent(AddNewCloth.this.getBaseContext(), YourCloset.class);
//        autocategory = (AutoCompleteTextView) findViewById(R.id.auto_category);
//        autosubcategory = (AutoCompleteTextView) findViewById(R.id.auto_subcategory);

        category = (Spinner) findViewById(R.id.category);
        subcategory = (Spinner) findViewById(R.id.subcategory);

        DbHandler db = new DbHandler(AddNewCloth.this);


        ArrayList<String> categories = new ArrayList<>(asList(getResources().getStringArray(R.array.categories_array)));
        ArrayAdapter<String> adapter1= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        category.setAdapter(adapter1);

        ArrayList<String> tops_sub = new ArrayList<>(asList(getResources().getStringArray(R.array.tops_array)));
        ArrayAdapter<String> topadapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tops_sub);
        ArrayList<String> bottoms_sub = new ArrayList<>(asList(getResources().getStringArray(R.array.bottoms_array)));
        ArrayAdapter<String> bottomsadapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bottoms_sub);
        ArrayList<String> jackets_sub = new ArrayList<>(asList( getResources().getStringArray(R.array.jackets_array)));
        ArrayAdapter<String> jacketsadapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, jackets_sub);
        ArrayList<String> shoes_sub = new ArrayList<>(asList(getResources().getStringArray(R.array.shoes_array)));
        ArrayAdapter<String> shoesadapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shoes_sub);
        ArrayList<String> accessories_sub = new ArrayList<>(asList(getResources().getStringArray(R.array.accessories_array)));
        ArrayAdapter<String> accessoriesadapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, accessories_sub);
        pickImageButton.setOnClickListener(v -> ImagePicker.with(AddNewCloth.this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1350)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start());

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddNewCloth.this, "you have selected "+category.getSelectedItem().toString() , Toast.LENGTH_SHORT).show();
                if (category.getSelectedItem().toString().equals("Select Category")) {
                    Toast.makeText(AddNewCloth.this, "Please choose category first", Toast.LENGTH_SHORT).show();
                }else if (category.getSelectedItem().toString().equals("Tops")) {
                    subcategory.setAdapter(topadapter);
                } else if (category.getSelectedItem().toString().equals("Bottoms")) {
                    subcategory.setAdapter(bottomsadapter);
                } else if (category.getSelectedItem().toString().equals("Jackets")) {
                    subcategory.setAdapter(jacketsadapter);
                } else if (category.getSelectedItem().toString().equals("Shoes")) {
                    subcategory.setAdapter(shoesadapter);
                } else if (category.getSelectedItem().toString().equals("Accessories")) {
                    subcategory.setAdapter(accessoriesadapter);
                } else {
                    throw new IllegalStateException("Unexpected value: " + categories);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AddNewCloth.this, "Please choose category first", Toast.LENGTH_SHORT).show();
            }
        });

        addClothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category.getSelectedItem().toString().equals("") || subcategory.getSelectedItem().toString().equals("Shoes") || img_uri.toString().equals("")){
                }else{
                    Cloths cloth = new Cloths(category.getSelectedItem().toString(), subcategory.getSelectedItem().toString(), img_uri.toString());
                    db.addCloths(cloth);
                    startActivity(your_closet_intent);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        img_uri = data.getData();
        image_view.setImageURI(img_uri);
    }
}