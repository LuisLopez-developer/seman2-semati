package com.example.semana2_senati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        initUI();
    }

    private void initUI() {
        initListeners();
    }

    private void initListeners() {
        binding.ivBack.setOnClickListener(v -> returnLogin());
        // Set up the register button click listener
        binding.button.setOnClickListener(v -> registerUser());
    }

    private void returnLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void registerUser() {
        // Get the input data
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();
        String user = binding.etUser.getText().toString().trim();

        // Call the createUser method
        long result = userDatabase.createUser(email, password, user);

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