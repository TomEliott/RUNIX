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
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GPXParser {

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

    public DataGPS readGPX(String filename){
        try {
            DataGPS data = new DataGPS();
            Double latitude;
            Double longitute;
            Double elevation;
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
                        longitute = Double.valueOf(parser.getAttributeValue(1));
                        parser.next();
                        elevation = Double.valueOf(parser.nextText());
                        parser.next();
                        date = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(parser.nextText());
                        data.add(latitude,longitute,elevation,date);
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

    public void writeGPX(DataGPS data, String filename){
        XmlSerializer gpxSerializer = Xml.newSerializer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
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
                gpxSerializer.text(temp.getmElevation().toString());
                gpxSerializer.endTag("","ele");
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
