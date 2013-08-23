package com.abel.nfc.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NfcCLient extends Activity implements OnClickListener{
	
	Button btnPost;
    TextView outputText;
    List<NameValuePair> nameValuePairs;
 
    //public static final String URL = "http://10.0.2.2:8888/nfcServer"; // for the emulator
    public static final String URL = "http://nfcreceiver.appspot.com/nfcStore"; // for app engine and real device
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_client);
 
        findViewsById();
 
        btnPost.setOnClickListener(this);
    }
 
    private void findViewsById() {
        btnPost = (Button) findViewById(R.id.buttonPost);
        outputText = (TextView) findViewById(R.id.outputTxt);
    }
 
    public void onClick(View view) {
        GetXMLTask task = new GetXMLTask();
        task.execute(new String[] { URL });
    }
 
    private class GetXMLTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String output = null;
            for (String url : urls) {
                output = uploadPost(url);
            }
            return output;
        }
 
              
    private String uploadPost(String url) {
        String output = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            
            nameValuePairs = new ArrayList<NameValuePair>();
    	    nameValuePairs.add(new BasicNameValuePair("nfcID", "215452"));
    	    nameValuePairs.add(new BasicNameValuePair("userName", "abel"));
    	      	    
    	    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            
            HttpResponse httpResponse = httpClient.execute(httppost);
            HttpEntity httpEntity = httpResponse.getEntity();
            output = EntityUtils.toString(httpEntity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
 
    @Override
    protected void onPostExecute(String output) {
        outputText.setText(output);
    }
    }
	
	
}
