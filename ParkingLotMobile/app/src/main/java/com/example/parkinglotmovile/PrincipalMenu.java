package com.example.parkinglotmovile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PrincipalMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_menu);
        String Title= "Bienvenidos a la Aplicacion";
        this.setTitle(Title);
    }
}