package com.bitballoon.se4351_synapps.synapps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Bontavy on 11/14/2015.
 */
public class PinActivity extends AppCompatActivity {

    private EditText pinNumber;
    private Button pinButton;
    private Button newPinButton;
    private Button resumeAppButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_pin);
        initializeUI();
        resumeApp();
    }

    private void initializeUI() {
        newPinButton = (Button)findViewById(R.id.new_pin_button);
        pinButton = (Button)findViewById(R.id.pin_button);
        resumeAppButton = (Button)findViewById(R.id.resume_to_app);

    }

    private void resumeApp() {
        resumeAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinActivity.this, MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.emergency_call, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) throws SecurityException{
        switch (item.getItemId()) {
            case R.id.action_call:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:2816850685"));
                startActivity(callIntent);
                // User chose the "Call" item, call caretaker
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



}
