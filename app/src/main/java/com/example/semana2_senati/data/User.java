package com.example.semana2_senati.data;
import android.annotation.SuppressLint;
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

    @SuppressLint("Range")
    public String getUser(String correo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String userName = null;

        // Definir la cláusula WHERE
        String selection = "correo = ?";
        String[] selectionArgs = new String[]{ correo };

        // Consultar la base de datos
        Cursor cursor = db.query(
                "users",           // Nombre de la tabla
                new String[]{"usuario"}, // Columnas a recuperar
                selection,         // Cláusula WHERE
                selectionArgs,     // Argumentos de selección
                null,              // Agrupamiento (GROUP BY)
                null,              // Cláusula HAVING
                null               // Ordenamiento (ORDER BY)
        );

        // Verificar si el cursor tiene algún resultado
        if (cursor != null && cursor.moveToFirst()) {
            // Extraer el nombre de usuario
            userName = cursor.getString(cursor.getColumnIndex("usuario"));
            cursor.close();
        }

        db.close();
        return userName;
    }

}
