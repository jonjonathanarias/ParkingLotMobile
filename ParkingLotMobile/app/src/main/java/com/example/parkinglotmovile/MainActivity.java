package com.example.parkinglotmovile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    String CLAVE_GENERAL="estornudo";

    TextView tv_registrar;


    EditText et1, et2;

    private Cursor fila;
    String Title ="inicio de sesión";
    private Intent RegistroDatos;
    private Object view;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle(Title);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.drawable.ic_action_name);
        }

        et1= (EditText) findViewById(R.id.etUsuario);
        et2= (EditText) findViewById(R.id.etContraseña);

    }


    public void login(View view){
        DBAyuda admin = new DBAyuda(this, "ParkingLotMobile",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();

        BCrypt c =new BCrypt();
        //Encrypter c =new Encrypter();

        String usuario=et1.getText().toString();
        String contrasenia=et2.getText().toString().trim();



        fila=db.rawQuery("select nombreUsuario,claveUsuario from usuarios where nombreUsuario='"+
                usuario+"'",null);

        try {
            if (fila.moveToFirst()){
                String usua=fila.getString(0);
                String clav=fila.getString(1).trim();

                if (usuario.equals(usua)&& c.verificarPassword (contrasenia, clav)){
                    Intent ven=new Intent(this, PrincipalMenu.class );
                    ven.putExtra("usuario", usuario);
                    startActivityForResult(ven, 1234);
                    startActivity(ven);
                    et1.setText("");
                    et2.setText("");

                }
                else {
                    Toast toast=Toast.makeText(this, "Contraseña incorrecta.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
            else {
                Toast toast=Toast.makeText(this, "Ususario inexistente.", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        catch (Exception e){
            Toast toast=Toast.makeText(this, "error" + e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public void registroDatos(View v){
        Intent rdatos=new Intent(this, RegistrarDatos.class);
        startActivity(rdatos);
    }
}

