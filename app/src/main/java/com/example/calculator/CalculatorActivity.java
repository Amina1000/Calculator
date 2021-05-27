package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class CalculatorActivity extends AppCompatActivity {

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
            dataCalculator.setConsoleExpression('.');
        } else if (id == R.id.button_0) {
            dataCalculator.setConsoleExpression('0');
        } else if (id == R.id.button_1) {
            dataCalculator.setConsoleExpression('1');
        } else if (id == R.id.button_2) {
            dataCalculator.setConsoleExpression('2');
        } else if (id == R.id.button_3) {
            dataCalculator.setConsoleExpression('3');
        } else if (id == R.id.button_4) {
            dataCalculator.setConsoleExpression('4');
        } else if (id == R.id.button_5) {
            dataCalculator.setConsoleExpression('5');
        } else if (id == R.id.button_6) {
            dataCalculator.setConsoleExpression('6');
        } else if (id == R.id.button_7) {
            dataCalculator.setConsoleExpression('7');
        } else if (id == R.id.button_8) {
            dataCalculator.setConsoleExpression('8');
        } else if (id == R.id.button_9) {
            dataCalculator.setConsoleExpression('9');
        }
        console.setText(dataCalculator.getConsoleExpression());
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
        console.setText(dataCalculator.getConsoleExpression());
    }
}