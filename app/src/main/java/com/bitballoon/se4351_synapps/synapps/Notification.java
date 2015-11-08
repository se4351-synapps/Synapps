package com.bitballoon.se4351_synapps.synapps;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Bontavy on 11/8/2015.
 */
public class Notification {
    //int activity_id=0;

    ImageView notification_image;
    String notification_text;
    String activity_time;
    Boolean monday;
    Boolean tuesday;
    Boolean wednesday;
    Boolean thursday;
    Boolean friday;
    Boolean saturday;
    Boolean sunday;
    Boolean weekday;
    Boolean weekend;

    public Notification(ImageView notification_image, String notification_text, String activity_time, boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, boolean saturday, boolean sunday, boolean weekday, boolean weekend){
        //activity_id++; 
        this.notification_image=notification_image;
        this.notification_text =  notification_text;
        this.activity_time=activity_time;
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
        this.notification_text = notification_text;
    }

    public String getNotification_text() {
        return notification_text;
    }

    public void setActivity_time(String activity_time) {
        this.activity_time = activity_time;
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
}


