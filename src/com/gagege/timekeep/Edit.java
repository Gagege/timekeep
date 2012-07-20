package com.gagege.timekeep;

import java.util.Date;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class Edit extends SherlockFragmentActivity {
	
    private EntryDataSource dataSource;
    private Entry entry; 
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        
        if(null == dataSource)
        	dataSource = new EntryDataSource(this);
        
        dataSource.open();
        entry = dataSource.getEntryById(getIntent().getExtras().getLong("id"));
        dataSource.close();
        
        setupHoursEdit();
        setupDateEdit();
        setupProjectEdit();
        setupClientEdit();
        setupNotesEdit();
        
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
        hours.setText(entry.hours() + "");
	}

	private String hoursTextOnlyNumbers(String hours) {
		return hours.replace(" hrs", "");
	}
	
	public void showDatePickerDialog(View view) {
		SherlockDialogFragment newFragment = new DatePickerFragment((EditText)findViewById(R.id.dateTextEdit));
	    newFragment.show(getSupportFragmentManager(), "datePicker");
	}

	private void setupDateEdit() {
		EditText date = (EditText)findViewById(R.id.dateTextEdit);
        date.setText(entry.prettyDate());
	}

	private void setupProjectEdit() {
		EditText project = (EditText)findViewById(R.id.projectTextEdit);
        project.setText(entry.project());
	}

	private void setupClientEdit() {
		EditText client = (EditText)findViewById(R.id.clientTextEdit);
		client.setText(entry.client());
	}

	private void setupNotesEdit() {
		EditText notes = (EditText)findViewById(R.id.notesTextEdit);
		notes.setText(entry.notes());
	}
	
	public void deleteClick(View view) {
		dataSource.open();
		dataSource.deleteEntry(entry);
		dataSource.close();
		finish();
	}
	
	public void cancelClick(View view) {
		finish();
	}
	
	public void doneClick(View view) {
		entry.hours(hoursAsDouble());
		entry.date(dateAsLong());
		entry.project(((EditText)findViewById(R.id.projectTextEdit)).getText().toString());
		entry.client(((EditText)findViewById(R.id.clientTextEdit)).getText().toString());
		entry.notes(((EditText)findViewById(R.id.notesTextEdit)).getText().toString());
		dataSource.open();
		dataSource.updateEntry(entry);
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
