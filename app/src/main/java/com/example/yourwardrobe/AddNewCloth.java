package com.example.yourwardrobe;

import static java.util.Arrays.asList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AddNewCloth extends AppCompatActivity {

    ImageView image_view;
    MaterialButton pickImageButton;

    TextInputLayout category, subcategory;
    AutoCompleteTextView autocategory, autosubcategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_cloth);

        image_view = (ImageView)findViewById(R.id.image_view);
        pickImageButton = (MaterialButton) findViewById(R.id.pick_image_button);

        autocategory = (AutoCompleteTextView) findViewById(R.id.auto_category);
        autosubcategory = (AutoCompleteTextView) findViewById(R.id.auto_subcategory);

        category = (TextInputLayout) findViewById(R.id.category);
        subcategory = (TextInputLayout) findViewById(R.id.subcategory);

        ArrayList<String> categories = new ArrayList<>(asList("Tops", "Bottoms", "Jackets", "Shoes", "Accessories"));
        ArrayAdapter<String> adapter1= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        autocategory.setAdapter(adapter1);

        ArrayList<String> tops_sub = new ArrayList<>(asList("T-shirts", "Blouses", "Shirts", "Kurta", "Other"));
        ArrayList<String> bottoms_sub = new ArrayList<>(asList("T-shirts", "Blouses", "Shirts", "Kurta", "Other"));
        ArrayList<String> jackets_sub = new ArrayList<>(asList("T-shirts", "Blouses", "Shirts", "Kurta", "Other"));
        ArrayList<String> shoes_sub = new ArrayList<>(asList("T-shirts", "Blouses", "Shirts", "Kurta", "Other"));
        ArrayList<String> accessories_sub = new ArrayList<>(asList("T-shirts", "Blouses", "Shirts", "Kurta", "Other"));

        pickImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(AddNewCloth.this)
//                        .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        final ArrayAdapter<String>[] adapter2 = new ArrayAdapter<String>[1];
        category.addOnEditTextAttachedListener(new TextInputLayout.OnEditTextAttachedListener() {
            @Override
            public void onEditTextAttached(@NonNull TextInputLayout textInputLayout) {
                switch (categories){
                    case "Tops" :
                        adapter2[0] = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tops_sub);
                        autocategory.setAdapter(adapter2[0]);
                        break;
                    case "Bottoms" :
                        adapter2[0] = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
                        autocategory.setAdapter(adapter2[0]);
                        break;
                    case "Jackets" :
                        adapter2[0] = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
                        autocategory.setAdapter(adapter2[0]);
                        break;
                    case "Shoes" :
                        adapter2[0] = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
                        autocategory.setAdapter(adapter2[0]);
                        break;
                    case "Accessories" :
                        adapter2[0] = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
                        autocategory.setAdapter(adapter2[0]);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + categories);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        image_view.setImageURI(uri);
    }
}