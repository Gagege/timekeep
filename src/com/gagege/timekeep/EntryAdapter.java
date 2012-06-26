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
			TextView itemId = (TextView) view.findViewById(R.id.itemId);
			TextView itemName = (TextView) view.findViewById(R.id.itemName);
			if (itemId != null) {
				itemId.setText(entry.id() + "");
			}
			if(itemName != null) {
				itemName.setText(entry.prettyDate());
			}
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
