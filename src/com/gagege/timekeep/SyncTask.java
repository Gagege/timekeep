package com.gagege.timekeep;

import java.net.URL;
import com.gagege.timekeep.RestClient.RequestMethod;
import android.os.AsyncTask;

public class SyncTask extends AsyncTask<URL, Integer, String> {

	@Override
	protected String doInBackground(URL... params) {
		
		return callService(params[0]);
	}
	
	protected void onPostExecute(String result)
	{
		
	}


	private String callService(URL... url) {
		String baseurlString = "/api";
		RestClient client = new RestClient(baseurlString + url);
		
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
