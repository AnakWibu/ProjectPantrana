package com.example.pantranafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pantranafinal.UserDatabase.User;
import com.example.pantranafinal.UserDatabase.UserHelper;

public class Register extends AppCompatActivity {

    EditText name, password, conPassword, email, city, country;
    Button btnRegister;
    UserHelper userHelper;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        btnRegister.setOnClickListener(v -> {

            if( !validateEmail() | !valName() | !valCity() | !valCountry() | !valPass() | !valConPass() |!valCheckBox()){
                return;
            }
            String nameTxt = name.getText().toString();
            String passwordTxt = password.getText().toString();
            String emailTxt = email.getText().toString();
            String cityTxt = city.getText().toString();
            String countryTxt = country.getText().toString();

            User user = new User(nameTxt, emailTxt, cityTxt, countryTxt, passwordTxt);
            userHelper.insert(user);

            Toast.makeText(this, "Register Success", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finishAffinity();
        });
    }

    private void init(){

        name = findViewById(R.id.etname);
        password = findViewById(R.id.etpassword);
        conPassword = findViewById(R.id.etcnfrmpw);
        email = findViewById(R.id.etemail);
        city = findViewById(R.id.etcity);
        country = findViewById(R.id.etcountry);
        btnRegister = findViewById(R.id.btnregister);
        userHelper = new UserHelper(this);
        checkBox = findViewById(R.id.cbcond);
    }

    private Boolean validateEmail(){

        userHelper = new UserHelper(this);
        String emailAdd = email.getText().toString();

        User user =userHelper.valEmail(emailAdd);
        if(user!=null){
            if (emailAdd.isEmpty()){

                email.setError("Field cannot be empty");
                return false;
            } else if (!emailAdd.endsWith(".com")) {

                email.setError("email address must end with ‘.com’.");
                return false;
            } else if(emailAdd.equals(user.getEmail())){

                email.setError("Email already used.");
                return false;
            } else {

                email.setError(null);
                return true;
            }
        }
        else{
            if (emailAdd.isEmpty()){

                email.setError("Field cannot be empty");
                return false;
            } else if (!emailAdd.endsWith(".com")) {

                email.setError("email address must end with ‘.com’.");
                return false;

            } else {
                email.setError(null);
                return true;
            }
        }
    }

    private Boolean valName(){
        String user = name.getText().toString();
        if (user.isEmpty()){

            name.setError("Field cannot be empty");
            return false;
        } else {

            name.setError(null);
            return true;
        }
    }

    private Boolean valCity(){
        String City = city.getText().toString();
        if (City.isEmpty()){

            city.setError("Field cannot be empty");
            return false;
        } else {

            city.setError(null);
            return true;
        }
    }

    private Boolean valCountry(){
        String Country = country.getText().toString();
        if (Country.isEmpty()){

            country.setError("Field cannot be empty");
            return false;
        } else {

            country.setError(null);
            return true;
        }
    }

    private Boolean valPass(){
        String Pass = password.getText().toString();
        if (Pass.isEmpty()){

            password.setError("Field cannot be empty");
            return false;
        } else {

            country.setError(null);
            return true;
        }
    }

    private Boolean valConPass(){
        String conPass = conPassword.getText().toString();
        String Pass = password.getText().toString();
        if (conPass.isEmpty()){

            conPassword.setError("Field cannot be empty");
            return false;
        } else if(!conPass.equals(Pass)) {

            conPassword.setError("Password didn't match..");
            return false;
        } else {

            conPassword.setError(null);
            return true;
        }
    }

    private  Boolean valCheckBox(){
        if (!checkBox.isChecked()){
            checkBox.setError("Must be chacked.");
            return false;
        } else {
            return true;
        }
    }
}