package com.example.htmlparse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	
Button b1;
String link1;
DefaultHttpClient httpClient ;
HttpEntity httpEntity = null;
HttpResponse httpResponse = null;
String response, result = null;
InputStream is = null;
String[] currencylist=new String[14];
String[] finallist=new String[14];
String[] finallist2=new String[14];
String[] currencyvalue=new String[42];
ArrayList<String> currencyvaluelist;
StringBuilder sb;
String[] linkHref =new String[56];
String linkText ;
int i=0,j=0,l=0;
public static final int DIALOG_DOWNLOAD_PROGRESS = 0;

private ProgressDialog mProgressDialog,pd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		link1="http://www.thomascook.in/tcportal/productCur.do";
		startDownload(link1);
		b1=(Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for(int i=0;i<finallist.length;i++){
					Toast.makeText(getApplicationContext(), finallist[i], Toast.LENGTH_LONG).show();
						}

				
			}
		});
		
	
		
		
	}
	protected void startDownload(String myurl) {
		// TODO Auto-generated method stub
		String url = myurl;

		new DownloadFileAsync().execute(url);

	}
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_DOWNLOAD_PROGRESS:

			mProgressDialog = new ProgressDialog(MainActivity.this);
			mProgressDialog.setMessage("Downloading file..");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
			

			return mProgressDialog;
		default:
			return null;
		}
	}
	
	class DownloadFileAsync extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			
			showDialog(DIALOG_DOWNLOAD_PROGRESS);
			
			
		}

		@Override
		protected String doInBackground(String... aurl) {
			try {				
				Document doc =  (Document) Jsoup.connect(aurl[0]).get();
				
				
				Elements table = (Elements) doc.getElementsByTag("td");
			
		for (Element link : table) {
					linkText = link.attr("bgcolor");
					if(linkText.equalsIgnoreCase("#f2f3ef")){
						
						linkHref[i]=link.text();
						
						i++;
						
						
					}
					
					
					}
//				for(int i=0;i<table.size();i++){
//				String color=table.text().toString();
//				
//				}
//					 
//			
				
				
				
//				sb = new StringBuilder();
//				httpClient = new DefaultHttpClient();
//				HttpGet httpGet = new HttpGet(aurl[0]);
//
//				httpResponse = httpClient.execute(httpGet);
//
//				is = httpResponse.getEntity().getContent();
//				try {
//					BufferedReader reader = new BufferedReader(
//							new InputStreamReader(is));
//					
//					
//
//					String line = "";
//					while ((line = reader.readLine()) != null) {
//						sb.append(line);
//					}
//
					
					for(j=0;j<=13;j++){
						currencylist[j]=linkHref[j*4];
					}
					
						for(int k=0;k<56;k++){
							if(k%4!=0){
							currencyvalue[l]=linkHref[k];
							l++;
							}
						}
						
					
						result = currencyvalue[38];
						for(j=0;j<=13;j++){
//						finallist[j]=currencylist[j]+" "+":"+" "+currencyvalue[j*3+0]+" "+"|"+" "+currencyvalue[j*3+1]+" "+"|"+" "+currencyvalue[j*3+2];
							finallist[j]=currencylist[j]+" "+":"+" "+currencyvalue[j*3+2];
						}
						//result = finallist[1];
//				} catch (Exception e) {
//					Log.d("Error",
//							"Error occured while reading data");
//				}
//
			} 
			catch (Exception e) {
//
			}
//			
				
			return null;

		}

		@Override
		protected void onPostExecute(String result) {
			 
				dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
			

		}
		
	}
	
}
