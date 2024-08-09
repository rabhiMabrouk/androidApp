package com.example.login_java_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.login_java_sql.databinding.ActivitySignup2Binding;

public class signupActivity2 extends AppCompatActivity {
    databaseHelper databaseHelper;
    //كائن يتم إنشاؤه تلقائيًا بواسطة Android Studio بناءً على اسم النشاط
    ActivitySignup2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getLayoutInflater() هي دالة تُرجع مُعالج تضخيم (LayoutInflater)، الذي يستخدم لتحويل ملفات XML إلى كائنات Java. inflate() هو طريقة تضخيم مُستخدمة لإنشاء كائنات مرئية (View) من تخطيط واجهة المستخدم XML.
        binding = ActivitySignup2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new databaseHelper(this);
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmpass = binding.signupConfirm.getText().toString();

                if(email.equals("")||password.equals("")||confirmpass.equals(""))
                    Toast.makeText(signupActivity2.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else {
                    if(password.equals(confirmpass)){
                        boolean checkemail = databaseHelper.checkemail(email);
                        if (checkemail==false) {
                            boolean insert = databaseHelper.insertData(email,password);
                            if(insert==true){
                                Toast.makeText(signupActivity2.this, "signup successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signupActivity2.this,loginActivity3.class);
                                signupActivity2.this.startActivity(intent);
                            }else
                                Toast.makeText(signupActivity2.this, "signup fialed", Toast.LENGTH_SHORT).show();
                        }else Toast.makeText(signupActivity2.this, "user alredy exist. Pleas login", Toast.LENGTH_SHORT).show();

                    }else Toast.makeText(signupActivity2.this, "Invalid password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.loginRedirecText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signupActivity2.this,loginActivity3.class);
                signupActivity2.this.startActivity(intent);
            }
        });
    }

}
//مفهوم Data Binding:
//
//Data Binding هي مكتبة تسمح بربط بيانات التطبيق مباشرة بعناصر واجهة المستخدم (UI) في XML، مما يتيح الوصول المباشر إلى عناصر الواجهة دون الحاجة إلى استخدام findViewById.
//يتم إنشاء كائنات خاصة بكل نشاط (Activity) بناءً على اسم النشاط، مثل ActivitySignup2Binding لـ ActivitySignup2.
//binding.getRoot():
//
//binding هو كائن من النوع ActivitySignup2Binding الذي يتم إنشاؤه باستخدام Data Binding.
//getRoot() هو طريقة تُستخدم لاسترجاع العنصر الجذري لتخطيط الواجهة المعرف في XML. في كثير من الأحيان، يكون هذا الجذر هو LinearLayout أو ConstraintLayout أو أي عنصر أخر يحتوي على باقي عناصر الواجهة.
//setContentView(...):
//
//setContentView() هي دالة في النشاط (Activity) تُستخدم لتعيين تخطيط الواجهة كمحتوى رئيسي للنشاط.
//باستخدام setContentView(binding.getRoot())، أنت تُعين العنصر الجذري لتخطيط الواجهة (الذي تم استرجاعه باستخدام getRoot()) كمحتوى رئيسي لنشاطك.