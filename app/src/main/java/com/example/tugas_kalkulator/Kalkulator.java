package com.example.tugas_kalkulator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Kalkulator extends AppCompatActivity {
    int[] buttonAngka = {R.id.btnNol, R.id.btnSatu, R.id.btnDua, R.id.btnTiga, R.id.btnEmpat, R.id.btnLima, R.id.btnEnam, R.id.btnTujuh, R.id.btnDelapan, R.id.btnSembilan};
    int[] buttonOperasi = {R.id.btnTambah, R.id.btnKurang, R.id.btnKali, R.id.btnBagi};
    TextView txtDisplay;

    boolean lastNumber;
    boolean lastKoma;
    boolean stateError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);
        this.txtDisplay = (TextView) findViewById(R.id.txtDisplay);
        setAngkaOnClickListener();
        setOperasiOnClickListener();
    }

    private void setOperasiOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumber && !stateError) {
                    Button button = (Button) v;
                    txtDisplay.append(button.getText());
                    lastNumber = false;
                    lastKoma = false;
                }
            }
        };
        for (int id : buttonOperasi) {
            findViewById(id).setOnClickListener(listener);
        }
        findViewById(R.id.btnKoma).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumber && !stateError && !lastKoma) {
                    txtDisplay.append(".");
                    lastNumber = false;
                    lastKoma = true;
                }
            }
        });
        findViewById(R.id.btnHapus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtDisplay.setText("");
                lastNumber = false;
                stateError = false;
                lastKoma = false;
            }
        });
        findViewById(R.id.btnSama).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqual();
            }
        });
    }

    private void onEqual() {
        if (lastNumber && !stateError) {
            String txt = txtDisplay.getText().toString();
            Expression expression = new ExpressionBuilder(txt).build();
            try {
                double result = expression.evaluate();
                txtDisplay.setText(Double.toString(result));
                lastKoma = true;
            } catch (ArithmeticException ex) {
                txtDisplay.setText("Error");
                stateError = true;
                lastNumber = false;
            }
        }
    }

    private void setAngkaOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (stateError) {
                    txtDisplay.setText(button.getText());
                    stateError = false;
                } else {
                    txtDisplay.append(button.getText());
                }
                lastNumber = true;
            }
        };
        for (int id : buttonAngka) {
            findViewById(id).setOnClickListener(listener);
        }
    }
}



