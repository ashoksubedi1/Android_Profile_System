package com.example.ashok.firebaseauthdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        editTextEmail = (EditText) findViewById(R.id.editTextemail);
        editTextPassword = (EditText) findViewById(R.id.editTextpassword);
        textViewSignup = (TextView) findViewById(R.id.textViewSignUp);
        buttonSignIn = (Button) findViewById(R.id.buttonSignin);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = firebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!= null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }


    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
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
        else{
            progressDialog.setMessage("Registering User please wait...");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful()){
                        finish();
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    }
                }
            });
        }
    }
    @Override
    public void onClick(View v) {
       if (v == buttonSignIn){
           userLogin();
       }
       if (v == textViewSignup){
           finish();
           startActivity(new Intent(this, MainActivity.class) );
       }
    }
}
