package com.griffithcollege.runix;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GPSLocationListener implements  LocationListener {

    private DataGPS data;

    public GPSLocationListener(){
        this.data = new DataGPS();
    }

    public DataGPS getData() {
        return data;
    }

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
        // ?
    }

    @Override
    public void onProviderDisabled(String provider) {
        // ?
    }
}
