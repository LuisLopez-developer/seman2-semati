package com.example.semana2_senati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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

        binding.btnProductRegister.setOnClickListener(v -> registerPorduct(v));
    }

    private void registerPorduct(View v) {
        Administrador admin = new Administrador(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String code = binding.txtCode.getText().toString();
        String description = binding.txtDescription.getText().toString();
        String price = binding.txtPrice.getText().toString();

        if(!code.isEmpty() && !description.isEmpty() && !price.isEmpty()){
            ContentValues register = new ContentValues();
            register.put("codido", code);
            register.put("description", code);
            register.put("precio", code);

            db.insert("articulos", null, register);
            db.close();

            binding.txtCode.setText("");
            binding.txtDescription.setText("");
            binding.txtPrice.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debes llenar tpdos los campos", Toast.LENGTH_SHORT).show();
        }
    }


}