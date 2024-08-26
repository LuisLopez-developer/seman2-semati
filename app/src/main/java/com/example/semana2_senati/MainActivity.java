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

        binding.button.setOnClickListener(v -> next(v));
        createUserExample();
    }

    public void next(View view){
        Intent intent = new Intent(this, DataBaseActivity.class);
        startActivity(intent);
    }

    private void createUserExample() {
        User userDbHelper = new User(this, "User", null, 1);
        long result = userDbHelper.createUser("luis@senati.com", "contra123", "Equipo Devs");
    }
}