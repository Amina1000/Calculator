package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.Locale;

public class SettingsActivity extends ModeActivity implements Constants {

    private EditText editName;
    private EditText editSurName;
    private EditText editAge;
    private EditText editEmail;
    private Account account;
    private ToggleButton btnTheme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // Восстановление настроек из файла настроек
        initView();
        loadPrefs(btnTheme);
        Bundle bundle = getIntent().getExtras();
        account = bundle.getParcelable(YOUR_ACCOUNT);
        populateView();

        btnTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            savePrefs(btnTheme);
            recreate();
        });

        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> {
            Intent intentResult = new Intent();
            createAccount();
            intentResult.putExtra(YOUR_ACCOUNT, account);
            intentResult.putExtra(NIGHT_MODE, btnTheme.isChecked());
            setResult(RESULT_OK, intentResult);
            // Метод finish() завершает активити
            finish();
        });
    }

    private void createAccount() {

        account = new Account(
                editName.getText().toString(),
                editSurName.getText().toString(),
                Integer.parseInt(editAge.getText().toString()),
                editEmail.getText().toString());
    }

    private void populateView() {
        editName.setText(account.getName());
        editSurName.setText(account.getSurName());
        editAge.setText(String.format(Locale.getDefault(), "%d", account.getAge()));
        editEmail.setText(account.getEmail());
    }

    private void initView() {
        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editEmail = findViewById(R.id.editEmail);
        editSurName = findViewById(R.id.editSurname);
        btnTheme = findViewById(R.id.btnTheme);
    }
}