package com.example.parkinglotmovile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class RegistrarDatos extends AppCompatActivity {

    String Title="Registro de Usuarios";
    EditText etUsuario, etclave, etNombreUsusario, etApellidoUsusario, etTelefonoUsusario, etEmailUsusario, etVehiculo, etModelo, etPatente;

    private static final String AES= "AES";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_datos);
        this.setTitle(Title);

        etUsuario=(EditText) findViewById(R.id.etUsuario);
        etclave=(EditText) findViewById(R.id.etClave);
        etNombreUsusario=(EditText) findViewById(R.id.etNombreUsusario);
        etApellidoUsusario=(EditText) findViewById(R.id.etApellidoUsusario);
        etTelefonoUsusario=(EditText) findViewById(R.id.etTelefonoUsusario);
        etEmailUsusario=(EditText) findViewById(R.id.etEmailUsusario);
        etVehiculo=(EditText) findViewById(R.id.etVehiculo);
        etModelo=(EditText) findViewById(R.id.etModelo);
        etPatente=(EditText) findViewById(R.id.etPatente);

        try{
            encriptar(etclave.getText().toString());
        } catch (Exception e){

        }

    }
    //-----------metodo vara validar si el capo esta vacio------------
    public boolean isNull(){
        if (etUsuario.equals("")&&etclave.equals("")&&etNombreUsusario.equals("")&&etApellidoUsusario.equals("")
                &&etTelefonoUsusario.equals("")&&etEmailUsusario.equals("")&&etVehiculo.equals("")&&etModelo.equals("")&&etPatente.equals("")){
            return false;
        }else {return true;}
    }
    //-----------metodo encriptar contraseña-----------
    public static String encriptar(String etclave) throws Exception{
        //Instancia del Generador de llaves tipo AES
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        keyGenerator.init(128);
        //Instanciamos una llave secreta
        SecretKey secretKey = keyGenerator.generateKey();
        //codificamos la llave en bytes

        byte[] bytesSecretKey = secretKey.getEncoded();
        //Construimos una clave secreta indicandole que es de tipo AES
        SecretKeySpec secretKeySpec = new SecretKeySpec(bytesSecretKey, AES);
        //Instanciamos un objeto de cifrado de tipo AES
        Cipher cipher = Cipher.getInstance(AES);
        //Inicializamos el sistema de cifrado en modo Encriptacion con nuestra clave que hemos creado antes
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        //Procedemos a Encriptar el mensaje
        byte[] mensajeEncritado = cipher.doFinal(etclave.getBytes());
        Log.d("TAG", new String(etclave));


        //Iniciamos el sistema de cifrado en modos Desencriptacion con nuestra clave
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        //Obtenemos el array de bytes del mensaje desencriptado
        byte[] mensajeDesEncriptado = cipher.doFinal(mensajeEncritado);
        Log.d("TAG", new String(mensajeDesEncriptado));
        return new String(mensajeDesEncriptado);
    }


    //-----------boton volver--------
    public void onVolver(View view){
        Intent miIntent= new Intent(RegistrarDatos.this, MainActivity.class);
        startActivity(miIntent);
    }


    public  void registarDatosUsuarios(View v){
        DBAyuda admin=new DBAyuda(this, "ParkingLotMobile", null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        System.out.println(db.getPath());
        String nombreUsuario=etUsuario.getText().toString();
        String claveUsuario=etclave.getText().toString();

        ContentValues values= new ContentValues();

        values.put("nombreusuario", nombreUsuario);
        values.put("claveusuario", claveUsuario);
        System.out.println(nombreUsuario);
        System.out.println(claveUsuario);


        db.insert("usuarios", null, values);

        String nombre=etNombreUsusario.getText().toString();
        String apellido=etApellidoUsusario.getText().toString();
        String telefono=etTelefonoUsusario.getText().toString();
        String email=etEmailUsusario.getText().toString();

        ContentValues values1 = new ContentValues();

        values1.put("nombre", nombre);
        values1.put("apellido", apellido);
        values1.put("telefono", telefono);
        values1.put("email", email);

        db.insert("clientes", null, values1);

        String patente=etPatente.getText().toString();
        String vehiculo=etVehiculo.getText().toString();
        String modelo=etModelo.getText().toString();

        ContentValues values2 = new ContentValues();

        values2.put("patente",patente);
        values2.put("vehiculo", vehiculo);
        values2.put("modelo", modelo);

        db.insert("vehiculos", null, values2);

        db.close();

        Toast ToastMens=Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT);
        ToastMens.show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}