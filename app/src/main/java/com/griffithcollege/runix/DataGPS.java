package com.griffithcollege.runix;

import java.util.Date;
import java.util.LinkedList;

public class DataGPS {

    private LinkedList<DataPoint> data;

    public DataGPS(){
        this.data = new LinkedList<>();
    }

    public void add(Double latitude, Double longitude, Double elevation, Date time){
        DataPoint dataPoint = new DataPoint(latitude, longitude, elevation, time);
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
        private  Double mElevation;
        private  Date mTime;

        DataPoint(Double latitude, Double longitude, Double elevation, Date time){
            this.mLatitude = latitude;
            this.mLongitude = longitude;
            this.mElevation = elevation;
            this.mTime = time;
        }

        public Double getmLatitude() {
            return mLatitude;
        }

        public Double getmLongitude() {
            return mLongitude;
        }

        public Double getmElevation() {
            return mElevation;
        }

        public Date getmTime() {
            return mTime;
        }
    }
}
