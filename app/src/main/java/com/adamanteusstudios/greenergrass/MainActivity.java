package com.adamanteusstudios.greenergrass;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class MainActivity extends ActionBarActivity {
    String errorString = "An error has occured. Please check you network connection and try again."



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

    private class DownloadDate extends AsyncTask<String, void, String>{

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
            URL freeUrl = new URL(theUrl);

        } finally{
            //do this no matter what even if there is an error
            if(is != null){ //is = input stream
                is.close();
            }
        }
    }

}
