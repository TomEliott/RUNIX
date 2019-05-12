package com.griffithcollege.runix;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GPSLocationListener implements  LocationListener {

    private DataGPS data;

    /**
     * The constructor of the GPSLocationListener create a new DataGPS, and take in argument
     * the LocationManager used in MainActivity for the onProviderEnabled() methods
     */
    public GPSLocationListener(){
        this.data = new DataGPS();
    }

    /**
     *  This method return the DataGPS, it is only called before the GPSLocationListener is
     *  destroyed
     *
     * @return DataGPS
     */
    public DataGPS getData() {
        return data;
    }

    /**
     * This method that is called when the location changed just use the different information
     * on the location to create a new DataPoint that will be added to the DataGPS
     *
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {
        Date date = new Date(location.getTime());

        data.add(location.getLatitude(),location.getLongitude(),location.getAltitude(), (location.getSpeed()*3.6f), date);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    //nothing to do.
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
