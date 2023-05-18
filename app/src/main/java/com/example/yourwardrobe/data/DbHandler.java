package com.example.yourwardrobe.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.yourwardrobe.model.Cloths;
import com.example.yourwardrobe.model.Outfit;
import com.example.yourwardrobe.param.Params;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    public DbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_clothes_table = "CREATE TABLE "+ Params.CLOTH_TABLE + "("
                + Params.KEY_CLOTH_ID + " INTEGER PRIMARY KEY,"
                + Params.KEY_CATEGORY +" TEXT, "
                +Params.KEY_SUBCATEGORY+" TEXT, "
                +Params.KEY_CLOTH_IMAGE_PATH + " TEXT)";
        Log.d("dbtest", "Query being run is :"+ create_clothes_table);
        db.execSQL(create_clothes_table);

        String create_outfit_table = "CREATE TABLE "+ Params.OUTFIT_TABLE + "("
                + Params.KEY_OUTFIT_ID + " INTEGER PRIMARY KEY,"
                + Params.KEY_OUTFIT_NAME +" TEXT, "
                +Params.KEY_TOP_ID+" INTEGER, "
                +Params.KEY_BOTTOM_ID+" INTEGER, "
                +Params.KEY_JACKET_ID+" INTEGER, "
                +Params.KEY_SHOES_ID+" INTEGER, "
                +Params.KEY_ACCESSORIES_ID+" INTEGER, "
                +Params.KEY_OUTFIT_COVER_PATH + " TEXT)";
        Log.d("dbtest", "Query being run is :"+ create_outfit_table);
        db.execSQL(create_outfit_table);

//        String create_brief_case_table = "CREATE TABLE "+ Params.CLOTH_TABLE + "(" + Params.KEY_CLOTH_ID + " INTEGER PRIMARY KEY," + Params.KEY_CATEGORY +" TEXT, " +Params.KEY_SUBCATEGORY+" TEXT, "+Params.KEY_CLOTH_IMAGE_PATH + " TEXT)";
//        Log.d("dbtest", "Query being run is :"+ create_brief_case_table);
//        db.execSQL(create_brief_case_table);
//
//        String create_laundry_table = "CREATE TABLE "+ Params.CLOTH_TABLE + "(" + Params.KEY_CLOTH_ID + " INTEGER PRIMARY KEY," + Params.KEY_CATEGORY +" TEXT, " +Params.KEY_SUBCATEGORY+" TEXT, "+Params.KEY_CLOTH_IMAGE_PATH + " TEXT)";
//        Log.d("dbtest", "Query being run is :"+ create_laundry_table);
//        db.execSQL(create_laundry_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCloths(Cloths cloth){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_CATEGORY, cloth.getCategory());
        values.put(Params.KEY_SUBCATEGORY, cloth.getSubcategory());
        values.put(Params.KEY_CLOTH_IMAGE_PATH, cloth.getPath());

        db.insert(Params.CLOTH_TABLE, null, values);
        Log.d("dbtest", "Successfully inserted!!!");
        db.close();
    }

    public List<Cloths> getAllCloths(){
        List<Cloths> clothsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM "+ Params.CLOTH_TABLE;
        Cursor cursor = db.rawQuery(select, null);

        if (cursor.moveToFirst()){
            do {
                Cloths cloths = new Cloths();
                cloths.setId(Integer.parseInt(cursor.getString(0)));
                cloths.setCategory(cursor.getString(1));
                cloths.setSubcategory(cursor.getString(2));
                cloths.setPath(cursor.getString(3));
                clothsList.add(cloths);
            }while (cursor.moveToNext());
        }
        return clothsList;
    }

    public void addOutfit(Outfit outfit){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_OUTFIT_NAME, outfit.getOutfitName());
        values.put(Params.KEY_TOP_ID, outfit.getTopId());
        values.put(Params.KEY_BOTTOM_ID, outfit.getBottomId());
        values.put(Params.KEY_JACKET_ID, outfit.getJacketId());
        values.put(Params.KEY_SHOES_ID, outfit.getShoesId());
        values.put(Params.KEY_ACCESSORIES_ID, outfit.getAccessoriesId());
        values.put(Params.KEY_OUTFIT_COVER_PATH, outfit.getOutfitImagePath());

        db.insert(Params.OUTFIT_TABLE, null, values);
        Log.d("dbtest", "Successfully inserted!!!");
        db.close();
    }

    public List<Outfit> getAllOutfits(){
        List<Outfit> outfitList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM "+ Params.OUTFIT_TABLE;
        Cursor cursor = db.rawQuery(select, null);

        if (cursor.moveToFirst()){
            do {
                Outfit outfit = new Outfit();
                outfit.setId(Integer.parseInt(cursor.getString(0)));
                outfit.setOutfitName(cursor.getString(1));
                outfit.setTopId(Integer.parseInt(cursor.getString(2)));
                outfit.setBottomId(Integer.parseInt(cursor.getString(3)));
                outfit.setJacketId(Integer.parseInt(cursor.getString(4)));
                outfit.setShoesId(Integer.parseInt(cursor.getString(5)));
                outfit.setAccessoriesId(Integer.parseInt(cursor.getString(6)));
                outfit.setOutfitImagePath(cursor.getString(7));
                outfitList.add(outfit);
            }while (cursor.moveToNext());
        }
        return outfitList;
    }
    public List<Cloths> getByCategory(String category){
        List<Cloths> clothsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM "+ Params.CLOTH_TABLE+ " WHERE "+ Params.KEY_CATEGORY+ "="+ "\""+category+"\"";
        Cursor cursor = db.rawQuery(select, null);

        if (cursor.moveToFirst()){
            do {
                Cloths cloths = new Cloths();
                cloths.setId(Integer.parseInt(cursor.getString(0)));
                cloths.setCategory(cursor.getString(1));
                cloths.setSubcategory(cursor.getString(2));
                cloths.setPath(cursor.getString(3));
                clothsList.add(cloths);
            }while (cursor.moveToNext());
        }
        return clothsList;
    }
    public List<Cloths> getByID(int id){
        List<Cloths> clothsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM "+ Params.CLOTH_TABLE+ " WHERE "+ Params.KEY_CLOTH_ID+ "="+ "\"" +id+ "\"";
        Cursor cursor = db.rawQuery(select, null);

        if (cursor.moveToFirst()){
            do {
                Cloths cloths = new Cloths();
                cloths.setId(Integer.parseInt(cursor.getString(0)));
                cloths.setCategory(cursor.getString(1));
                cloths.setSubcategory(cursor.getString(2));
                cloths.setPath(cursor.getString(3));
                clothsList.add(cloths);
            }while (cursor.moveToNext());
        }
        return clothsList;
    }
}
