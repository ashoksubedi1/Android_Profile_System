package com.example.ashok.firebaseauthdemo;

import android.widget.EditText;

/**
 * Created by ashok on 4/8/2017.
 */

public class UserInformation {
    public String first_name;
    public String last_name;
    public String major;
    //public String phone;

    public UserInformation(){

    }


    public UserInformation(String first_name, String last_name, String major) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.major = major;
    }
}
