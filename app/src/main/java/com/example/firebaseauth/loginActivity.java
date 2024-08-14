package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {
    EditText inputEmail,inputPass;
    Button btnLog,btnG,btnF;
    TextView txtLog;
    ProgressDialog progressDlog;
    FirebaseAuth myAuth;
    FirebaseUser myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtLog = findViewById(R.id.txtLog);
        btnLog = findViewById(R.id.btnLog);
        btnG = findViewById(R.id.btnG);
        btnF = findViewById(R.id.btnF);
        inputEmail = findViewById(R.id.inputEmailLog);
        inputPass = findViewById(R.id.inputPasswordLog);
        myAuth = FirebaseAuth.getInstance();
        myUser = myAuth.getCurrentUser();


        btnLog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPass.getText().toString();
                CheckInformationLogin(email,password);

            }
        });
        txtLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this,RegisterActivity.class);
                loginActivity.this.startActivity(intent);
                finish();
            }
        });
    }

    private void CheckInformationLogin(String email,String password) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if(!email.matches(emailRegex))
            errorMsg(inputEmail,"Invalid email Address");
        else if((password.length()<7)) {
            errorMsg(inputPass,"At least 8 characters");
        }else {
            Toast.makeText(this, "Call Method login", Toast.LENGTH_SHORT).show();
            checkLoginFirebase(email,password);
        }

    }

    private void checkLoginFirebase(String email,String password) {
        progressDlog = new ProgressDialog(this);
        progressDlog.setTitle("Login");
        progressDlog.setMessage("Please wait..");
        progressDlog.setCanceledOnTouchOutside(false);
        progressDlog.show();
        myAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDlog.dismiss();
                    sendUserNextActivity();
                    Toast.makeText(loginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                }else {
                    progressDlog.dismiss();
                    Toast.makeText(loginActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendUserNextActivity() {
        Intent intent = new Intent(loginActivity.this,MainActivity.class);
        loginActivity.this.startActivity(intent);
        finish();
    }

    private void errorMsg(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}