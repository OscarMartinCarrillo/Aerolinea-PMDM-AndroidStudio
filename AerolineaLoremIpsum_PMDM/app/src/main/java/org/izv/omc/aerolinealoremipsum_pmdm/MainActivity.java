package org.izv.omc.aerolinealoremipsum_pmdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    //Creo las variables con el mismo nombre que id tienen en las Activity para facilitar
    //todo y nos aseguramos que es del mismo tipo
    protected EditText etOrigen, etDestino, etNombre, etEmail, etApellidos, etDireccion, etTlf;
    protected CheckBox cbMovilidadReducida, cbPrimeraClase, cbVentanilla, cbDesayuno, cbAlmuerzo, cbCena, cbTerminos;
    protected Switch swMascota, swSeguro, swPreferente;
    protected Button btComprar;

    //Metodo onCreate por defecto
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Le asiganmos la toolbar que hemos definido en la Activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Funcion en la que pondremos todo el codigo
        initialize();
    }

    private void initialize() {
        //Hacemos la asignacion de todos los campos
        etOrigen = findViewById(R.id.etOrigen);
        etDestino = findViewById(R.id.etDestino);
        cbMovilidadReducida = findViewById(R.id.cbMovilidadReducida);
        cbPrimeraClase = findViewById(R.id.cbPrimeraClase);
        cbVentanilla = findViewById(R.id.cbVentanilla);
        cbDesayuno = findViewById(R.id.cbDesayuno);
        cbAlmuerzo = findViewById(R.id.cbAlmuerzo);
        cbCena = findViewById(R.id.cbCena);
        swMascota = findViewById(R.id.swMascota);
        swSeguro = findViewById(R.id.swSeguro);
        swPreferente = findViewById(R.id.swPreferente);
        btComprar = findViewById(R.id.btComprar);
        cbTerminos = findViewById(R.id.cbTerminos);
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etDireccion = findViewById(R.id.etDireccion);
        etEmail = findViewById(R.id.etEmail);
        etTlf = findViewById(R.id.etTlf);

        //Creamos un mensaje de Toast
        Context context = getApplicationContext();
        CharSequence text = "Se cobrar un coste adicional";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);

        //Lo lanzamos cuando hagamos click en el Switch de Preferente con una funcion lambda
        swPreferente.setOnClickListener((View view) -> {
            //Lo mostramos solo si esta Activo, para que no nos muestre el Toast al desactivarlo tambien, solo al activarlo
            if (swPreferente.isChecked())
                toast.show();
        });

        //Dialogo de Alerta para confirmar
        AlertDialog dialogo = new AlertDialog
                .Builder(MainActivity.this) // NombreDeLaActividad.this, o getActivity() si es dentro de un fragmento
                .setPositiveButton("Seguir con la compra", (dialog, which) -> {
                    //Click en el boton positivo, así que la acción esta confirmada


                    //Aqui nos aseguramos que al menos han puesto los campos trimandolos, lo necesario para que la aplicacion no se cierre
                    if (
                            etDestino.getText().toString().trim().length()   > 1 &&
                            etOrigen.getText().toString().trim().length()    > 1 &&
                            etNombre.getText().toString().trim().length()    > 1 &&
                            etTlf.getText().toString().trim().length()       > 1 &&
                            etEmail.getText().toString().trim().length()     > 1 &&
                            etApellidos.getText().toString().trim().length() > 1 &&
                            etDireccion.getText().toString().trim().length() > 1
                    ) {
                        //Creamos el intent hacia la segunda Activity
                        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                        //Creamos un Bundle en el que meteremos todo
                        Bundle bundle = new Bundle();
                        //Pasamos nuestro bundle vacio para llenarlo en la funcion
                        pasarDatos(bundle);

                        //Le pasamos el bundle al intent
                        intent.putExtras(bundle);

                        //Iniziamos el intent hacia la segunda Activity
                        startActivity(intent);
                    } else
                        //Mensaje de error en caso de no llenar todos los campos y asi no mandarnos a la segunda actividad durante una larga duracion
                        Toast.makeText(getApplicationContext(), "Debe completar los campos obligatorios", Toast.LENGTH_LONG).show();

                })
                .setNegativeButton("Cancelar(SnackBar)", (dialog, which) -> {
                    //Click en el botón negativo, no confirmaron

                    //Mensaje por SnackBar
                    Snackbar.make(getWindow().getDecorView().getRootView(), "No hay conexion a internet.", Snackbar.LENGTH_LONG)
                            .show();

                    //Simplemente descartamos el diálogo
                    dialog.dismiss();
                })
                .setTitle("Seguir con la compra del vuelo") // El título
                .setMessage("¿Deseas Seguir con la compra del vuelo?") // El mensaje
                .create();//Llamar a Create para crear el AlertDialog

        //Mostrmos el dialogo anterior creado al darle al boton de Comprar
        btComprar.setOnClickListener((View view) -> {
            //Comprobamos las condiciones
            if (cbTerminos.isChecked()) {
                dialogo.show();
            } else
                //Mensaje de aceptar las condiciones
                Toast.makeText(getApplicationContext(), "Debe Aceptar los terminos y condiciones", Toast.LENGTH_LONG).show();
        });


    }

    //Funcion para pasarle los datos al bundle que recibe, asi no llenamos el initialize
    private void pasarDatos(Bundle bundle) {
        //Pasamos a String el contenido del texto y en minuscula para que no haya problemas al calcular el precio del vuelo
        bundle.putString("etOrigen", etOrigen.getText().toString().toLowerCase());
        bundle.putString("etDestino", etDestino.getText().toString().toLowerCase());

        //El resto de campos al no ser de texto pasamos si esta marcado en caso de los Checkbox y si esta activado en caso de los Switch
        bundle.putString("cbMovilidadReducida", String.valueOf(cbMovilidadReducida.isChecked()));
        bundle.putString("cbPrimeraClase", String.valueOf(cbPrimeraClase.isChecked()));
        bundle.putString("swMascota", String.valueOf(swMascota.isActivated()));
        bundle.putString("cbDesayuno", String.valueOf(cbDesayuno.isChecked()));
        bundle.putString("cbAlmuerzo", String.valueOf(cbAlmuerzo.isChecked()));
        bundle.putString("cbCena", String.valueOf(cbCena.isChecked()));
        bundle.putString("swSeguro", String.valueOf(swSeguro.isActivated()));
        bundle.putString("swPreferente", String.valueOf(swPreferente.isActivated()));
    }

    //Funcion por defecto para inflar el menu dentro del toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //Funcion para darle funcionalidad al menu overflow
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Este Switch controla toda la funcionalidad
        switch (item.getItemId()) {
            //En caso de darle al boton de menu de overflow nos llevara a unas webs indicadas
            case R.id.moDestinos:
                Intent i1 = new Intent(Intent.ACTION_VIEW);
                i1.setData(Uri.parse("https://www.tripadvisor.es/TravelersChoice-Destinations-cPopular-g1"));
                startActivity(i1);
                break;
            case R.id.moMaletas:
                Intent i2 = new Intent(Intent.ACTION_VIEW);
                i2.setData(Uri.parse("https://www.amazon.es/s?k=maletas&__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&ref=nb_sb_noss"));
                startActivity(i2);
                break;
            case R.id.moHoteles:
                Intent i3 = new Intent(Intent.ACTION_VIEW);
                i3.setData(Uri.parse("https://www.booking.com/"));
                startActivity(i3);
                break;
            //El caso por default nos mostrara un mensaje por Toast con el mensaje "No disponible y una corta duracion"
            default:
                Toast.makeText(getApplicationContext(), "No disponible", Toast.LENGTH_SHORT).show();
                break;
        }
        //Return de la funcion
        return super.onOptionsItemSelected(item);
    }
}