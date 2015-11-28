package com.bitballoon.se4351_synapps.synapps;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;

/**
 * Created by Bontavy on 11/8/2015.
 */
public class Notification implements Parcelable {
    //int activity_id=0;

    private ImageView notification_image;
    private TextView notification_text;
    private TextView activity_time;
    int type; //4 types: regular -1/meals -2/medication - 3/ financial 4
    //do what you need to

    boolean monday;
    boolean tuesday;
    boolean wednesday;
    boolean thursday;
    boolean friday;
    boolean saturday;
    boolean sunday;
    boolean weekday;
    boolean weekend;


    public Notification(ImageView notification_image, TextView notification_text, TextView activity_time) {
        this.notification_image = notification_image;
        this.notification_text = notification_text;
        this.activity_time = activity_time;
    }

    public Notification(ImageView notification_image, TextView notification_text, TextView activity_time, boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, boolean saturday, boolean sunday, boolean weekday, boolean weekend){
        //activity_id++; 
        this.notification_image=notification_image;
        this.notification_text = notification_text;
        this.activity_time = activity_time;
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

    protected Notification(Parcel in) {
        type = in.readInt();
        monday = in.readByte() != 0;
        tuesday = in.readByte() != 0;
        wednesday = in.readByte() != 0;
        thursday = in.readByte() != 0;
        friday = in.readByte() != 0;
        saturday = in.readByte() != 0;
        sunday = in.readByte() != 0;
        weekday = in.readByte() != 0;
        weekend = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeByte((byte) (monday ? 1 : 0));
        dest.writeByte((byte) (tuesday ? 1 : 0));
        dest.writeByte((byte) (wednesday ? 1 : 0));
        dest.writeByte((byte) (thursday ? 1 : 0));
        dest.writeByte((byte) (friday ? 1 : 0));
        dest.writeByte((byte) (saturday ? 1 : 0));
        dest.writeByte((byte) (sunday ? 1 : 0));
        dest.writeByte((byte) (weekday ? 1 : 0));
        dest.writeByte((byte) (weekend ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Notification> CREATOR = new Creator<Notification>() {
        @Override
        public Notification createFromParcel(Parcel in) {
            return new Notification(in);
        }

        @Override
        public Notification[] newArray(int size) {
            return new Notification[size];
        }
    };

    public void setNotification_image(ImageView notification_image){
        this.notification_image=notification_image;
    }

    public void setNotification_text (TextView notification_text) {
        this.notification_text = notification_text;
    }

    public String getNotification_text() {
        return notification_text.getText().toString();
    }

    public ImageView getNotification_image() {
        return notification_image;
    }

    public void setActivity_time (TextView activity_time) {
        this.activity_time = activity_time;
    }

    public String getActivity_time() {
        return activity_time.getText().toString();
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


