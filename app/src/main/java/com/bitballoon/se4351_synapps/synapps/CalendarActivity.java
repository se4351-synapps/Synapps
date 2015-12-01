package com.bitballoon.se4351_synapps.synapps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

/**
 * Created by brandonquiocho on 11/30/15.
 */
public class CalendarActivity extends AppCompatActivity {

    private Button continue_button;
    private Button cancel_button;
    private Button get_button;

    DatePicker date_picker;

    final Context context = this;

    String notificationData = "";
    String date_sel = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);

        Intent intent = getIntent();
        notificationData = intent.getStringExtra("notificationData");

        initiateUI();
        continueCalendarBtn();
        cancelCalendarBtn();
        getCalendarBtn();

    }

    private void initiateUI(){
        continue_button = (Button)findViewById(R.id.continue_calendar_button);
        cancel_button = (Button)findViewById(R.id.cancel_calendar_button);
        get_button = (Button)findViewById(R.id.get_calendar_button);
        date_picker = (DatePicker) findViewById(R.id.datePicker);
    }

    private void continueCalendarBtn() {
        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day  = date_picker.getDayOfMonth();
                int month = date_picker.getMonth() + 1;
                int year = date_picker.getYear();
                date_sel = month + "/" + day + "/" + year;

                //print_message("date_sel: " + date_sel);

                Intent intent = new Intent(CalendarActivity.this, NotificationListActivity.class);
                intent.putExtra("notificationData", notificationData);
                intent.putExtra("date_sel", date_sel);
                startActivity(intent);
            }
        });
    }

    private void cancelCalendarBtn() {
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getCalendarBtn() {
        get_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int   day  = date_picker.getDayOfMonth();
//                int   month= date_picker.getMonth() + 1;
//                int   year = date_picker.getYear();
//                date_sel = month + " " + day + " " + year;
                print_message("Temporary ^-^ ");

            }
        });
    }

    public void print_message(String str) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("Print Message Called ^-^");

        // set dialog message
        alertDialogBuilder
                .setMessage(str)
                .setCancelable(false)
                .setPositiveButton("Okay",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, open CreateNewUserActivity
                        dialog.cancel();
                    }
                });
//                .setNegativeButton("No",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//                        // if this button is clicked, prompt the user
//                        // with the same question
//                        dialog.cancel();
//                    }
//                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
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
