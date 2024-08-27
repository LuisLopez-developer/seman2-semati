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
    private User userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userDatabase = new User(this, "Users", null, 1);

        // Set up the register button click listener
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        // Get the input data
        String correo = binding.etEmail.getText().toString().trim();
        String contraseña = binding.etPassword.getText().toString().trim();
        String usuario = binding.etUser.getText().toString().trim();

        // Call the createUser method
        long result = userDatabase.createUser(correo, contraseña, usuario);

        // Provide feedback to the user
        if (result != -1) {
            Toast.makeText(this, "Usuario registrado.", Toast.LENGTH_SHORT).show();
            clearInputs(); // Clear the input fields after successful registration
        } else {
            Toast.makeText(this, "Error en el registro. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputs() {
        binding.etEmail.setText("");
        binding.etPassword.setText("");
        binding.etUser.setText("");
    }

}