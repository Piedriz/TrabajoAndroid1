package com.example.juegoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    Button check,start;
    Spinner attemps;
    EditText number;
    int numattemps;
    int numaleatorio;
    String numintentos;
    String xxx;
    Boolean firtgame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        check=findViewById(R.id.validar);
        check.setOnClickListener(this);
        check.setEnabled(false);

        attemps=findViewById(R.id.intentos);

        number=findViewById(R.id.numero);
        number.setOnClickListener(this);
        number.setEnabled(false);

        start = findViewById(R.id.iniciar);
        start.setOnClickListener(this);


        String[] opciones = {"5","7","10"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opciones);
        attemps.setAdapter(adapter);


        numaleatorio = (int) (Math.random()*100);
        firtgame = true;

    }

    @Override
    public void onBackPressed() {

    }

    public void calcular(int numerodeintentos,int numerodigitado, int numerogenerado){
        if (numerodeintentos > 0){
            if (numerodigitado == numerogenerado){
                Toast.makeText(getApplicationContext(), "USTED ES GANADOR", Toast.LENGTH_SHORT).show();
                number.setEnabled(false);
                check.setEnabled(false);
                start.setEnabled(true);
            }else{
                if (numerodigitado < numerogenerado){
                    Toast.makeText(getApplicationContext(), "SU NUMERO ES MENOR", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "SU NUMERO ES MAYOR", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(getApplicationContext(), "USTED ES PERDEDOR", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "NUMERO: "+xxx, Toast.LENGTH_SHORT).show();
            number.setEnabled(false);
            check.setEnabled(false);
            start.setEnabled(true);
        }

    }
    //Valida si la entrada es un int
    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.numero:
                break;

            case R.id.validar:
                String numelegido = number.getText().toString();
                if (!numelegido.isEmpty() && isNumeric(numelegido) ) {
                    int numselec = Integer.parseInt(numelegido);
                    if (numselec <= 100 && numselec>=0) {
                        numattemps = numattemps-1;
                        calcular(numattemps, numselec, numaleatorio);
                    }else{
                        Toast.makeText(getApplicationContext(), "Solo numeros entre 0 y 100", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Digite entre 0 y 100", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.iniciar:
                if (firtgame) {
                    numintentos = attemps.getSelectedItem().toString();
                    numattemps = Integer.parseInt(numintentos);
                    xxx = String.valueOf(numaleatorio);
                    //Toast.makeText(getApplicationContext(), xxx, Toast.LENGTH_SHORT).show();
                    number.setEnabled(true);
                    check.setEnabled(true);
                    attemps.setEnabled(false);
                    firtgame=false;
                    start.setEnabled(false);
                }else {
                    Intent newgame = new Intent(getApplicationContext(), GameActivity.class);
                    startActivity(newgame);
                }
        }
    }
}