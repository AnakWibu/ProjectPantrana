package com.example.pantranafinal.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public String DATABASE_NAME = "StockDatabase.db";
    private int DATABASE_VERSION = 1;

    public String TABLE_NAME = "StockList";
    public String COLUMN_ID = "StockId";
    public String COLUMN_NAME = "StockName";
    public String COLUMN_QUANTITY = "StockQuantity";
    public String COLUMN_EXPIRED = "StockExpired";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "StockDatabase.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " +
                COLUMN_QUANTITY + " TEXT, " + COLUMN_EXPIRED + " TEXT);";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void addData(String name, int quantity, String expired){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_QUANTITY, quantity);
        contentValues.put(COLUMN_EXPIRED, expired);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(result == -1){

            Toast.makeText(context, "Fail to Add", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(context, "Add Success", Toast.LENGTH_SHORT).show();
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

    public void updateData(String id, String name, String quantity, String expired){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_QUANTITY, quantity);
        contentValues.put(COLUMN_EXPIRED, expired);

        long result = sqLiteDatabase.update(TABLE_NAME, contentValues, "StockId=?", new String[]{id});
        if(result == -1){

            Toast.makeText(context, "Fail to Add", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(context, "Add Success", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteData(String id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_NAME,"StockId=?", new String[]{id});
        if(result == -1){

            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }
}
