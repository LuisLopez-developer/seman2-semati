package com.example.semana2_senati;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.semana2_senati.databinding.ActivityDataBaseBinding;
import com.example.semana2_senati.databinding.ActivityMainBinding;

public class DataBaseActivity extends AppCompatActivity {

    private ActivityDataBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDataBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}