package com.example.semana2_senati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.semana2_senati.databinding.ActivitySecondBinding;

public class secondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    
        initUI();
    }

    private void initUI() {
        initExtras();
        initListeners();
    }

    private void initExtras() {
        Intent intent = getIntent();
        String userName = intent.getStringExtra("USER_NAME");

        // Set the welcome text with the username
        if (userName != null) {
            String welcomeText = String.format("Bienvenido %s", userName);
            binding.twWelcomeTitle.setText(welcomeText);
        }
    }


    private void initListeners() {
        binding.btnSignOut.setOnClickListener(v -> back());
        binding.btnViewPanel.setOnClickListener(v -> viewPanel());
    }

    public void back(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void viewPanel(){
        Intent intent = new Intent(this, DataBaseActivity.class);
        startActivity(intent);
    }
}