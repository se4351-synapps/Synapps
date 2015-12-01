package com.bitballoon.se4351_synapps.synapps;

import android.content.Context;
import android.util.Log;
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
public class NotificationAdapter extends ArrayAdapter<Notification> {
    private ArrayList<Notification> notifications;
    private LayoutInflater layoutInflater;

    public NotificationAdapter(Context context, ArrayList<Notification> notifications) {
        super(context, 0, notifications);
        this.notifications = notifications;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            view = layoutInflater.inflate(R.layout.notification_list_row, parent, false);

            // cache view fields into the holder
            holder = new ViewHolder();
            holder.notificationImage = (ImageView) view.findViewById(R.id.notification_image);
            holder.notificationText = (TextView) view.findViewById(R.id.notification_text);
            holder.notificationTime = (TextView) view.findViewById(R.id.notification_time);
            // associate the holder with the view for later lookup
            view.setTag(holder);
        } else
            // view already exists, get the holder instance from the view
            holder = (ViewHolder) view.getTag();

        // Populate the data into the template view using the data object
        Notification notification = notifications.get(position);
        holder.notificationImage.setImageResource(R.mipmap.daily_routine);
        holder.notificationText.setText(notification.getNotification_text());
        holder.notificationTime.setText(notification.getActivity_time());

        // Return the completed view to render on screen
        return view;
    }

    static class ViewHolder {
        ImageView notificationImage;
        TextView notificationText;
        TextView notificationTime;
    }
}
