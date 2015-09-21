package com.bitballoon.se4351_synapps.synapps;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by brandonquiocho on 9/18/15.
 */
public class NewUserSelectionActivity extends AppCompatActivity {
    private ImageView patient_image;
    private ImageView loved_one_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_icons);
        new_patient_icon_click();
        new_loved_one_icon_click();
    }

    public void new_patient_icon_click() {
        patient_image = (ImageView)findViewById(R.id.patient_icon);
        patient_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewUserSelectionActivity.this, "Ask user to get their loved one", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void new_loved_one_icon_click() {
        loved_one_image = (ImageView)findViewById(R.id.loved_one_icon);
        loved_one_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewUserSelectionActivity.this, "Go to New User Account page", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(NewUserSelectionActivity.this, CreateNewUserActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
