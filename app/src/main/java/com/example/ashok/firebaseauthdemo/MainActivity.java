package com.example.ashok.firebaseauthdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextpasword;
    private TextView textViewSignIn;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!= null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.button2);
        editTextEmail = (EditText) findViewById(R.id.editText2);
        editTextpasword = (EditText) findViewById(R.id.editText3);
        textViewSignIn = (TextView) findViewById(R.id.textbitch);
        buttonRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);

    }
    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextpasword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            //email is email
            Toast.makeText(this, "Please the enter the email bitch", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please enter the password", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            progressDialog.setMessage("Registering User");
            progressDialog.show();
        }
        //progressDialog.setMessage("Registering User");
        //progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        finish();
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    } else{
                        Toast.makeText(MainActivity.this, "Couldnot register I am sorry", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            });
    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            registerUser();
        }
        if (v == textViewSignIn){
            //will open login activity here
            startActivity(new Intent(this, LogInActivity.class));
        }

    }
}
