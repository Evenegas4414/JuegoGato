package com.ervr.juegogato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean jugadorX = true;
    private int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button resetButton = findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetear();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (jugadorX) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        contador++;

        if (verificarGanador()) {
            if (jugadorX) {
                Toast.makeText(this, "¡Jugador X gana!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "¡Jugador O gana!", Toast.LENGTH_SHORT).show();
            }
            bloquearBotones();
        } else if (contador == 9) {
            Toast.makeText(this, "¡Empate!", Toast.LENGTH_SHORT).show();
            bloquearBotones();
        } else {
            jugadorX = !jugadorX;
        }
    }

    private boolean verificarGanador() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void bloquearBotones() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetear() {
        jugadorX = true;
        contador = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }
}
