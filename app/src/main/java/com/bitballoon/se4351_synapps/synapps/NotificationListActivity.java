package com.bitballoon.se4351_synapps.synapps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Bontavy on 11/27/2015.
 */
public class NotificationListActivity extends AppCompatActivity {
    // variables relating to listview for notifications
    ListView notificationsListView;
    NotificationAdapter notificationAdapter;
    ImageView notificationImage;
    TextView notificationText;
    TextView notificationTime;
    ArrayList<Notification> notificationArray;

    // add notification button
    ImageView addNotificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_list);

        notificationArray = (ArrayList<Notification>) getIntent().getSerializableExtra("Notifications");

        setNotificationAdapter();

        // home button goes to main menu
        addNotificationButton = (ImageView) findViewById(R.id.add_notification_button);
        addNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NotificationListActivity.this, EditNotificationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // method sets the adapter for the listview of notifications
    private void setNotificationAdapter() {
        notificationsListView = (ListView) findViewById(R.id.notifications_listview);
        notificationArray = new ArrayList<Notification>();

        notificationAdapter = new NotificationAdapter(this, notificationArray);
        notificationsListView.setAdapter(notificationAdapter);

        notificationText = new TextView(this);
        notificationTime = new TextView(this);

        // loop to make list of notifications
        for (int i = 0; i < notificationArray.size(); i++) {
            notificationImage = notificationArray.get(i).getNotification_image();
            notificationText.setText(notificationArray.get(i).getNotification_text());
            notificationTime.setText(notificationArray.get(i).getActivity_time());
            // creates notification and adds to arraylist
            Notification notification = new Notification(notificationImage, notificationText, notificationTime);
            notificationArray.add(notification);
        }
        notificationAdapter.notifyDataSetChanged();
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
