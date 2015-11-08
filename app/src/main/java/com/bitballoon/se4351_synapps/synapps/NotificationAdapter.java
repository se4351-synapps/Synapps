package com.bitballoon.se4351_synapps.synapps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Bontavy on 11/8/2015.
 */
public class NotificationAdapter extends ArrayAdapter<Notification> {
    public NotificationAdapter(Context context, ArrayList<Notification> notifications) {
        super(context, 0, notifications);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Notification notification = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.notification_list_item, parent, false);
        }
        // Lookup view for data population
        ImageView notificationImage = (ImageView) convertView.findViewById(R.id.notification_image);
        String notificationText = (String) convertView.findViewById(R.id.notification_text).toString();
        // Populate the data into the template view using the data object
        notificationImage.setImageDrawable(notification.getNotification_image().getDrawable());
        notificationText = notification.getNotification_text();
        // Return the completed view to render on screen
        return convertView;
    }
}
