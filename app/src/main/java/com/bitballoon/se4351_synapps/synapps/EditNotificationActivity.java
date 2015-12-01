package com.bitballoon.se4351_synapps.synapps;

/**
 * Created by brandonquiocho on 11/6/15.
 */

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.Calendar;
import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.CheckBox;
import android.widget.Toast;

public class EditNotificationActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    private TextView mTimeDisplay;
    private Button mPickTime;
    private TextView mDateLabel;

    private int mHour;
    private int mMinute;
    private String mAMPM;

    static final int TIME_DIALOG_ID = 0;

    final Context context = this;

    private Button saveButton;
    private Button cancelButton;

    EditText noti_input;

    String date_data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_notification);

        initiateUI();
        saveNotificationBtn();
        cancelNotificationBtn();

        // notification input variable
        noti_input = (EditText)findViewById(R.id.noti_field_input);

        // capture our View elements
        mTimeDisplay = (TextView) findViewById(R.id.timeDisplay);
        mPickTime = (Button) findViewById(R.id.pickTime);
        mDateLabel = (TextView) findViewById(R.id.date_field);

        // add a click listener to the button
        mPickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });

        // get the current time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // display the current date
        updateDisplay();

        // get the notification text and time from the NotificationListActivity
        Intent intent = getIntent();
        noti_input.setText(intent.getStringExtra("notificationText"));
        mTimeDisplay.setText(intent.getStringExtra("notificationTime"));
        // date from calendar
        date_data = intent.getStringExtra("date_data");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mDateLabel.setText(date_data);
    }

    private void initiateUI(){
        saveButton = (Button)findViewById(R.id.save_button);
        cancelButton = (Button)findViewById(R.id.cancel_button);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, mHour, mMinute, false);
        }
        return null;
    }

    public void onCheckBoxClick(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.weekday_btn:
                if (checked) {
                    print_message("Weekday Btn");
                }
                else {
                    // Remove the meat
                }
                break;
            case R.id.weekend_btn:
                if (checked) {
                    print_message("Weekend Btn");
                }
                else {
                    // I'm lactose intolerant
                }
                break;
            // TODO: Veggie sandwich
        }
    }

    private void saveNotificationBtn() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //print_message("Notification Created");
                convert_time();
                String hour = pad(mHour);
                String min = pad(mMinute);
                print_notification(noti_input.getText().toString(), hour, min);
                showNotification(noti_input.getText().toString(), hour, min, mAMPM, date_data);
                //Toast.makeText(EditNotificationActivity.this, "Notification Created", Toast.LENGTH_LONG);

            }
        });
    }

    public void convert_time(){

        if (mHour >= 12) {
            mAMPM = "PM";
            if(mHour != 12){
                mHour = mHour - 12;
            }
        }
        else{
            if(mHour == 0){
                mHour = 12;
            }
            mAMPM = "AM";
        }
    }

    public void showNotification(String noti, String hour, String min, String ampm, String date) {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, DisplayNotificationsActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Notification")
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle("Date: " + date)
                .setContentText( hour + ":" + min + " " + ampm + " - " + noti)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

    private void cancelNotificationBtn() {
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //print_message("Canceled Notification ._.");
                finish();
                //Toast.makeText(EditNotificationActivity.this, "Notification Created", Toast.LENGTH_LONG);

            }
        });
    }

    public void print_notification(String str, String hour, String min) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle(date_data);

        // set dialog message
        alertDialogBuilder
                .setMessage(str + " at " + hour + ":" +  min + " " + mAMPM + " added.")
                .setCancelable(false)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
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


    // updates the time we display in the TextView
    private void updateDisplay() {
        convert_time();
        mTimeDisplay.setText(
                new StringBuilder()
                        .append(pad(mHour)).append(":")
                        .append(pad(mMinute)).append(" ")
                        .append(mAMPM));
    }

    // the callback received when the user "sets" the time in the dialog
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mHour = hourOfDay;
                    mMinute = minute;
                    updateDisplay();
                }
            };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
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