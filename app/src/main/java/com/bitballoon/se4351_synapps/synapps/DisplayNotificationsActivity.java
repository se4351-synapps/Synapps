package com.bitballoon.se4351_synapps.synapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Bontavy on 11/6/2015.
 */
public class DisplayNotificationsActivity extends AppCompatActivity {
    ListView notificationsListView;
    ArrayAdapter<String> notificationAdapter;
    ImageView homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_notifications);
        notificationsListView = (ListView) findViewById(R.id.notifications_listview);

        ArrayList<String> notificationArray = new ArrayList<String>();
        notificationArray.add("note 1");
        notificationArray.add("note 2");
        notificationArray.add("note 3");
        notificationArray.add("note 4");
        notificationArray.add("note 1");
        notificationArray.add("note 2");
        notificationArray.add("note 3");
        notificationArray.add("note 4");
        notificationArray.add("note 1");
        notificationArray.add("note 2");
        notificationArray.add("note 3");
        notificationArray.add("note 4");
        notificationArray.add("note 1");
        notificationArray.add("note 2");
        notificationArray.add("note 3");
        notificationArray.add("note 4");

        notificationAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, notificationArray);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_call:
                // User chose the "Call" item, show the app settings UI...
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
