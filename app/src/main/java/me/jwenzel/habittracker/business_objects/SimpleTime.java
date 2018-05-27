package me.jwenzel.habittracker.business_objects;

public class SimpleTime {

    private int mHour;
    private int mMinute;

    public SimpleTime(int hour, int minute) {
        mHour = hour;
        mMinute = minute;
    }

    public static SimpleTime fromStringTime(String timeString) {
        int timeInt = Integer.parseInt(timeString);
        int minute = timeInt % 100;
        int hour = timeInt / 100;
        return new SimpleTime(hour, minute);
    }

    public int getHour() {
        return mHour;
    }

    public void setHour(int hour) {
        mHour = hour;
    }

    public int getMinute() {
        return mMinute;
    }

    public void setMinute(int minute) {
        mMinute = minute;
    }

    @Override
    public String toString() {
        // This is kinda hacky but we need the format to be hhmm
        return String.format("%02d%02d", mHour, mMinute);
    }
}
