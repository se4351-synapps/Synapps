package com.bitballoon.se4351_synapps.synapps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bontavy on 11/8/2015.
 */
public class NotificationAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Notification> notifications;
    private LayoutInflater layoutInflater;

    public NotificationAdapter(Context context, ArrayList<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        //Notification notification = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.notification_list_row, parent, false);
        }
        // Lookup view for data population
        ImageView notificationImage = (ImageView) convertView.findViewById(R.id.notification_image);
        TextView notificationText = (TextView) convertView.findViewById(R.id.notification_text);
        TextView notificationTime = (TextView) convertView.findViewById(R.id.notification_time);

        // Populate the data into the template view using the data object
        notificationImage.setImageResource(R.mipmap.daily_routine);
        notificationText.setText((CharSequence) notifications.get(position).getNotification_text());
        notificationTime.setText(notifications.get(position).getActivity_time());
        // Return the completed view to render on screen
        return convertView;
    }
}
