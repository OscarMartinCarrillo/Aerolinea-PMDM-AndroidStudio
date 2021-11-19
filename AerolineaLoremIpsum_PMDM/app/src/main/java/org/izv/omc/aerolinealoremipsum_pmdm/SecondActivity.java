package org.izv.omc.aerolinealoremipsum_pmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    //Creamos las variables
    //Las strings es porque se lo hemos pasado en String por el Bundle
    protected String etOrigen, etDestino, cbMovilidadReducida, cbPrimeraClase, swMascota, cbVentanilla, cbDesayuno, cbAlmuerzo, cbCena, swSeguro, swPreferente;
    //TextVies que es el campo de texto en el que mostramos el mensaje de total
    protected TextView tvResult;
    //Boton de continuar para la siguiente pantalla de factura y el de volver
    protected Button btContinuar, btVolver;

    //Funcion por defecto de onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Obtenemos le bundle que le hemos pasado en la actividad anterior
        Bundle bundle = getIntent().getExtras();

        //Mediante una funcion obtenemos los datos del bundle y no manchamos mas el codigo
        getDatos(bundle);

        //Funcion initialize para no llenar el onCreate
        initialize();

    }

    private void initialize() {
        //Asignamos el campo de texto y el boton
        tvResult = findViewById(R.id.tvResult);
        btContinuar = findViewById(R.id.btContinuar);
        btVolver=findViewById(R.id.btVolver);

        //A la variable localizacion le pasamos la suma del resultado de la funcion pasarNumero, que esta nos convierte la cadena en un numero
        int localizacion=pasarNumero(etOrigen)+pasarNumero(etDestino);
        //Creamos un random con seed la suma de origen y destino pasado a numero
        Random generator = new Random(localizacion);
        //Generamos un numero random y lo multiplicamos por 1000 para que no desentone demasiado
        double random = generator.nextDouble()*1000;
        //Al tener muchos decimales asi le quitamos los decimales sobrantes y nos quedamos con solo 2 decimales para el precio
        random = Math.round(random*100.0)/100.0;
        //Con esta funcion calculamos el total con los extras marcados
        double total=calcularTotal(random);

        //Le ponemos al cuadro de texto el mensaje con el valor precio total del vuelo
        tvResult.setText("El coste total del viaje sera de: " + total + "€");

        //Creamos un intent a la tercera actividad
        Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
        //Creamos el bundle
        Bundle bundle = new Bundle();
        //Le volvemos a pasar todos los datos al bundle igual que antes, pero en este caso tambien le pasamos le random(precio del vuelo sin extras) y el total(precio del vuelo con extras)
        pasarDatos(bundle, random, total);

        //Le pasamos al intent el bundle
        intent.putExtras(bundle);

        //Al pulsar el boton nos manda a la tercera actividad con el intent
        btContinuar.setOnClickListener(view -> startActivity(intent));
        //Funcion de volver atras
        btVolver.setOnClickListener(view -> finish());
    }

    //Funcion para crear el precio total del vuelo con los extras
    private double calcularTotal(double random) {
        //Estos if comprueban los extras si estan activados, en caso de estarlo
        // se suma una cantidad al vuelo, en caso contrario pasa al siguiente extra
        if (cbMovilidadReducida.equals("true"))
            random += 50;
        if (cbPrimeraClase.equals("true"))
            random += 250;
        if (swMascota.equals("true"))
            random += 200;
        if (cbDesayuno.equals("true"))
            random +=15;
        if (cbAlmuerzo.equals("true"))
            random +=25;
        if (cbCena.equals("true"))
            random +=20;
        if (swSeguro.equals("true"))
            random +=5.5;
        if (swPreferente.equals("true"))
            random +=100;

        //Acaba devolviento el total del vuelo con los extras
        return random;
    }

    private void pasarDatos(Bundle bundle, double random,double total) {
        //Volvemos a ponerle los valores que le queremos pasar a la siguiente actividad
        bundle.putString("etOrigen", etOrigen);
        bundle.putString("etDestino", etDestino);
        bundle.putString("cbMovilidadReducida", cbMovilidadReducida);
        bundle.putString("cbPrimeraClase", cbPrimeraClase);
        bundle.putString("swMascota", swMascota);
        bundle.putString("cbDesayuno", cbDesayuno);
        bundle.putString("cbAlmuerzo", cbAlmuerzo);
        bundle.putString("cbCena", cbCena);
        bundle.putString("swSeguro", swSeguro);
        bundle.putString("swPreferente", swPreferente);
        bundle.putString("random", String.valueOf(random));
        bundle.putString("total", String.valueOf(total));
    }

    //Funcion para pasar el Origen y Destino a numero
    private int pasarNumero(String str) {
        //Creamos un StringBuilder
        StringBuilder sb = new StringBuilder();
        //Recorremos el String
        for (char c : str.toCharArray())
            //Lo concatenamos al StringBuilder obligando a que sea el int de esa letra con el forzado de tipo
            sb.append((int)c);

        BigInteger mInt = new BigInteger(sb.toString());
        //Lo devolvemos el valor
        return mInt.intValue();
    }

    //Funcion para obtener los datos del Bundle que nos llega
    private void getDatos(Bundle bundle) {
        //Sencillamente le asignamos a cada variable
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
    }
}