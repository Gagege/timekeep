package com.gagege.timekeep;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EntryAdapter extends ArrayAdapter<Entry> {
	private List<Entry> items;
	
	public EntryAdapter(Context context, int textViewResourceId, List<Entry> entries) {
		super(context, textViewResourceId, entries);
		
		items = entries;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		Context context = this.getContext();
		if (view == null) {
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = vi.inflate(R.layout.item, null);
		}
		Entry entry = items.get(position);
		if (entry != null) {
			TextView entryDate = (TextView) view.findViewById(R.id.entryDate);
			TextView entryClient = (TextView) view.findViewById(R.id.entryClient);
			TextView entryProject = (TextView) view.findViewById(R.id.entryProject);
			entryDate.setText(entry.id() + " - " + entry.prettyDate());
			entryClient.setText(entry.client());
			entryProject.setText(entry.project());
		}
		return view;
	}
	
	public boolean hasItem(long id) {
		boolean hasItem = false;
		for(Entry entry : items) {
			if(id == entry.id())
			{
				hasItem = true;
				break;
			}
		}
		return hasItem;
	}
}
