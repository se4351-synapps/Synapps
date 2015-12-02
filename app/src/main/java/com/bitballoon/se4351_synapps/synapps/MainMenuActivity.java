package com.bitballoon.se4351_synapps.synapps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * Created by Bontavy on 9/25/2015.
 */
public class MainMenuActivity extends AppCompatActivity {

    private ImageView daily_routine_icon;
    private ImageView back_icon;
    final Context context = this;
    ArrayList<Notification> notificationArray;
    String notificationData = "";
    String notificationArrayListSize = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Intent intent = getIntent();
        notificationData = intent.getStringExtra("notificationData");
        notificationArrayListSize = intent.getStringExtra("notificationArrayListSize");

        Log.d("notificationData", notificationData);
        Log.d(notificationArrayListSize, "notificationArrayList");

        daily_routine_icon_click();
        back_icon_click();

    }

    public void daily_routine_icon_click() {
        daily_routine_icon = (ImageView) findViewById(R.id.daily_routine_icon);
        daily_routine_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainMenuActivity.this, CalendarActivity.class);
                intent.putExtra("notificationData", notificationData);
                intent.putExtra("notificationArrayListSize", notificationArrayListSize);
                startActivity(intent);
            }
        });
    }

    public void back_icon_click() {
        back_icon = (ImageView) findViewById(R.id.back_icon);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainMenuActivity.this, DisplayNotificationsActivity.class);
                intent.putExtra("notificationData", notificationData);
                intent.putExtra("notificationArrayListSize", notificationArrayListSize);
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
    public boolean onOptionsItemSelected(MenuItem item) throws SecurityException {
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

