package com.example.semana2_senati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.semana2_senati.data.User;
import com.example.semana2_senati.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the binding object
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(v -> register());

        binding.button.setOnClickListener(v -> validateLogin());
        createUserExample();

    }

    private void createUserExample() {
        User userDbHelper = new User(this, "User", null, 1);
        long result = userDbHelper.createUser("luis@senati.com", "contra123", "Equipo Devs");
    }

    private void validateLogin() {
        String correo = binding.etEmail.getText().toString().trim();
        String contraseña = binding.etPassword.getText().toString().trim();

        if (!correo.isEmpty() && !contraseña.isEmpty()) {
            User userDbHelper = new User(this, "User", null, 1);
            boolean isValidUser = userDbHelper.validateUser(correo, contraseña);

            if (isValidUser) {
                // Obtener el nombre del usuario
                String userName = userDbHelper.getUserName(correo);

                // Crear un intent para pasar al siguiente Activity
                Intent intent = new Intent(this, secondActivity.class);
                intent.putExtra("USER_NAME", userName);  // Pasar el nombre del usuario
                startActivity(intent);

                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Correo o contraseña equivocada", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor, ingresa tu correo y contraseña", Toast.LENGTH_SHORT).show();
        }
    }

    private void register(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}