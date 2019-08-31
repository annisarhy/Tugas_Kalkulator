package com.example.tugas_kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.FocusFinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText tCelcius,tKelvin,tFahrenheit,tReamur;
    Button btnKonversi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tCelcius=(EditText) findViewById(R.id.celcius);
        tKelvin=(EditText) findViewById(R.id.kelvin);
        tFahrenheit=(EditText) findViewById(R.id.fahrenheit);
        tReamur=(EditText) findViewById(R.id.reamur);

        btnKonversi = (Button)findViewById(R.id.btn_konversi);
    }

    @Override
    public void onClick(View view) {
        try{
            int ncelcius = Integer.parseInt(tCelcius.getText().toString());
            double nkelvin = ncelcius + 273.15;
            double nfarenheit = ncelcius * 1.8 + 32;
            double nreamur = ncelcius * 0.8;
            tKelvin.setText(String.valueOf(nkelvin));
            tFahrenheit.setText(String.valueOf(nfarenheit));
            tReamur.setText(String.valueOf(nreamur));

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    public void next(View view) {
        Intent explicit = new Intent(this, Kalkulator.class);
        startActivity(explicit);
    }
}
