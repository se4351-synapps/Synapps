package com.bitballoon.se4351_synapps.synapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText emailAddress;
    private EditText password;
    private Button loginButton;
    private Button newUserButton;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); //hellobitches
        initializeUI();
        login();
        selectNewUser();
    }

    // initialize UI elements
    private void initializeUI() {
        emailAddress = (EditText)findViewById(R.id.email_address);
        password = (EditText)findViewById((R.id.password));
        loginButton = (Button)findViewById(R.id.login_button);
        newUserButton = (Button)findViewById(R.id.new_user_button);
        forgotPassword = (TextView)findViewById(R.id.forgot_password);
    }

    // login to the app if the login credentials are correct
    private void login() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(emailAddress.getText().toString().equals("homie@synapps.com") && password.getText().toString().equals("synapps"))) {
                    Toast.makeText(LoginActivity.this, R.string.invalid_login, Toast.LENGTH_LONG).show();
                    emailAddress.setText("");
                    password.setText("");
                } else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
    // Go to the New User Selection screen
    private void selectNewUser() {
        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, NewUserSelectionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

//oops
}
