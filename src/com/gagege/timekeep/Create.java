package com.gagege.timekeep;

import java.text.DateFormat;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

public class Create extends FragmentActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        
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
}
