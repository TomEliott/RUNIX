package com.griffithcollege.runix;

import android.os.Environment;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GPXParser {

    /** Either create a new file if it doesnt exist or load it if it does, depend if we read or write a gpx file
     *
     * @param filename of the gpx we want to save/load
     * @return the file
     */
    public File getGPXFile(String filename){
        try{
            File GPX = new File(Environment.getExternalStorageDirectory() + "/" + "GPStracks", filename);
            if(!GPX.exists()){
                GPX.createNewFile();
            }
            return GPX;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**we read the gpx file by going to each trackpoint and getting the different values and then put
     * them in a DataPoint that is then added to the DataGPS
     *
     * @param filename of the gpx file to be read
     * @return the DataGPS extracted from the gpx file
     */
    public DataGPS readGPX(String filename){
        try {
            DataGPS data = new DataGPS();
            Double latitude;
            Double longitude;
            Double altitude;
            Float speed;
            Date date;
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            File GPX = getGPXFile(filename);
            FileInputStream in = new FileInputStream(GPX);
            parser.setInput(in,"UTF-8");
            int eventType = parser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                if (eventType == XmlPullParser.START_TAG){
                    if(parser.getName().equals("trkpt")){
                        latitude = Double.valueOf(parser.getAttributeValue(0));
                        longitude = Double.valueOf(parser.getAttributeValue(1));
                        parser.next();
                        altitude = Double.valueOf(parser.nextText());
                        parser.next();
                        speed = Float.valueOf(parser.nextText());
                        parser.next();
                        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parser.nextText());
                        data.add(latitude,longitude,altitude, speed, date);
                    }
                }
                eventType = parser.next();
            }
            return data;
        } catch (XmlPullParserException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /** write the gpx file using a serializer called XmlSerializer
     *
     * @param data the DataGPS which data needs to be written in a gpx file
     * @param filename of the gpx we want to write in, will be created if it does'nt exist
     */
    public void writeGPX(DataGPS data, String filename){
        XmlSerializer gpxSerializer = Xml.newSerializer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            File GPX = getGPXFile(filename);
            FileOutputStream out = new FileOutputStream(GPX);
            gpxSerializer.setOutput(out, "UTF-8");
            gpxSerializer.startDocument("UTF-8", false);
            gpxSerializer.startTag("","gpx");
            gpxSerializer.startTag("","trk");
            gpxSerializer.startTag("","trkseg");
            for(int i =0; i < data.size();i++){
                DataGPS.DataPoint temp = data.get(i);
                gpxSerializer.startTag("","trkpt");
                gpxSerializer.attribute("","lat",temp.getmLatitude().toString());
                gpxSerializer.attribute("","lon",temp.getmLongitude().toString());
                gpxSerializer.startTag("","ele");
                gpxSerializer.text(temp.getmAltitude().toString());
                gpxSerializer.endTag("","ele");
                gpxSerializer.startTag("","speed");
                gpxSerializer.text(temp.getmSpeed().toString());
                gpxSerializer.endTag("","speed");
                gpxSerializer.startTag("","time");
                gpxSerializer.text(dateFormat.format(temp.getmTime()));
                gpxSerializer.endTag("","time");
                gpxSerializer.endTag("","trkpt");
            }
            gpxSerializer.endTag("","trkseg");
            gpxSerializer.endTag("","trk");
            gpxSerializer.endTag("","gpx");
            gpxSerializer.endDocument();
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
