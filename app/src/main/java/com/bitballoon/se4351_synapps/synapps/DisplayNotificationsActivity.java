package com.bitballoon.se4351_synapps.synapps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bontavy on 11/6/2015.
 */
public class DisplayNotificationsActivity extends AppCompatActivity {
    ListView notifications;
    ArrayAdapter<String> notificationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_notifications);
        notifications = (ListView) findViewById(R.id.notifications_listview);
    }
}
