package com.bitballoon.se4351_synapps.synapps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import static  com.bitballoon.se4351_synapps.synapps.CreatePinActivity.*;
import static com.bitballoon.se4351_synapps.synapps.CreatePinActivity.KEY_PIN;



/**
 * Created by Bontavy on 11/14/2015.
 */
public class PinActivity extends AppCompatActivity {

    private EditText pinNumber;
    private Button pinButton;
    private Button newPinButton;
    private Button resumeAppButton;
    private String activePin="";

    String notificationData = "";
    String notificationArrayListSize = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_pin);

        Intent intent = getIntent();
        notificationData = intent.getStringExtra("notificationData");
        notificationArrayListSize = intent.getStringExtra("notificationArrayListSize");
        Log.d("notificationData", notificationData);
        Log.d(notificationArrayListSize, "notificationArrayList");
        initializeUI();
        resumeApp();
        logIn();
        makePin();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initializeUI() {
        //activePin = @string/pin_actual;

        pinNumber = (EditText)findViewById(R.id.pin_number);
        newPinButton = (Button)findViewById(R.id.new_pin_button);
        pinButton = (Button)findViewById(R.id.pin_button);
        resumeAppButton = (Button)findViewById(R.id.resume_to_app);
    }

    private void resumeApp() {
        resumeAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinActivity.this, MainMenuActivity.class);
                intent.putExtra("notificationData", notificationData);
                intent.putExtra("notificationArrayListSize", notificationArrayListSize);
                startActivity(intent);
                finish();
            }
        });
    }

    private void logIn() {
        pinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (pinNumber.getText().toString().equals("1514")) {
//
//                    Intent intent = new Intent(PinActivity.this, MainMenuActivity.class);
//                    startActivity(intent);
//                    finish();
//
//                 }else{
//                    Toast.makeText(PinActivity.this, R.string.invalid_pin, Toast.LENGTH_LONG).show();
//                    pinNumber.setText("");
//                }
                Intent rp = getIntent();
                activePin= rp.getStringExtra(KEY_PIN);
                if (pinNumber.getText().toString().equals(activePin)) {
                    Intent intent = new Intent(PinActivity.this, MainMenuActivity.class);
                    intent.putExtra("notificationData", notificationData);
                    intent.putExtra("notificationArrayListSize", notificationArrayListSize);
                    startActivity(intent);
                    finish();
                } else {
                    pinNumber.setText("");
                    Toast.makeText(PinActivity.this, R.string.invalid_pin, Toast.LENGTH_LONG).show();
                }
            }


        });
    }
    private void makePin() {
        newPinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinActivity.this, CreatePinActivity.class);
                intent.putExtra("notificationData", notificationData);
                intent.putExtra("notificationArrayListSize", notificationArrayListSize);
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
                callIntent.setData(Uri.parse("tel:5055034455"));
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
