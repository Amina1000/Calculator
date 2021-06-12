package com.example.calculator;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;


public class CalculatorActivity extends AppCompatActivity implements Constants {

    private DataCalculator dataCalculator;
    private EditText console;
    private Account account;
    private static final int REQUEST_CODE_SETTING_ACTIVITY = 5;
    private TextView sing;
    private final HashMap<String, String> singAccount = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_grid);
        initView();
        initController();
    }

    private void initView() {
        dataCalculator = new DataCalculator();
        console = findViewById(R.id.console);
        account = new Account();
        sing = findViewById(R.id.sing);
    }

    private void initController() {
        // цифровые кнопки
        Button buttonPoint = findViewById(R.id.button_point);
        Button button0 = findViewById(R.id.button_0);
        Button button1 = findViewById(R.id.button_1);
        Button button2 = findViewById(R.id.button_2);
        Button button3 = findViewById(R.id.button_3);
        Button button4 = findViewById(R.id.button_4);
        Button button5 = findViewById(R.id.button_5);
        Button button6 = findViewById(R.id.button_6);
        Button button7 = findViewById(R.id.button_7);
        Button button8 = findViewById(R.id.button_8);
        Button button9 = findViewById(R.id.button_9);

        // назначаем единый обработчик для всех цифровых кнопок
        buttonPoint.setOnClickListener(setNumberOperation);
        button0.setOnClickListener(setNumberOperation);
        button1.setOnClickListener(setNumberOperation);
        button2.setOnClickListener(setNumberOperation);
        button3.setOnClickListener(setNumberOperation);
        button4.setOnClickListener(setNumberOperation);
        button5.setOnClickListener(setNumberOperation);
        button6.setOnClickListener(setNumberOperation);
        button7.setOnClickListener(setNumberOperation);
        button8.setOnClickListener(setNumberOperation);
        button9.setOnClickListener(setNumberOperation);

        // кнопки очистки
        Button buttonAc = findViewById(R.id.button_ac);
        Button buttonCe = findViewById(R.id.button_ce);
        // кнопки команд
        Button buttonPercent = findViewById(R.id.button_percent);
        Button buttonDivide = findViewById(R.id.button_divide);
        Button buttonMultiply = findViewById(R.id.button_multiply);
        Button buttonMinus = findViewById(R.id.button_minus);
        Button buttonPlus = findViewById(R.id.button_plus);
        Button buttonResult = findViewById(R.id.button_result);

        // назначаем единый обработчик для кнопок очистки
        buttonAc.setOnClickListener(calcOperation);
        buttonCe.setOnClickListener(calcOperation);
        // и кнопок команд
        buttonPercent.setOnClickListener(calcOperation);
        buttonDivide.setOnClickListener(calcOperation);
        buttonMultiply.setOnClickListener(calcOperation);
        buttonMinus.setOnClickListener(calcOperation);
        buttonPlus.setOnClickListener(calcOperation);
        buttonResult.setOnClickListener(calcOperation);
    }

    public View.OnClickListener setNumberOperation = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
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
    };

    public View.OnClickListener calcOperation = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
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
    };

    public void openSettingsActivity(View view) {
        Intent runSettings = new Intent(this, SettingsActivity.class);
        // Метод стартует активити, указанную в интенте
        populateAccount();
        runSettings.putExtra(YOUR_ACCOUNT, account);
        ActivityInfo activityInfo =
                runSettings.resolveActivityInfo(getPackageManager(),
                        runSettings.getFlags());

        if (activityInfo != null) {
            startActivityForResult(runSettings, REQUEST_CODE_SETTING_ACTIVITY);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_SETTING_ACTIVITY && data != null) {
            if (data.hasExtra(YOUR_ACCOUNT)) {
                account = data.getParcelableExtra(YOUR_ACCOUNT);
                populateView();
            }
            if (data.hasExtra(NIGHT_MODE)) {
                if (data.getBooleanExtra(NIGHT_MODE, false)) {
                    Toast.makeText(getApplicationContext(), "Выбрана ночная тема приложения.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Выбрана дневная тема приложения.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void populateView() {

        if (account != null) {
            singAccount.put("name", account.getName());
            singAccount.put("email", account.getEmail());
            singAccount.put("surName", account.getSurName());

            sing.setText(String.format("Пользователь %s %s\n email: %s авторизован",
                    singAccount.get("name"), singAccount.get("surName"), singAccount.get("email")));
        }
    }

    private void populateAccount() {
        if (singAccount != null && !singAccount.isEmpty()) {
            account.setName(singAccount.get("name"));
            account.setEmail(singAccount.get("email"));
            account.setSurName(singAccount.get("surName"));
        }
    }
}