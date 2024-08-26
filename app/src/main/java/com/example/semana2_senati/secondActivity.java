package com.example.semana2_senati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.semana2_senati.databinding.ActivityMainBinding;
import com.example.semana2_senati.databinding.ActivitySecondBinding;

public class secondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSignOut.setOnClickListener(v -> back(v));
        binding.btnViewPanel.setOnClickListener(v -> viewPanel(v));
    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void viewPanel(View view){
        Intent intent = new Intent(this, DataBaseActivity.class);
        startActivity(intent);
    }
}