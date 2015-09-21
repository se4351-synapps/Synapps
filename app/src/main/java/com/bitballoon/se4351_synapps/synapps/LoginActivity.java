package com.bitballoon.se4351_synapps.synapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText emailAddress;
    private EditText password;
    private Button loginButton;
    private Button newUserButton;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        selectNewUser();
    }

    public void selectNewUser() {
        newUserButton = (Button)findViewById(R.id.new_user_button);
        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, NewUserSelectionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
