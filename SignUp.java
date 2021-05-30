package com.example.arads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //Variables
    TextInputLayout regName, regUsername, regEmail, regPhoneNum, regPassword;
    Button regSignup, toLoginScreen;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks to all activities in activity_sign_up.xml
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNum = findViewById(R.id.reg_phoneNum);
        regPassword = findViewById(R.id.reg_password);
        regSignup = findViewById(R.id.reg_signup);
        toLoginScreen = findViewById(R.id.to_login_screen);

        //Save data in firebase on completing registration
        toLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                //Get all the values
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNum = regPhoneNum.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass();

                reference.child(phoneNum).setValue(helperClass);
                Toast.makeText(SignUp.this, "Firebase connection successful", Toast.LENGTH_LONG).show();
            }
        }); //Register Button method end
    } //On create method end

    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val1 = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val1.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val1.length() >= 15) {
            regUsername.setError("Username too long");
            return false;
        } else if (!val1.matches(noWhiteSpace)) {
            regUsername.setError("No white spaces are allowed");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val2 = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val2.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val2.matches(emailPattern)) {
            regEmail.setError("Invalid Email Address");
            return false;
        } else {
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNum() {
        String val3 = regPhoneNum.getEditText().getText().toString();

        if (val3.isEmpty()) {
            regPhoneNum.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNum.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val4 = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val4.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val4.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }


    //Save data in firebase on button click
    public void registerUser(View view) {

        if(!validateName() | !validatePassword() | !validatePhoneNum() | !validateEmail() | !validateUsername()){
            return;
        }

        //Get all the values in strings
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNum = regPhoneNum.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
        UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNum, password);
        reference.child(username).setValue(helperClass);
    }
}