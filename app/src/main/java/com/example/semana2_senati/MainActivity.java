package com.example.semana2_senati;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.semana2_senati.data.User;
import com.example.semana2_senati.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private User userDbHelper; // Declare User class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the binding object
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the User class
        userDbHelper = new User(this, "Users", null, 1);

        binding.btnRegister.setOnClickListener(v -> register());

        binding.button.setOnClickListener(v -> validateLogin());
    }

    private void validateLogin() {
        // Get the input data from the UI
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        // Validate the user's credentials
        boolean isValid = userDbHelper.validateUser(email, password);

        // Provide feedback to the user
        if (isValid) {
            // Get the username associated with the email
            String userName = userDbHelper.getUserNameByEmail(email);

            // Create an intent to start secondActivity
            Intent intent = new Intent(this, secondActivity.class);
            intent.putExtra("USER_NAME", userName); // Pass the username to the next activity
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid email or password. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }


    private void register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}