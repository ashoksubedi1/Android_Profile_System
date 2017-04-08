package com.example.ashok.firebaseauthdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private DatabaseReference databaseReference;
    private EditText first_Name;
    private EditText last_Name;
    private EditText major;
    //private EditText phone;
    private Button buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogOut);

        firebaseAuth = FirebaseAuth.getInstance();
        if( firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LogInActivity.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        first_Name = (EditText) findViewById(R.id.FirstId);
        last_Name = (EditText) findViewById(R.id.LastId);
        major = (EditText) findViewById(R.id.MajorId);
        //phone = (EditText) findViewById(R.id.PhoneId);
        buttonSave = (Button)findViewById(R.id.buttonSave);



        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail.setText("Welcome "+user.getEmail());
        buttonLogout.setOnClickListener(this);
        buttonSave.setOnClickListener(this);


    }
    private void saveUserInformation(){
        String name1 = first_Name.getText().toString().trim();
        String name2 = last_Name.getText().toString().trim();
        String major1 = major.getText().toString().trim();
        //EditText phone1 = phone;

        UserInformation userInformation = new UserInformation(name1,name2, major1);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this,"Information Saved...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LogInActivity.class));
        }
        if(v == buttonSave){
            saveUserInformation();
        }
    }
}
