package com.example.parkinglotmovile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


public class PrincipalMenu extends AppCompatActivity {

    TextView tvnombreuserlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_menu);
        String Title= "Bienvenidos a la Aplicacion";
        this.setTitle(Title);

        tvnombreuserlog=(TextView) findViewById(R.id.tvnombreuserlog);
        Bundle extras = getIntent().getExtras();
        String s = extras.getString("usuario");
        s = s + " ,Bienvenidos a la App!";
        tvnombreuserlog.setText(s);
    }
}