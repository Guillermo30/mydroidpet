package mydroidpet.mydroidpet.com.mydroidpet.atsk;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import mydroidpet.mydroidpet.com.mydroidpet.Location;

/**
 * Created by victor on 25/10/14.
 */
public class LocationAtsk extends AsyncTask<Void,Void,Location> {
    @Override
    protected Location doInBackground(Void... params) {
        String url="http://192.168.240.1/arduino/mascota";
        Location location= new Location();
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        try {
            HttpResponse response=client.execute(get);
            // Get hold of the response entity
            HttpEntity entity = response.getEntity();
            // If the response does not enclose an entity, there is no need
            // to worry about connection release

            if (entity != null) {

                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                String result= convertStreamToString(instream);
                // now you have the string representation of the HTML request
                instream.close();
                JSONObject js = new JSONObject(result);
                location.setX(js.getDouble("x"));
                location.setY(js.getDouble("y"));
                location.setZ(js.getDouble("z"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return location;
     }
    private String convertStreamToString(InputStream input){
        /*
     * To convert the InputStream to String we use the BufferedReader.readLine()
     * method. We iterate until the BufferedReader return null which means
     * there's no more data to read. Each line will appended to a StringBuilder
     * and returned as String.
     */
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
