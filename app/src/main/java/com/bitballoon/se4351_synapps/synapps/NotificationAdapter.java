package com.bitballoon.se4351_synapps.synapps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bontavy on 11/8/2015.
 */
public class NotificationAdapter extends ArrayAdapter<Notification> {
    private Context context;
    private ArrayList<Notification> notifications;

    public NotificationAdapter(Context context, ArrayList<Notification> notifications) {
        super(context, R.layout.notification_list_row, notifications);
        this.context = context;
        this.notifications = notifications;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Notification notification = notifications.get(position);
        // Get the data item for this position
        //Notification notification = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.notification_list_row, parent, false);
        }
        // Lookup view for data population
        ImageView notificationImage = (ImageView) convertView.findViewById(R.id.notification_image);
        TextView notificationText = (TextView) convertView.findViewById(R.id.notification_text);
        TextView notificationTime = (TextView) convertView.findViewById(R.id.notification_time);

        // Populate the data into the template view using the data object
        notificationImage.setImageResource(R.mipmap.daily_routine);
        notificationText.setText((CharSequence) notification.getNotification_text());
        notificationTime.setText(notification.getActivity_time());
        // Return the completed view to render on screen
        return convertView;
    }
}
