package com.example.semana2_senati.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class User extends SQLiteOpenHelper{
    public User(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, correo TEXT, contraseña TEXT, usuario TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long createUser(String correo, String contraseña, String usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("correo", correo);
        values.put("contraseña", contraseña);
        values.put("usuario", usuario);
        long result = db.insert("users", null, values);
        db.close();
        return result;
    }

    // Método para validar usuario
    public boolean validateUser(String correo, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE correo = ? AND contraseña = ?", new String[]{correo, contraseña});

        if (cursor != null && cursor.moveToFirst()) {
            cursor.close();
            return true;
        }

        if (cursor != null) {
            cursor.close();
        }
        return false;
    }

    public String getUserNameByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String user = null;
        String[] columns = {"usuario"};
        String selection = "correo = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(
                "users",       // Nombre de la tabla
                columns,       // Columnas a devolver
                selection,     // Columnas para la cláusula WHERE
                selectionArgs, // Valores para la cláusula WHERE
                null,          // Group by
                null,          // Having
                null           // Order by
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                user = cursor.getString(cursor.getColumnIndexOrThrow("usuario"));
            }
            cursor.close();
        }

        db.close();
        return user;
    }

}
