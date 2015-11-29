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
import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Karis on 11/27/2015.
 */
public class CreatePinActivity extends AppCompatActivity {
    private static EditText newpinNumber ;
    public  static final String KEY_PIN="pin number";
    private static String pinNum;
    private Button makepinButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_pin);
        initializeUI();
        makePin();
        getPin();
    }

    private void initializeUI() {
        newpinNumber = (EditText)findViewById(R.id.pin_number);
        pinNum = newpinNumber.getText().toString();
        makepinButton = (Button)findViewById(R.id.pin_button);
    }

    private void makePin() {
        makepinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pn = new Intent(CreatePinActivity.this, PinActivity.class);
                pn.putExtra(KEY_PIN, pinNum);
                startActivity(pn);
//                Intent intent = new Intent(CreatePinActivity.this, PinActivity.class);
//                startActivity(intent);
                finish();
            }
        });
    }

    public static String getPin(){
        return pinNum;
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
