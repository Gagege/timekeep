package com.gagege.timekeep;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class Timekeep extends SherlockListActivity {

    private static EntryAdapter dataAdapter;
    private static EntryDataSource dataSource;
    private static List<Entry> entries;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setupListItemClick();
        
        if(null == dataSource)
        	dataSource = new EntryDataSource(this);
    }
	
	@Override
	protected void onResume() {
        super.onResume();
		fillEntries();
	}
	
	private void fillEntries() {
        dataSource.open();
        entries = dataSource.getAllEntries();
        dataSource.close();
        
        if(null == dataAdapter)
        	dataAdapter = new EntryAdapter(this, R.layout.item, entries);
        else
        {
        	dataAdapter.clear();
        	entriesToArrayAdapter(entries);
        }
        
        setListAdapter(dataAdapter);
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
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getSupportMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        case R.id.new_entry:
        	Intent create = new Intent(this.getBaseContext(), Create.class);
        	startActivity(create);
            return true;
        case R.id.settings:
        	Intent settings = new Intent(this.getBaseContext(), Settings.class);
        	startActivity(settings);
            return true;
        case R.id.help:
            break;
        default:
            return super.onOptionsItemSelected(item);
        }
		return false;
    }
	
	private void entriesToArrayAdapter(List<Entry> entries) {
		for(Entry entry : entries) {
			dataAdapter.add(entry);
		}
	}
}