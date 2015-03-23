package com.adamanteusstudios.greenergrass;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends ActionBarActivity {
    String errorString = "An error has occured. Please check you network connection and try again.";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DownloadData extends AsyncTask<String, Void, String>{

        String freeXmlData;
        String paidXmlData;
        @Override
        protected String doInBackground(String... urls) {
            try{
                freeXmlData = downloadXML(urls[0]);//for the free apps
                paidXmlData = downloadXML(urls[1]);//for the paid apps

            }catch (IOException e){
                return errorString;
            }

            return "";
        }
    }

    private String downloadXML(String theUrl) throws IOException{
        int BUFFER_SIZE = 2000;//ammount of chars to try and process at one time
        InputStream is = null;

        //temporary container for data
        String xmlContent = "";

        try{
            URL url = new URL(theUrl);
            HttpURLConnection connecion = (HttpURLConnection) url.openConnection();//connect to url and download file
            connecion.setReadTimeout(10000); //reading should timeout after 10 seconds
            connecion.setConnectTimeout(15000); //the connectiosn itself should time out after 15 seconds
            connecion.setRequestMethod("GET");//command to down load the file
            connecion.setDoInput(true);
            int response = connecion.getResponseCode();//a good connection should return 200
            Log.d("downloadXML", "The repsonse code is: " + response);//putting request code to log of debugging

            //connectiong input stream to url
            is = connecion.getInputStream();

            //loop through file till we get to the end
            InputStreamReader isr = new InputStreamReader(is);
            int charRead; //each character is read individually and fed in to an array
            char[] inputBuffer = new char[BUFFER_SIZE];

            try{
                while((charRead = isr.read(inputBuffer)){ //while there is still data keep processing it
                    //put the chars from the input Buffer into a string called readString
                    //add the read string to the xmlContent
                    //reset the inputBuffer for the next 2000(or less) characters
                    String readString = String.copyValueOf(inputBuffer, 0, charRead);
                    xmlContent += readString;
                    inputBuffer = new char[BUFFER_SIZE];

                }

            }catch (IOException e){
                e.printStackTrace();//printing error so stack for debugging
                return null;
            }




        } finally{
            //do this no matter what even if there is an error
            if(is != null){ //is = input stream;
                is.close();
            }
        }
    }

}
