package com.bitballoon.se4351_synapps.synapps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

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
    ArrayList<Notification> notificationArrayList;
    String notificationData = "";
    String notificationArrayListSize = "";
    String date_data = "";

    // add notification button
    LinearLayout addNotificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_list);

        Intent intent = getIntent();
        notificationData = intent.getStringExtra("notificationData");
        notificationArrayListSize = intent.getStringExtra("notificationArrayListSize");
        date_data = intent.getStringExtra("date_sel");

        Log.d("notificationData", notificationData);
        Log.d(notificationArrayListSize, "notificationArrayList");
        setNotificationAdapter();
        goToEditNotification();
    }

    // method sets the adapter for the listview of notifications
    private void setNotificationAdapter() {
        notificationsListView = (ListView) findViewById(R.id.notifications_listview);
        notificationArrayList = new ArrayList<Notification>();

        notificationAdapter = new NotificationAdapter(this, notificationArrayList);
        notificationsListView.setAdapter(notificationAdapter);

        notificationImage = new ImageView(this);
        notificationText = new TextView(this);
        notificationTime = new TextView(this);

        notificationImage.setImageResource(R.mipmap.daily_routine);

        String[] notifications = notificationData.split(";");

        // loop to make list of notifications
        for (int i = 0; i < Integer.parseInt(notificationArrayListSize); i++) {
            // strings for the notification text
            String text = new String(notifications[i].substring(0, notifications[i].indexOf('|')));
            String time = new String(notifications[i].substring(notifications[i].indexOf('|') + 1, notifications[i].length()));

            // sets notification data
            notificationText.setText(text);
            notificationTime.setText(time);
            // creates notification and adds to arraylist
            notificationArrayList.add(i, new Notification(notificationImage, text, time));
            notificationAdapter.notifyDataSetChanged();
            Log.d("text", text);
            Log.d("time", time);
        }
    }

    // go to edit notification screen with notification info filled or go to edit notification screen when clicking add button
    private void goToEditNotification() {
        // when notification in listview is clicked
        notificationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String notificationText = notificationArrayList.get(position).getNotification_text();
                String notificationTime = notificationArrayList.get(position).getActivity_time();

                Intent intent = new Intent(NotificationListActivity.this, EditNotificationActivity.class);
                intent.putExtra("notificationText", notificationText);
                intent.putExtra("notificationTime", notificationTime);
                intent.putExtra("date_data", date_data);
                startActivity(intent);
            }
        });

        // add button goes to edit notification screen
        addNotificationButton = (LinearLayout) findViewById(R.id.add_notification_layout);
        addNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NotificationListActivity.this, EditNotificationActivity.class);
                intent.putExtra("date_data", date_data);
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
