package com.gagege.timekeep;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Timekeep extends ListActivity {
    private static final int ADD_ITEM = 0;
    private static final int REMOVE_ITEM = 1;

    private static EntryAdapter dataAdapter;
    private static EntryDataSource dataSource;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        if(null == dataSource)
        	dataSource = new EntryDataSource(this);
        
        setupListItemClick();
    }

	private void setupListItemClick() {
		ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> parent, View view,
		          int position, long id) {
		    	  long entryId = dataAdapter.getItem((int)id).id();
		    	  Intent edit = new Intent(view.getContext(), Edit.class);
		    	  edit.putExtra("id", entryId);
		    	  startActivity(edit);
		      }
        });
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Resources resource = getApplicationContext().getResources();
        menu.add(Menu.NONE, ADD_ITEM, ADD_ITEM,
            resource.getText(R.string.addEntry)).setIcon(android.R.drawable.ic_input_add);
        menu.add(Menu.NONE, REMOVE_ITEM, REMOVE_ITEM,
            resource.getText(R.string.removeEntry)).setIcon(android.R.drawable.ic_input_delete);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        case ADD_ITEM:
        	Intent create = new Intent(this.getBaseContext(), Create.class);
        	startActivity(create);
            break;
        case REMOVE_ITEM:
            dataAdapter.remove(dataAdapter.getItem(dataAdapter.getCount() - 1));
            break;
        }
        return false;
    }
	
	private void entriesToArrayAdapter(List<Entry> entries) {
		dataAdapter.clear();
		for(Entry entry : entries) {
			dataAdapter.add(entry);
		}
	}
	
	@Override
	protected void onResume() {
        dataSource.open();
        List<Entry> entries = dataSource.getAllEntries();
        dataSource.close();

        if(null == dataAdapter)
        	dataAdapter = new EntryAdapter(this, R.layout.item,entries);
        
        setListAdapter(dataAdapter);
        
        entriesToArrayAdapter(entries);
        
        super.onResume();
	}
}