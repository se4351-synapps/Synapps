package com.bitballoon.se4351_synapps.synapps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Bontavy on 9/25/2015.
 */
public class MainMenuActivity extends AppCompatActivity {

    private ImageView daily_routine_icon;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

    }

    public void new_loved_one_icon_click() {
        daily_routine_icon = (ImageView)findViewById(R.id.daily_routine_icon);
        daily_routine_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainMenuActivity.this, EditNotificationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
