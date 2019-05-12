package com.griffithcollege.runix;

import java.util.Date;
import java.util.LinkedList;

public class DataGPS {

    private LinkedList<DataPoint> data;

    /**
     * The constructor create an empty lLinkedList that is composed of DataPoint
     */
    public DataGPS(){
        this.data = new LinkedList<>();
    }

    /**
     * Create a new DataPoint and then add it to the LinkedList
     *
     * @param latitude the latitude of the new dataPoint
     * @param longitude the longitutde of the new dataPoint
     * @param altitude the altitude of the new dataPoint
     * @param speed the speed at which the user in going
     * @param time the time at which all the above happens
     */
    public void add(Double latitude, Double longitude, Double altitude, Float speed, Date time){
        DataPoint dataPoint = new DataPoint(latitude, longitude, altitude, speed, time);
        data.add(dataPoint);
    }

    /**
     * return the DataPoint at this index
     *
     * @param index the index of the desired DataPoint
     * @return the desired DataPoint
     */
    public DataPoint get(int index){
        return data.get(index);
    }

    /**
     *
     * @return the size of the linkedList
     */
    public int size(){
        return data.size();
    }

    public class DataPoint {
        private  Double mLatitude;
        private  Double mLongitude;
        private  Double mAltitude;
        private Float mSpeed;
        private  Date mTime;

        /**
         * the constructor of the Class DataPoint
         * @param latitude the latitude of the new dataPoint
         * @param longitude the longitutde of the new dataPoint
         * @param altitude the altitude of the new dataPoint
         * @param speed the speed at which the user in going
         * @param time the time at which all the above happens
         */
        DataPoint(Double latitude, Double longitude, Double altitude, Float speed, Date time){
            this.mLatitude = latitude;
            this.mLongitude = longitude;
            this.mAltitude = altitude;
            this.mSpeed = speed;
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
