package com.abel.nfc.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

import android.os.AsyncTask;

public class NFCData extends AsyncTask<String, String, String> {
	
	List<NameValuePair> nameValuePairs;
	
    @Override
    protected String doInBackground(String... args) {
    	String url      =  args[0];
        String nfcID    =  args[1];
        String userName =  args[2];
    	
        String output = null;
         output = uploadPost(url, nfcID, userName);

        return output;
    }

          
private String uploadPost(String url, String nfcID, String userName) {
    String output = null;
    try {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        
        nameValuePairs = new ArrayList<NameValuePair>();
	    nameValuePairs.add(new BasicNameValuePair("nfcID", nfcID));
	    nameValuePairs.add(new BasicNameValuePair("userName", userName));
	      	    
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
	//Do something with the result.
	//Not Necessary here.
 }

 
}
