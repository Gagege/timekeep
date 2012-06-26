package com.gagege.timekeep;

import java.text.DateFormat;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class Create extends FragmentActivity {
	
    private static EntryDataSource dataSource;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        
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
					if(!hours.toString().contains("hrs"))
						hoursView.setText(hours +" hrs");
				}
            }
        };
        hours.setOnFocusChangeListener(focusListener);
	}

	private String hoursTextOnlyNumbers(String hours) {
		return hours.replace(" hrs", "");
	}

	private void setupDateEdit() {
		EditText date = (EditText)findViewById(R.id.dateTextEdit);
        date.setText(DateFormat.getDateInstance().format(new Date()));
	}
	
	public void showDatePickerDialog(View view) {
		DialogFragment newFragment = new DatePickerFragment((EditText)findViewById(R.id.dateTextEdit));
	    newFragment.show(getSupportFragmentManager(), "datePicker");
	}
	
	public void cancelClick(View view) {
		finish();
	}
	
	public void doneClick(View view) {
		Entry entry = new Entry();
		entry.hours(Double.parseDouble(hoursTextOnlyNumbers(((EditText)findViewById(R.id.hoursTextEdit)).getText().toString())));
		entry.date((new Date(((EditText)findViewById(R.id.dateTextEdit)).getText().toString())).getTime());
		entry.project(((EditText)findViewById(R.id.projectTextEdit)).getText().toString());
		entry.client(((EditText)findViewById(R.id.clientTextEdit)).getText().toString());
		entry.notes(((EditText)findViewById(R.id.notesTextEdit)).getText().toString());
		dataSource.open();
		dataSource.createEntry(entry);
		dataSource.close();
		finish();
	}
}
