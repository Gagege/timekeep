package com.gagege.timekeep;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class Edit extends FragmentActivity {
	
    private static EntryDataSource dataSource;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        
        if(null == dataSource)
        	dataSource = new EntryDataSource(this);
        
        setupHoursEdit();
        setupDateEdit();
    }
	
	private void setupHoursEdit() {
		EditText hours = (EditText)findViewById(R.id.hoursTextEdit);
        OnFocusChangeListener focusListener = new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasFocus) {
				EditText hoursView = (EditText) view;
				String hours = hoursView.getText().toString();
            	if(hasFocus)
				{
            		hoursView.setText(hoursTextOnlyNumbers(hours));
            		hoursView.selectAll();
				}
				else
				{
					if(hours.toString() == "")
					{
						hoursView.setText("");
					}
					else
					{
						if(!hours.toString().contains("hrs"))
							hoursView.setText(hours +" hrs");
					}
				}
            }
        };
        hours.setOnFocusChangeListener(focusListener);
	}

	private String hoursTextOnlyNumbers(String hours) {
		return hours.replace(" hrs", "");
	}
}
