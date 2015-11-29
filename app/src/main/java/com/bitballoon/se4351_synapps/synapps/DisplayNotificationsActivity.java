package com.bitballoon.se4351_synapps.synapps;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
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
    TextView notificationText;
    TextView notificationTime;
    ArrayList<Notification> notificationArray;

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
                intent.putExtra("Notifications", notificationArray);
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

    // methods shows detailed view of notification
    private void showDetailedNotification() {
        // for decreasing the size of notification image in the alertdialog
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        final int DESIRED_WIDTH = (int) display.getWidth() / 2;
        final int DESIRED_HEIGHT = (int) display.getHeight() / 2;

        // when notification in listview is clicked
        notificationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set size of notification image in the alertdialog
                ImageView notificationImage = notificationArray.get(position).getNotification_image();
                notificationImage.setMinimumWidth(DESIRED_WIDTH);
                notificationImage.setMinimumHeight(DESIRED_HEIGHT);
                // add notification info and buttons in the alertdialog
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage(notificationArray.get(position).getNotification_text() +
                        "\n\nComplete Task at: " + notificationArray.get(position).getActivity_time())
                        .setView(notificationImage)
                        .setNegativeButton("Close", null)
                        .setPositiveButton("Confirm Completion", null);
                AlertDialog alertDialog = builder.create();
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
