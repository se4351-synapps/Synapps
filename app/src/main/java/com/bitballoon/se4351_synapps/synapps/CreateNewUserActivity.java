package com.bitballoon.se4351_synapps.synapps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Bontavy on 9/18/2015.
 */
public class CreateNewUserActivity extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText emailAddress;
    private EditText password;
    private EditText confirmPassword;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_user);
    }
}
