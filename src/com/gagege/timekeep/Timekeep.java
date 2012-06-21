package com.gagege.timekeep;

import java.text.DateFormat;
import java.util.Date;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Timekeep extends ListActivity {
    private static final int ADD_ITEM = 0;
    private static final int REMOVE_ITEM = 1;
    private static final int EXIT_ITEM = 2;

    private ArrayAdapter<String> dataAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        dataAdapter = new ArrayAdapter<String>(this, R.layout.item,R.id.itemName);

        setListAdapter(dataAdapter);
        
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> parent, View view,
		          int position, long id) {
		    	  Intent edit = new Intent(view.getContext(), Edit.class);
		    	  startActivityForResult(edit, 0);
		      }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Resources resource = getApplicationContext().getResources();
        menu.add(Menu.NONE, ADD_ITEM, ADD_ITEM,
            resource.getText(R.string.ADD_ITEM)).setIcon(android.R.drawable.ic_input_add);
        menu.add(Menu.NONE, REMOVE_ITEM, REMOVE_ITEM,
            resource.getText(R.string.REMOVE_ITEM)).setIcon(android.R.drawable.ic_input_delete);
        menu.add(Menu.NONE, EXIT_ITEM, EXIT_ITEM,
            resource.getText(R.string.EXIT_ITEM)).setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        case ADD_ITEM:
            dataAdapter.add(DateFormat.getDateInstance().format(new Date()));
            break;
        case REMOVE_ITEM:
            dataAdapter.remove(dataAdapter.getItem(dataAdapter.getCount() - 1));
            break;
        case EXIT_ITEM:
            finish();
        }
        return false;
    }
}