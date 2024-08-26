package com.example.semana2_senati;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.semana2_senati.data.Administrador;
import com.example.semana2_senati.databinding.ActivityDataBaseBinding;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.semana2_senati.data.User;
import com.example.semana2_senati.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        User admin = new User(this, "users", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String correo = binding.etEmail.getText().toString().trim();
        String contraseña = binding.etPassword.getText().toString().trim();
        String usuario = binding.etUser.getText().toString().trim();

        if(!correo.isEmpty() && !contraseña.isEmpty() && !usuario.isEmpty()){
            ContentValues register = new ContentValues();
            register.put("correo", correo);
            register.put("contraseña", contraseña);
            register.put("usuario", usuario);

            long result = db.insert("users", null, register);
            db.close();

            if (result != -1) {
                clearInputs();
                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputs() {
        binding.etEmail.setText("");
        binding.etPassword.setText("");
        binding.etUser.setText("");
    }

}