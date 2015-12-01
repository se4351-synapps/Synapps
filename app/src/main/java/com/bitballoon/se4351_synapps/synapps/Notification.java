package com.bitballoon.se4351_synapps.synapps;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Created by Bontavy on 11/8/2015.
 */
public class Notification {
    //int activity_id=0;

    ImageView notification_image;
    String notification_text = "";
    String activity_time = "";
    int type;
    /*
        0: regular
        1: meal
        2: medication
        3: finance
    */

    boolean monday;
    boolean tuesday;
    boolean wednesday;
    boolean thursday;
    boolean friday;
    boolean saturday;
    boolean sunday;
    boolean weekday;
    boolean weekend;

    public Notification(ImageView notification_image, String notification_text, String activity_time) {
        this.notification_image = notification_image;
        this.notification_text.equals(notification_text);
        this.activity_time.equals(activity_time);
    }

    public Notification(ImageView notification_image, String notification_text, String activity_time, boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, boolean saturday, boolean sunday, boolean weekday, boolean weekend){
        //activity_id++; 
        this.notification_image=notification_image;
        this.notification_text.equals(notification_text);
        this.activity_time.equals(activity_time);
        this.monday=monday;
        this.tuesday=tuesday;
        this.wednesday=wednesday;
        this.thursday=thursday;
        this.friday=friday;
        this.saturday=saturday;
        this.sunday=sunday;
        this.weekday=weekday;
        this.weekend=weekend;

    }


//    public void setActivity_id(int activity_id) {
//        this.activity_id=activity_id;
//    }
//
//    public int getActiviy_id() {
//        return activity_id;
//    }
    public void setNotification_image(ImageView notification_image){
        this.notification_image=notification_image;
    }

    public void setNotification_text(String notification_text) {
        this.notification_text.equals(notification_text);
    }

    public String getNotification_text() {
        return notification_text;
    }

    public ImageView getNotification_image() {
        return notification_image;
    }

    public void setActivity_time(String activity_time) {
        this.activity_time.equals(activity_time);
    }

    public String getActivity_time() {
        return activity_time;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean getMonday() {
        return monday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean getTuesday() {
        return tuesday;


    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean getWednesday() {
        return wednesday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean getThursday() {
        return thursday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public boolean getFriday() {
        return friday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;

    }

    public boolean getSaturday() {
        return saturday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

    public boolean getSunday() {
        return sunday;
    }

    public void setWeekday(boolean weekday) {
        this.weekday = weekday;
    }

    public boolean getWeekday() {
        return weekday;
    }

    public void setWeekend(boolean weekend) {
        this.weekend = weekend;
    }

    public boolean getWeekend() {
        return weekend;
    }

    public void setType(int type){
        this.type = type;
    }

    public int getType(){
        return type;
    }

    public static int getNotificationCountOnDay(int month, int date, int year, int filter){
        //TODO make data structure to manage all notifications
        //testing dummy notifications
        Notification[] notifications = new Notification[3];
        notifications[0] = new Notification(null, "test", "test", true, false, false, false, false, false, false, false, false);
        notifications[0].setType(1);
        notifications[1] = new Notification(null, "test", "test", false, true, false, false, false, false, false, false, false);
        notifications[1].setType(2);
        notifications[2] = new Notification(null, "test", "test", false, false, false, false, false, false, false, true, false);
        notifications[2].setType(3);

        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, date);
        int count = 0;

        for(Notification n : notifications){
            //check filter
            if(filter != -1 && filter != n.getType())
                continue;

            //check day of week
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            if(
                ((n.getWeekend() || n.getSunday()) && dayOfWeek == Calendar.SUNDAY) ||
                ((n.getWeekday() || n.getMonday()) && dayOfWeek == Calendar.MONDAY) ||
                ((n.getWeekday() || n.getTuesday()) && dayOfWeek == Calendar.TUESDAY) ||
                ((n.getWeekday() || n.getWednesday()) && dayOfWeek == Calendar.WEDNESDAY) ||
                ((n.getWeekday() || n.getThursday()) && dayOfWeek == Calendar.THURSDAY) ||
                ((n.getWeekday() || n.getFriday()) && dayOfWeek == Calendar.FRIDAY) ||
                ((n.getWeekend() || n.getSaturday()) && dayOfWeek == Calendar.SATURDAY))
            {
                count++;
                continue;
            }

            //TODO support for notifications on a specific day?
        }

        return count;
    }
}


