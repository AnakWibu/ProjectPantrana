package com.example.pantranafinal.UserDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserHelper {

    private final String TABLE_NAME = "users";
    private UserDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public UserHelper(Context context){
        dbHelper = new UserDatabaseHelper(context);
    }

    // insert
    public void insert(User user){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("email", user.getEmail());
        contentValues.put("city", user.getCity());
        contentValues.put("password", user.getPassword());
        contentValues.put("country", user.getCountry());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }
    // auth -> read
    public User auth(String email, String password){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ? and password = ?",
                new String[]{email, password});

        User user = null;
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setCity(cursor.getString(3));
            user.setCountry(cursor.getString(4));
            user.setPassword(cursor.getString(5));

            cursor.close();
        }
        db.close();
        return user;
    }

    // validate user email not same in database
    public User valEmail(String email){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});

        User user = null;
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            user = new User();
            user.setEmail(cursor.getString(3));
            cursor.close();
        }
        db.close();;
        return user;
    }
}
