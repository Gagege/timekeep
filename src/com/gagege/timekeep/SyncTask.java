package com.gagege.timekeep;

import java.net.URL;
import com.gagege.timekeep.RestClient.RequestMethod;
import android.os.AsyncTask;

public class SyncTask extends AsyncTask<String, Integer, String> {

	@Override
	protected String doInBackground(String... params) {
		
		return callService(params);
	}
	
	protected void onPostExecute(String result)
	{
		
	}


	private String callService(String... url) {
		String baseurlString = "http://96.2.65.101:3000/";
		RestClient client = new RestClient(baseurlString + url[0]);
		
		try
		{
			client.Execute(RequestMethod.GET);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String responseString = client.getResponse();
		return responseString;
	}
}
