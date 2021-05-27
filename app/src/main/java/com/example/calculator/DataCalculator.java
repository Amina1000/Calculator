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
    private StringBuilder consoleExpression;
    Operations lastOperations;

    private boolean reset;

    protected DataCalculator(Parcel in) {
        consoleExpression = new StringBuilder(in.readString());
        result = new BigDecimal(in.readString());
        lastOperations = Operations.valueOf(in.readString());
        reset = in.readByte() != 0;
    }

    public DataCalculator() {
        clearAll();
    }

    public void clearAll() {
        result = BigDecimal.ZERO;
        consoleExpression = new StringBuilder('0');
        reset = true;
        lastOperations = Operations.RESULT;
    }

    public void cleanOne() {
        int length = consoleExpression.length();
        if (length > 1) {
            consoleExpression.deleteCharAt(length - 1);
        } else {
            consoleExpression = new StringBuilder('0');
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(consoleExpression.toString());
        dest.writeString(result.toString());
        dest.writeString(lastOperations.toString());
        dest.writeByte((byte) (reset ? 0 : 1));
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

    public String getConsoleExpression() {
        return consoleExpression.toString();
    }

    public void setConsoleExpression(char symbol) {
        if (reset) {
            this.consoleExpression = new StringBuilder();
            consoleExpression.append(symbol);
            reset = false;
        } else {
            this.consoleExpression.append(symbol);
        }
    }

    public void calculate_expression(Operations opr) {

        if (reset) {
            lastOperations = opr;
        } else {
            calculate(new BigDecimal(consoleExpression.toString()));
            lastOperations = opr;
            reset = true;
        }
    }

    private void calculate(BigDecimal number){
        switch (lastOperations) {
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
        consoleExpression = new StringBuilder(result.toString());

    }

}
