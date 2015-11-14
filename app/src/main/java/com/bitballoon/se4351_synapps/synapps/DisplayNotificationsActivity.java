package com.bitballoon.se4351_synapps.synapps;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Bontavy on 11/6/2015.
 */
public class DisplayNotificationsActivity extends AppCompatActivity {
    // variables relating to listview for notifications
    ListView notificationsListView;
    NotificationAdapter notificationAdapter;
    ImageView notificationImage;
    String notificationText;
    String notificationTime;
    ArrayList<Notification> notificationArray;

    // home button
    ImageView homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_notifications);

        // set the adapter for the listview for notifications
        setNotificationAdapter();

        // home button goes to main menu
        homeButton = (ImageView) findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DisplayNotificationsActivity.this, MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // method sets the adapter for the listview of notifications
    private void setNotificationAdapter() {
        notificationsListView = (ListView) findViewById(R.id.notifications_listview);
        notificationArray = new ArrayList<Notification>();
        notificationImage = (ImageView) findViewById(R.id.notification_image);

        for (int i = 0; i < 50; i++) {
            String text = "This is Notification " + (i+1) + " shown on two lines";
            String time = "12:00 PM";
            notificationText = text;
            notificationTime = time;
            Notification notification = new Notification(notificationImage, notificationText, notificationTime);
            notificationArray.add(notification);
        }

        notificationAdapter = new NotificationAdapter(this, notificationArray);
        notificationsListView.setAdapter(notificationAdapter);
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
