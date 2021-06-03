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
        Button button_point = findViewById(R.id.button_point);
        Button button_0 = findViewById(R.id.button_0);
        Button button_1 = findViewById(R.id.button_1);
        Button button_2 = findViewById(R.id.button_2);
        Button button_3 = findViewById(R.id.button_3);
        Button button_4 = findViewById(R.id.button_4);
        Button button_5 = findViewById(R.id.button_5);
        Button button_6 = findViewById(R.id.button_6);
        Button button_7 = findViewById(R.id.button_7);
        Button button_8 = findViewById(R.id.button_8);
        Button button_9 = findViewById(R.id.button_9);

        // назначаем единый обработчик для всех цифровых кнопок
        button_point.setOnClickListener(setNumberOperation);
        button_0.setOnClickListener(setNumberOperation);
        button_1.setOnClickListener(setNumberOperation);
        button_2.setOnClickListener(setNumberOperation);
        button_3.setOnClickListener(setNumberOperation);
        button_4.setOnClickListener(setNumberOperation);
        button_5.setOnClickListener(setNumberOperation);
        button_6.setOnClickListener(setNumberOperation);
        button_7.setOnClickListener(setNumberOperation);
        button_8.setOnClickListener(setNumberOperation);
        button_9.setOnClickListener(setNumberOperation);

        // кнопки очистки
        Button button_ac = findViewById(R.id.button_ac);
        Button button_ce = findViewById(R.id.button_ce);
        // кнопки команд
        Button button_percent = findViewById(R.id.button_percent);
        Button button_divide = findViewById(R.id.button_divide);
        Button button_multiply = findViewById(R.id.button_multiply);
        Button button_minus = findViewById(R.id.button_minus);
        Button button_plus = findViewById(R.id.button_plus);
        Button button_result = findViewById(R.id.button_result);

        // назначаем единый обработчик для кнопок очистки
        button_ac.setOnClickListener(calcOperation);
        button_ce.setOnClickListener(calcOperation);
        // и кнопок команд
        button_percent.setOnClickListener(calcOperation);
        button_divide.setOnClickListener(calcOperation);
        button_multiply.setOnClickListener(calcOperation);
        button_minus.setOnClickListener(calcOperation);
        button_plus.setOnClickListener(calcOperation);
        button_result.setOnClickListener(calcOperation);
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