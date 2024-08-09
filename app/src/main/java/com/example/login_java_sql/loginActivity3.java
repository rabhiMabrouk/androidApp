package com.example.login_java_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.login_java_sql.databinding.ActivityLogin3Binding;
import com.example.login_java_sql.databinding.ActivitySignup2Binding;

public class loginActivity3 extends AppCompatActivity {
    databaseHelper databaseHelper;
    ActivityLogin3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogin3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new databaseHelper(this);
        binding.loginButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.loginEmail.getText().toString();
                String password= binding.loginPass.getText().toString();
                if(email.equals("")||password.equals(""))
                    Toast.makeText(loginActivity3.this, "all field are mandatory", Toast.LENGTH_SHORT).show();
                else {
                    boolean checkemail = databaseHelper.checkemail(email);
                    if(checkemail==false){
                        Toast.makeText(loginActivity3.this, "email not exist.Please signup", Toast.LENGTH_SHORT).show();
                    }else {
                        boolean checkpass = databaseHelper.checkpassword(email,password);
                        if(checkpass==true){
                            Toast.makeText(loginActivity3.this, "login successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(loginActivity3.this, MainActivity.class);
                            loginActivity3.this.startActivity(intent);
                        }else {
                            Toast.makeText(loginActivity3.this, "password not correctly", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        binding.loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity3.this,signupActivity2.class);
                loginActivity3.this.startActivity(intent);
            }
        });
    }
}