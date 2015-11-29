package com.bitballoon.se4351_synapps.synapps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    LinearLayout addNotificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_list);

        setNotificationAdapter();
        goToEditNotification();
    }

    // method sets the adapter for the listview of notifications
    private void setNotificationAdapter() {
        notificationsListView = (ListView) findViewById(R.id.notifications_listview);
        notificationArray = new ArrayList<Notification>();

        notificationAdapter = new NotificationAdapter(this, notificationArray);
        notificationsListView.setAdapter(notificationAdapter);

        notificationImage = new ImageView(this);
        notificationText = new TextView(this);
        notificationTime = new TextView(this);

        // loop to make list of notifications
        for (int i = 0; i < 5; i++) {
            DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            //get current date time with Calendar()
            Calendar cal = Calendar.getInstance();

            // strings for the notification text
            //String text = "Feed your cat. The food is called Fancy Feast which you can find in the kitchen in the cabinet to the right of the stove.";
            //String time = dateFormat.format(cal.getTime());
            // sets notification data
            notificationImage.setImageResource(R.mipmap.daily_routine);
            notificationText.setText("This is a notification. The notification is number " + (i + 1) + " in this list. Checking to see if the list populates with new data for each item.");
            notificationTime.setText((int) (1 + Math.random() * 12) + ":" + (int) (Math.random() * 59) + " PM");
            // creates notification and adds to arraylist
            notificationArray.add(new Notification(notificationImage, notificationText, notificationTime));
            notificationAdapter.notifyDataSetChanged();
        }
    }

    // go to edit notification screen with notification info filled or go to edit notification screen when clicking add button
    private void goToEditNotification() {
        // when notification in listview is clicked
        notificationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String notificationText = notificationArray.get(position).getNotification_text();
                String notificationTime = notificationArray.get(position).getActivity_time();

                Intent intent = new Intent(NotificationListActivity.this, EditNotificationActivity.class);
                intent.putExtra("notificationText", notificationText);
                intent.putExtra("notificationTime", notificationTime);
                startActivity(intent);
            }
        });

        // add button goes to edit notification screen
        addNotificationButton = (LinearLayout) findViewById(R.id.add_notification_layout);
        addNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NotificationListActivity.this, EditNotificationActivity.class);
                startActivity(intent);
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
