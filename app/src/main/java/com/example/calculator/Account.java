package com.example.calculator;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * homework com.example.calculator
 *
 * @author Amina
 * 01.06.2021
 */
public class Account implements Parcelable {
    private String name;
    private String surName;
    private String email;
    private int age;

    public Account() {
    }

    public Account(String name, String surname, int age, String email){
        setName(name);
        setSurName(surname);
        setAge(age);
        setEmail(email);
    }

    protected Account(Parcel in) {
        name = in.readString();
        surName = in.readString();
        age = in.readInt();
        email = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(surName);
        dest.writeInt(age);
        dest.writeString(email);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}
