package com.gagege.timekeep;

import java.text.DateFormat;
import java.util.Date;

import com.actionbarsherlock.app.SherlockDialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

public class DatePickerFragment extends SherlockDialogFragment implements DatePickerDialog.OnDateSetListener{

	private EditText dateEdit;
	
	public DatePickerFragment(EditText textBox) {
		super();
		dateEdit = textBox;
	}
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
	
		Date date = new Date(dateEdit.getText().toString());
	    int year = date.getYear() + 1900;
	    int month = date.getMonth();
	    int day = date.getDate();
	
	    return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
		Date date = new Date(year + "/" + (month + 1) + "/" + day);
		dateEdit.setText(DateFormat.getDateInstance().format(date));
	}

}
