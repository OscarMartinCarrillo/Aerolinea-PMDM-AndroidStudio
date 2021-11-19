package org.izv.omc.aerolinealoremipsum_pmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    //Creamos las variables
    //Boton de volver al inicio
    protected Button btInicio;
    //Los Strings son los que hay en el bundle que son necesarias
    protected String etOrigen, etDestino, cbMovilidadReducida, cbPrimeraClase, swMascota, cbVentanilla, cbDesayuno, cbAlmuerzo, cbCena, swSeguro, swPreferente, random, total;
    //Los TextView son los cuadros de texto de la Actividad de la factura
    protected TextView tvPrecioVuelo, tvPrecioMovilidadReducida, tvPrecioPrimeraClase, tvPrecioMascota, tvPrecioVentanilla, tvPrecioDesayuno, tvPrecioAlmuerzo, tvPrecioCena, tvPrecioSeguro, tvPrecioPreferente, tvPrecioTotal;


    //Funcion onCreate por defecto
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //Cargamos los bundle que le hemos pasado en la anterior actividad
        Bundle bundle = getIntent().getExtras();

        //Funcion para asignar los datos del bundle a las variables con el mismo nombre
        getDatos(bundle);

        //Initialize para no ensuciar el onCreate
        initialize();

    }

    private void initialize() {
        //AsignarTextViews para asignar los TextView como su nombre dice
        asignarTextViews();
        //Funcion para imprimir los datos
        imprimirDatos();

        //Boton de inicio con el intent
        btInicio.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

    }

    private void asignarTextViews() {
        //Le asignamos a los TextView su correspondiente cuadro de texto de la Activity
        tvPrecioVuelo=findViewById(R.id.tvPrecioVuelo);
        tvPrecioMovilidadReducida=findViewById(R.id.tvPrecioMovilidadReducida);
        tvPrecioPrimeraClase=findViewById(R.id.tvPrecioPrimeraClase);
        tvPrecioMascota=findViewById(R.id.tvPrecioMascota);
        tvPrecioVentanilla=findViewById(R.id.tvPrecioVentanilla);
        tvPrecioDesayuno=findViewById(R.id.tvPrecioDesayuno);
        tvPrecioAlmuerzo=findViewById(R.id.tvPrecioAlmuerzo);
        tvPrecioCena=findViewById(R.id.tvPrecioCena);
        tvPrecioSeguro=findViewById(R.id.tvPrecioSeguro);
        tvPrecioPreferente=findViewById(R.id.tvPrecioPreferente);
        tvPrecioTotal=findViewById(R.id.tvPrecioTotal);
        btInicio=findViewById(R.id.btInicio);

    }

    //Imprimir los datos de factura
    private void imprimirDatos() {
        //Llenamos el precio del vuelo sin extras
        tvPrecioVuelo.setText(random+ "€");

        //Comprobamos si tiene han llegado marcados los extras para llenar los textView
        // con el precio del extra, o en caso contrario no se llena y se deja el 0 por defecto que hay en el layout
        if (cbMovilidadReducida.equals("true"))
            tvPrecioMovilidadReducida.setText("50€");
        if (cbPrimeraClase.equals("true"))
            tvPrecioPrimeraClase.setText("250€");
        if (swMascota.equals("true"))
            tvPrecioMascota.setText("200€");
        if (cbDesayuno.equals("true"))
            tvPrecioDesayuno.setText("15€");
        if (cbAlmuerzo.equals("true"))
            tvPrecioAlmuerzo.setText("25€");
        if (cbCena.equals("true"))
            tvPrecioCena.setText("20€");
        if (swSeguro.equals("true"))
            tvPrecioSeguro.setText("5.5€");
        if (swPreferente.equals("true"))
            tvPrecioPreferente.setText("100€");

        //Le ponemos el precio toal
        tvPrecioTotal.setText(total + "€");
    }

    //Get datos del bundle que le asiganmos a las variables con el mismo nombre
    private void getDatos(Bundle bundle) {
        etOrigen=bundle.getString("etOrigen");
        etDestino=bundle.getString("etDestino");
        cbMovilidadReducida=bundle.getString("cbMovilidadReducida");
        cbPrimeraClase=bundle.getString("cbPrimeraClase");
        swMascota=bundle.getString("swMascota");
        cbVentanilla=bundle.getString("cbVentanilla");
        cbDesayuno=bundle.getString("cbDesayuno");
        cbAlmuerzo=bundle.getString("cbAlmuerzo");
        cbCena=bundle.getString("cbCena");
        swSeguro=bundle.getString("swSeguro");
        swPreferente=bundle.getString("swPreferente");
        random = bundle.getString("random");
        total = bundle.getString("total");
    }
}