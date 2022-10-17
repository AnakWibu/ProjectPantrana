package com.example.pantranafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pantranafinal.UserDatabase.User;
import com.example.pantranafinal.UserDatabase.UserHelper;

public class Login extends AppCompatActivity {

    TextView register;
    EditText edtEmailAddress, edtPassword;
    Button btnLogin;
    UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        btnLogin.setOnClickListener(v -> {

            String emailTxt = edtEmailAddress.getText().toString();
            String passwordTxt = edtPassword.getText().toString();

            User user = userHelper.auth(emailTxt, passwordTxt);

            if (user != null) {

                Intent intent = new Intent(this, MainMenu.class);
                Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show();
                startActivity(intent);
                finishAffinity();
            } else {

                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            }

        });

        register.setOnClickListener(v -> {

            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        });
    }

    private void init() {

        edtEmailAddress = findViewById(R.id.loginEmail);
        edtPassword = findViewById(R.id.login_password);
        register =findViewById(R.id.tvregister);
        btnLogin = findViewById(R.id.btnlogin);
        userHelper = new UserHelper(this);
    }
}