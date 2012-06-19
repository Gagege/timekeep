package com.gagege.timekeep;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class Timekeep extends Activity implements OnClickListener {
	
    public void onClick(View v){
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ExpandableListView entryListView = (ExpandableListView)findViewById(R.id.entryLstVw);
        entryListView.add
    }
}