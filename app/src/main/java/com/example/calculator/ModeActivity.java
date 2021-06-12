package com.example.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

/**
 * homework com.example.calculator
 *
 * @author Amina
 * 03.06.2021
 */
public class ModeActivity extends AppCompatActivity implements Constants{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Устанавливать тему надо только до установки макета активити
        setNightMode(getNightModePref());
    }

    public static void setNightMode(boolean mode) {
        if (mode) {
            setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    // Чтение настроек, параметр «ночной режим»
    protected boolean getNightModePref() {
        // getSharedPreferences() доступна только в активити
        SharedPreferences prefs = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (prefs.contains(NIGHT_MODE)) {
            // Чтение настроек, параметр «ночной режим», если настройка не найдена - взять по умолчанию false
            return prefs.getBoolean(NIGHT_MODE, false);
        } else {
            return false;
        }
    }

    // Сохранение настроек
    protected void savePrefs(ToggleButton toggleButton) {
        // getSharedPreferences() доступна только в активити
        SharedPreferences prefs = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        // Сохранение настроек, параметр «ночной режим»
        editor.putBoolean(NIGHT_MODE, toggleButton.isChecked());
        editor.apply();
    }

    // Восстановление настроек
    protected void loadPrefs(ToggleButton toggleButton) {
        // getSharedPreferences() доступна только в активити
        SharedPreferences prefs = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        // переключатель ночного режима только в портретном режиме
        if (toggleButton != null) {
            if (prefs.contains(NIGHT_MODE)) {
                // Чтение настроек, параметр «ночной режим», если настройка не найдена - взять по умолчанию false
                toggleButton.setChecked(prefs.getBoolean(NIGHT_MODE, false));
            } else {
                toggleButton.setChecked(false);
            }
        }
    }
}
