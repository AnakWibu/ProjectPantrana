package com.example.pantranafinal.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class RecipeDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public String DATABASE_NAME = "RecipeDatabase.db";
    private int DATABASE_VERSION = 1;

    public String TABLE_NAME = "RecipeList";
    public String COLUMN_ID = "RecipeId";
    public String COLUMN_NAME = "RecipeName";
    public String COLUMN_DESC = "RecipeDesc";

    public RecipeDatabaseHelper(@Nullable Context context) {
        super(context, "RecipeDatabase.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " +
                COLUMN_DESC + " TEXT);";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void addData(String name, String desc){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_DESC, desc);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(result == -1){

            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = null;
        if(sqLiteDatabase != null){

            cursor = sqLiteDatabase.rawQuery(query, null);
        }

        return cursor;
    }

    public void deleteData(String id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_NAME,"RecipeId=?", new String[]{id});
        if(result == -1){

            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }
}
