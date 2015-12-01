package com.bitballoon.se4351_synapps.synapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by brandonquiocho on 11/30/15.
 */
public class CalendarActivity extends AppCompatActivity {

    private Button continue_button;
    private Button cancel_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);

        initiateUI();
        continueCalendarBtn();
        cancelCalendarBtn();


    }

    private void initiateUI(){
        continue_button = (Button)findViewById(R.id.continue_calendar_button);
        cancel_button = (Button)findViewById(R.id.cancel_calendar_button);
    }

    private void continueCalendarBtn() {
        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, NotificationListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void cancelCalendarBtn() {
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });
    }


}
