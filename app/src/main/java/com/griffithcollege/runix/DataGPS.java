package com.griffithcollege.runix;

import java.util.Date;
import java.util.LinkedList;

public class DataGPS {

    private LinkedList<DataPoint> data;

    public DataGPS(){
        this.data = new LinkedList<>();
    }

    public void add(Double latitude, Double longitude, Double altitude, Float speed, Date time){
        DataPoint dataPoint = new DataPoint(latitude, longitude, altitude, speed, time);
        data.add(dataPoint);
    }

    public DataPoint get(int index){
        return data.get(index);
    }

    public int size(){
        return data.size();
    }

    public class DataPoint {
        private  Double mLatitude;
        private  Double mLongitude;
        private  Double mAltitude;
        private Float mSpeed;
        private  Date mTime;

        DataPoint(Double latitude, Double longitude, Double altitude, Float Speed, Date time){
            this.mLatitude = latitude;
            this.mLongitude = longitude;
            this.mAltitude = altitude;
            this.mSpeed = Speed;
            this.mTime = time;
        }

        public Double getmLatitude() {
            return mLatitude;
        }

        public Double getmLongitude() {
            return mLongitude;
        }

        public Double getmAltitude() {
            return mAltitude;
        }

        public Date getmTime() {
            return mTime;
        }

        public Float getmSpeed() {
            return mSpeed;
        }
    }
}
