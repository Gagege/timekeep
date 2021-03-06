package com.gagege.timekeep;

import java.text.DateFormat;
import java.util.Date;

import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class Create extends SherlockFragmentActivity {
	
    private static EntryDataSource dataSource;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        if(null == dataSource)
        	dataSource = new EntryDataSource(this);
        
        setupHoursEdit();
        setupDateEdit();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getSupportMenuInflater();
	    inflater.inflate(R.menu.createmenu, menu);
	    return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
	        case android.R.id.home:
	        	finish();
	        	return true;
        }
		return false;
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

	private void setupDateEdit() {
		EditText date = (EditText)findViewById(R.id.dateTextEdit);
        date.setText(DateFormat.getDateInstance().format(new Date()));
	}
	
	public void showDatePickerDialog(View view) {
		DialogFragment newFragment = new DatePickerFragment((EditText)findViewById(R.id.dateTextEdit));
	    newFragment.show(getSupportFragmentManager(), "datePicker");
	}
	
	public void doneClick(MenuItem item) {
		Entry entry = new Entry();
		entry.hours(hoursAsDouble());
		entry.date(dateAsLong());
		entry.project(((EditText)findViewById(R.id.projectTextEdit)).getText().toString());
		entry.client(((EditText)findViewById(R.id.clientTextEdit)).getText().toString());
		entry.notes(((EditText)findViewById(R.id.notesTextEdit)).getText().toString());
		dataSource.open();
		dataSource.createEntry(entry);
		dataSource.close();
		finish();
	}
	
	private double hoursAsDouble() {
		String hoursString = hoursTextOnlyNumbers(((EditText)findViewById(R.id.hoursTextEdit))
				.getText().toString());
		String stringToParse = ((hoursString.equals("")) ? "0" : hoursString);
		return Double.parseDouble(stringToParse);
	}
	
	private long dateAsLong() {
		return (new Date(((EditText)findViewById(R.id.dateTextEdit))
				.getText().toString())).getTime();
	}
}
