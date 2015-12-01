package com.bitballoon.se4351_synapps.synapps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Bontavy on 11/6/2015.
 */
public class DisplayNotificationsActivity extends AppCompatActivity {
    // variables relating to listview for notifications
    ListView notificationsListView;
    NotificationAdapter notificationAdapter;
    ImageView notificationImage;
    TextView notificationText;
    TextView notificationTime;
    ArrayList<Notification> notificationArrayList;
    String notificationData = "";

    // home button
    ImageView homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_notifications);

        // set the adapter for the listview for notifications
        setNotificationAdapter();

        // show detailed view of notification
        showDetailedNotification();

        // home button goes to main menu
        homeButton = (ImageView) findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayNotificationsActivity.this, PinActivity.class);
                intent.putExtra("notificationData", notificationData);
                startActivity(intent);
            }
        });
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

        // loop to make list of notifications
        for (int i = 0; i < 5; i++) {
            DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            //get current date time with Calendar()
            Calendar cal = Calendar.getInstance();

            // strings for the notification text
            String text = new String("This is a notification. The notification is number " + (i + 1) + " in this list. Checking to see if the list populates with new data for each item.");
            String time = new String(dateFormat.format(cal.getTime()));
            // sets notification data
            notificationText.setText(text);
            notificationTime.setText(time);
            // creates notification and adds to arraylist
            notificationArrayList.add(i, new Notification(notificationImage, text, time));
            notificationAdapter.notifyDataSetChanged();

            notificationData += text + "," + time + ";";

            Log.d("notificationData", notificationData);
        }

//        notificationText.setText("Notification 1: Because he’s the hero Gotham deserves, but not the one it needs right now. So we’ll hunt him because he can take it. Because he’s not our hero. He’s a silent guardian. A watchful protector. A dark knight.");
//        notificationTime.setText("9:30 PM");
//        notificationArrayList.add(new Notification(notificationImage, notificationText, notificationTime));
//
//        notificationText.setText("Notification 2: You either die a hero or you live long enough to see yourself become the villain.");
//        notificationTime.setText("12:00 AM");
//        notificationArrayList.add(new Notification(notificationImage, notificationText, notificationTime));
    }

    // methods shows detailed view of notification
    private void showDetailedNotification() {
        // for decreasing the size of notification image in the alertdialog
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        final int DESIRED_WIDTH = (int) display.getWidth() / 2;
        final int DESIRED_HEIGHT = (int) display.getHeight() / 2;

        // when notification in listview is clicked
        notificationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            AlertDialog alertDialog;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set size of notification image in the alertdialog
                ImageView notificationImage = notificationArrayList.get(position).getNotification_image();
                notificationImage.setMinimumWidth(DESIRED_WIDTH);
                notificationImage.setMinimumHeight(DESIRED_HEIGHT);
                // add notification info and buttons in the alertdialog
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage(notificationArrayList.get(position).getNotification_text() +
                        "\n\nComplete Task at: " + notificationArrayList.get(position).getActivity_time())
                        .setView(notificationImage)
                        .setNegativeButton("Close",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        alertDialog.dismiss();
                                    }
                                })
                        .setPositiveButton("Confirm Completion",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(DisplayNotificationsActivity.this, "Notification completed", Toast.LENGTH_LONG).show();
                                        alertDialog.dismiss();
                                    }
                                });

                alertDialog = builder.create();
                alertDialog.show();

                // set size of alertdialog to fill screen
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(alertDialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                alertDialog.getWindow().setAttributes(lp);
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
