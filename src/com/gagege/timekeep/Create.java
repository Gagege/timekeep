package com.gagege.timekeep;

import java.text.DateFormat;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class Create extends FragmentActivity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        
        EditText date = (EditText)findViewById(R.id.dateTextEdit);
        date.setText(DateFormat.getDateInstance().format(new Date()));
        
        EditText hours = (EditText)findViewById(R.id.hoursTextEdit);
        OnFocusChangeListener focusListener = new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasFocus) {
				EditText hoursView = (EditText) view;
				String hours = hoursView.getText().toString();
            	if(hasFocus)
				{
            		hoursView.setText(hours.replace(" hrs", ""));
            		hoursView.selectAll();
				}
				else
				{
					if(!hours.toString().contains("hrs"))
						hoursView.setText(hours +" hrs");
				}
            }
        };
        hours.setOnFocusChangeListener(focusListener);
    }
	
	public void showDatePickerDialog(View view) {
		DialogFragment newFragment = new DatePickerFragment((EditText)findViewById(R.id.dateTextEdit));
	    newFragment.show(getSupportFragmentManager(), "datePicker");
	}
	
	public void cancelClick(View view) {
		finish();
	}
}
