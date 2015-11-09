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
    ListView notificationsListView;
    ArrayAdapter<Notification> notificationAdapter;
    ImageView homeButton;

    ImageView notificationImage;
    String notificationText;
    String notificationTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_notifications);

        notificationsListView = (ListView) findViewById(R.id.notifications_listview);

        notificationImage = (ImageView) findViewById(R.id.notification_image);

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC-06:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("KK:mm");
        date.setTimeZone(TimeZone.getTimeZone("UTC-06:00"));
        String localTime = date.format(currentLocalTime);

        notificationTime = localTime;
        Notification notification1 = new Notification(notificationImage, "notification 1", notificationTime);
        Notification notification2 = new Notification(notificationImage, "notification 2", notificationTime);
        ArrayList<Notification> notificationArray = new ArrayList<Notification>();
        notificationArray.add(notification1);
        notificationArray.add(notification2);

        notificationAdapter = new ArrayAdapter<Notification>(this, android.R.layout.simple_list_item_1, notificationArray);
        notificationsListView.setAdapter(notificationAdapter);

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
                // User chose the "Call" item, show the app settings UI...
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
