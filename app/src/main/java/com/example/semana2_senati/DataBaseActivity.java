package com.example.semana2_senati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.semana2_senati.data.Administrador;
import com.example.semana2_senati.databinding.ActivityDataBaseBinding;

public class DataBaseActivity extends AppCompatActivity {

    private ActivityDataBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDataBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnProductRegister.setOnClickListener(v -> registerPorduct());
        binding.btnProductUpdate.setOnClickListener(v -> updateProduct());
        binding.btnProductSearch.setOnClickListener(v -> searchProduct());
    }

    private void registerPorduct() {
        Administrador admin = new Administrador(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String code = binding.txtCode.getText().toString();
        String description = binding.txtDescription.getText().toString();
        String price = binding.txtPrice.getText().toString();

        if(!code.isEmpty() && !description.isEmpty() && !price.isEmpty()){
            ContentValues register = new ContentValues();
            register.put("codigo", code);
            register.put("description", description);
            register.put("precio", price);

            db.insert("articulos", null, register);
            db.close();

            clearInputs();

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debes llenar tpdos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteProduct(){

    }

    private void searchProduct(){
        Administrador admin = new Administrador(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String code = binding.txtCode.getText().toString();

        if (!code.isEmpty()) {
            Cursor cursor = db.rawQuery("SELECT description, precio FROM articulos WHERE codigo = ?", new String[]{code});

            if (cursor.moveToFirst()) {
                String description = cursor.getString(0); // Guardar la descripción del producto
                String price = cursor.getString(1);

                binding.txtDescription.setText(description); // Primera columna: descripción
                binding.txtPrice.setText(price); // Segunda columna: precio

                // Usar la variable description en el mensaje de Toast
                Toast.makeText(this, "Producto \"" + description + "\" encontrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No existe un artículo con ese código", Toast.LENGTH_SHORT).show();
            }

            cursor.close();
        } else {
            Toast.makeText(this, "Debes ingresar el código del producto", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    private void updateProduct() {
        Administrador admin = new Administrador(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String code = binding.txtCode.getText().toString();
        String description = binding.txtDescription.getText().toString();
        String price = binding.txtPrice.getText().toString();

        if (!code.isEmpty()) {
            String[] column = { "codigo" }; // Columna a recuperar
            String selection = "codigo = ?";
            String[] selectionArgs = new String[]{ code };

            // Ejecuta la consulta
            Cursor cursor = db.query("articulos", column, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.close();

                ContentValues values = new ContentValues();

                // Solo agrega los valores que no están vacíos
                if (!description.isEmpty()) {
                    values.put("description", description);
                }
                if (!price.isEmpty()) {
                    values.put("precio", price);
                }

                // Asegúrate de que hay algo que actualizar
                if (values.size() > 0) {
                    // Actualiza la base de datos
                    String whereClause = "codigo = ?";
                    String[] whereArgs = new String[]{ code };
                    int rowsAffected = db.update("articulos", values, whereClause, whereArgs);

                    if (rowsAffected > 0) {
                        Toast.makeText(this, "Producto actualizado exitosamente", Toast.LENGTH_SHORT).show();
                        clearInputs();
                    } else {
                        Toast.makeText(this, "Error al actualizar el producto", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "No se ingresaron datos para actualizar", Toast.LENGTH_SHORT).show();
                }
            } else {
                // El producto no existe
                Toast.makeText(this, "El producto con el código ingresado no existe", Toast.LENGTH_SHORT).show();
                if (cursor != null) {
                    cursor.close();
                }
            }

            db.close();
        } else {
            Toast.makeText(this, "El código del producto es requerido", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputs(){
        binding.txtCode.setText("");
        binding.txtDescription.setText("");
        binding.txtPrice.setText("");
    }

}