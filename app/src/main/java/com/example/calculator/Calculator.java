package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class Calculator extends AppCompatActivity {

    private DataCalculator dataCalculator;
    private EditText console;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_grid);
        dataCalculator = new DataCalculator();
        console = findViewById(R.id.console);


    }

    public void setNumberOperation(View view) {
        // на оператор switch платформа ругалась, но код был компактнее.
        int id = view.getId();
        if (id == R.id.button_point) {
            dataCalculator.setConsole_expression('.');
        } else if (id == R.id.button_0) {
            dataCalculator.setConsole_expression('0');
        } else if (id == R.id.button_1) {
            dataCalculator.setConsole_expression('1');
        } else if (id == R.id.button_2) {
            dataCalculator.setConsole_expression('2');
        } else if (id == R.id.button_3) {
            dataCalculator.setConsole_expression('3');
        } else if (id == R.id.button_4) {
            dataCalculator.setConsole_expression('4');
        } else if (id == R.id.button_5) {
            dataCalculator.setConsole_expression('5');
        } else if (id == R.id.button_6) {
            dataCalculator.setConsole_expression('6');
        } else if (id == R.id.button_7) {
            dataCalculator.setConsole_expression('7');
        } else if (id == R.id.button_8) {
            dataCalculator.setConsole_expression('8');
        } else if (id == R.id.button_9) {
            dataCalculator.setConsole_expression('9');
        }
        console.setText(dataCalculator.getConsole_expression());
    }

    public void calcOperation(View view) {
        int id = view.getId();
        if (id == R.id.button_ac) {
            dataCalculator.clearAll();
        } else if (id == R.id.button_ce) {
            dataCalculator.cleanOne();
        } else if (id == R.id.button_divide) {
            dataCalculator.calculate_expression(Operations.DIVIDE);
        } else if (id == R.id.button_multiply) {
            dataCalculator.calculate_expression(Operations.MULTIPLY);
        } else if (id == R.id.button_minus) {
            dataCalculator.calculate_expression(Operations.MINUS);
        } else if (id == R.id.button_plus) {
            dataCalculator.calculate_expression(Operations.PLUS);
        } else if (id == R.id.button_result) {
            dataCalculator.calculate_expression(Operations.RESULT);
        }
        console.setText(dataCalculator.getConsole_expression());
    }
}