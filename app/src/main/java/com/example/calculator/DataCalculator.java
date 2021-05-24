package com.example.calculator;

import android.os.Parcel;
import android.os.Parcelable;


import java.math.BigDecimal;
import java.math.MathContext;

/**
 * homework com.example.calculator
 *
 * @author Amina
 * 24.05.2021
 */
public class DataCalculator implements Parcelable {
    private BigDecimal result;
    private StringBuilder console_expression;
    Operations last_operations;

    private boolean reset;

    protected DataCalculator(Parcel in) {
        console_expression = new StringBuilder(in.readString());
        result = new BigDecimal(in.readString());
        last_operations= Operations.valueOf(in.readString());
        reset = in.readByte() != 0;
    }

    public DataCalculator() {
        clearAll();
    }

    public void clearAll() {
        result = BigDecimal.ZERO;
        console_expression = new StringBuilder('0');
        reset = true;
        last_operations = Operations.RESULT;
    }

    public void cleanOne() {
        int length = console_expression.length();
        if (length > 1) {
            console_expression.deleteCharAt(length - 1);
        } else {
            console_expression = new StringBuilder('0');
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataCalculator> CREATOR = new Creator<DataCalculator>() {
        @Override
        public DataCalculator createFromParcel(Parcel in) {
            return new DataCalculator(in);
        }

        @Override
        public DataCalculator[] newArray(int size) {
            return new DataCalculator[size];
        }
    };

    public String getConsole_expression() {
        return console_expression.toString();
    }

    public void setConsole_expression(char symbol) {
        if (reset) {
            this.console_expression = new StringBuilder();
            console_expression.append(symbol);
            reset = false;
        } else {
            this.console_expression.append(symbol);
        }
    }

    public void calculate_expression(Operations opr) {

        if (reset) {
            last_operations = opr;
        } else {
            calculate(new BigDecimal(console_expression.toString()));
            last_operations = opr;
            reset = true;
        }
    }

    private void calculate(BigDecimal number){
        switch (last_operations) {
            case PERCENT:break;
            case DIVIDE:result = (number.compareTo(BigDecimal.ZERO) == 0) ? BigDecimal.ZERO :result.divide(number, MathContext.DECIMAL32);break;
            case MULTIPLY:result = result.multiply(number);break;
            case MINUS:result = result.subtract(number);break;
            case PLUS:result = result.add(number);break;
            case RESULT: result = number; break;
        }
        if (result.compareTo(BigDecimal.ZERO) == 0) {
            result = BigDecimal.ZERO;
        }
        console_expression = new StringBuilder(result.toString());

    }

}
