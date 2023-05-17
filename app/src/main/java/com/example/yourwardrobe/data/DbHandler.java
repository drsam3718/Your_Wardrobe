package com.example.yourwardrobe.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.example.yourwardrobe.model.Cloths;
import com.example.yourwardrobe.param.Params;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    public DbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE "+ Params.CLOTH_TABLE + "(" + Params.KEY_ID + " INTEGER PRIMARY KEY," + Params.KEY_CATEGORY +" TEXT, " +Params.KEY_SUBCATEGORY+" TEXT, "+Params.KEY_PATH + " TEXT)";
        Log.d("dbtest", "Query being run is :"+ create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCloths(Cloths cloth){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_CATEGORY, cloth.getCategory());
        values.put(Params.KEY_SUBCATEGORY, cloth.getSubcategory());
        values.put(Params.KEY_PATH, cloth.getPath());

        db.insert(Params.CLOTH_TABLE, null, values);
        Log.d("dbtest", "Successfully inserted!!!");
        db.close();
    }

    public List<Cloths> getAllContact(){
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
    public List<Cloths> getByCategory(String category){
        List<Cloths> clothsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM "+ Params.CLOTH_TABLE+ " WHERE "+ Params.KEY_CATEGORY+ "="+ "\"" +category+ "\"";
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
