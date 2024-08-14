package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText inputN,inputE,inputP,inputP2;
    Button btnR,btnG,btn,btnF;
    TextView txtS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtS = findViewById(R.id.txtSign);
        inputN = findViewById(R.id.inputNameS);
        inputE = findViewById(R.id.inputEmailS);
        inputP = findViewById(R.id.inputPasswordS);
        inputP2 = findViewById(R.id.inputPassword2S);
        btnR = findViewById(R.id.btnRegist);
        txtS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,loginActivity.class);
                RegisterActivity.this.startActivity(intent);
                finish();
            }
        });
        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputN.getText().toString();
                String email = inputE.getText().toString();
                String pass = inputP.getText().toString();
                String pass2 = inputP2.getText().toString();
                CheckInformationR(name,email,pass,pass2);
            }
        });
    }

    private void CheckInformationR(String name, String email, String pass, String pass2) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (name.isEmpty() || name.length() < 2 || name.length() > 50 || !name.matches("[a-zA-Zأ-ي ]+")) {
            errorMesseg(inputN,"only alphabetic between 2 and 50");
        } else if (!email.matches(emailRegex) ) {
            inputN.setError(null);
            errorMesseg(inputE,"Invalid email Address.");
        } else if (!(pass.length()>7)) {
            errorMesseg(inputP,"At least 8 characters");
        }else if(!pass.equals(pass2))
            errorMesseg(inputP2,"bassword does not mach");
        else Toast.makeText(this, "Call Register Methods", Toast.LENGTH_SHORT).show();
    }

    private void errorMesseg(EditText input,String s) {
        input.setError(s);
        input.requestFocus();
    }
}