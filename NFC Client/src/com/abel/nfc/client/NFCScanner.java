package com.abel.nfc.client;


import java.util.concurrent.ExecutionException;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint("NewApi")
public class NFCScanner extends Activity {

    TextView outputText;
    AsyncTask<String, String, String> uploadStatus;

    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private String nfcID = "Null";
    private String googleAccount ; 
    private AccountManager accountManager;

 
    //public static final String URL = "http://10.0.2.2:8888/nfcServer"; // for the emulator
    public static final String URL = "http://nfcreceiver.appspot.com/nfcStore"; // for app engine and real device
 
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_client);


        outputText = (TextView) findViewById(R.id.outputTxt);
        ////////////////////////
        
        accountManager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        Account[] accountList = accountManager.getAccounts();
        googleAccount = accountList[0].name;
        
        Toast.makeText(getApplicationContext(), googleAccount, Toast.LENGTH_SHORT).show();

        resolveIntent(getIntent());
     
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mAdapter == null) {
            //showMessage(R.string.error, R.string.no_nfc);
        	Toast.makeText(getApplicationContext(), "No Adapter found!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

       mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
      
        
    }
    
    // exit app
    public void closeActivity(View view) {
    	
     finish();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        
        if (mAdapter != null) {
            if (!mAdapter.isEnabled()) {
            	Toast.makeText(getApplicationContext(), "Enable NFC and Try Again!", Toast.LENGTH_SHORT).show();
            }
            mAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
          }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAdapter != null) {
            mAdapter.disableForegroundDispatch(this);
        }
    }

    
     private void resolveIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action) 
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
        	
        	Tag scannedTag = getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);
        	nfcID = getHex(scannedTag.getId());
        	
        	// Call Data uploader
        	NFCData nfcData = new NFCData();
        	uploadStatus = nfcData.execute(URL,nfcID,googleAccount);
        	try {
    			displayStaus(uploadStatus.get());
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (ExecutionException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            //Toast.makeText(getApplicationContext(), "Scanned ID is: "+nfcID, Toast.LENGTH_SHORT).show();
        }
    }

    

    private String getHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = bytes.length - 1; i >= 0; --i) {
            int b = bytes[i] & 0xff;
            if (b < 0x10)
                sb.append('0');
            sb.append(Integer.toHexString(b));
            if (i > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }


    @Override
    public void onNewIntent(Intent intent) {
        setIntent(intent);
        resolveIntent(intent);
    }

    private void displayStaus(String output) {
        //outputText.setText(output);
    	 Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
     }
    
    
}
