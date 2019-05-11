package com.griffithcollege.runix;

public class Statistics
{
    DataGPS data;

    public Statistics(DataGPS dataGPS){
        this.data = dataGPS;
    }

  public Float averageSpeed()
  {
        Float sumSpeed = 0f;
        for(int i = 0; i< data.size(); i++){
            sumSpeed += data.get(i).getmSpeed();
        }
        return (sumSpeed/data.size());
  }

    public Long timeTaken()
    {
        return ((data.get(data.size()-1).getmTime().getTime() - data.get(0).getmTime().getTime())/1000);
    }

    public Double distanceBetweenPoints(int index){
        DataGPS.DataPoint point1 = data.get(index);
        DataGPS.DataPoint point2 = data.get(index+1);
        Double longitude1 = point1.getmLongitude();
        Double latitude1 = point1.getmLatitude();
        Double altitude1 = point1.getmAltitude();
        Double longitude2 = point2.getmLongitude();
        Double latitude2 = point2.getmLatitude();
        Double altitude2 = point2.getmAltitude();

        final int R = 6371; // Radius of the earth

        Double latDistance = Math.toRadians(latitude2 - latitude1);
        Double lonDistance = Math.toRadians(longitude2 - longitude1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double distance = R * c * 1000; // convert to meters

        Double height = altitude1 - altitude2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    public Double totalDistance(){
        Double sumDistance = 0d;
        for(int i = 0; i< data.size()-1; i++){
            sumDistance += distanceBetweenPoints(i);
        }
        return sumDistance;
    }

    public Double maximumAltitude(){
        Double max = data.get(0).getmAltitude();
        for(int i = 1; i<data.size();i++){
            max = Math.max(max,data.get(i).getmAltitude());
        }
        return max;
    }

    public Double minimumAltitude() {
        Double min = data.get(0).getmAltitude();
        for (int i = 1; i < data.size(); i++) {
            min = Math.min(min, data.get(i).getmAltitude());
        }
        return min;
    }
}