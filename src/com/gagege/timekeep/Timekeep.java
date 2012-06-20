package com.gagege.timekeep;

import java.util.Arrays;
import java.util.List;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Timekeep extends ListActivity {
    private static final int ADD_ITEM = 0;
    private static final int REMOVE_ITEM = 1;
    private static final int EXIT_ITEM = 2;

    private ArrayAdapter<String> dataAdapter;
    private Dialog editorDialog = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
            R.id.itemName);
        dataAdapter.add("apple");
        dataAdapter.add("orange");
        dataAdapter.add("tomato");

        setListAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Resources resource = getApplicationContext().getResources();
        menu.add(Menu.NONE, ADD_ITEM, ADD_ITEM,
            resource.getText(R.string.ADD_ITEM)).setIcon(R.drawable.add);
        menu.add(Menu.NONE, REMOVE_ITEM, REMOVE_ITEM,
            resource.getText(R.string.REMOVE_ITEM)).setIcon(R.drawable.remove);
        menu.add(Menu.NONE, EXIT_ITEM, EXIT_ITEM,
            resource.getText(R.string.EXIT_ITEM)).setIcon(R.drawable.exit);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        case ADD_ITEM:
            showDialog(0);
            break;
        case REMOVE_ITEM:
            dataAdapter.remove(dataAdapter.getItem(dataAdapter.getCount() - 1));
            break;
        case EXIT_ITEM:
            finish();
        }
        return false;
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        Dialog editor = editorDialog;
        if (editorDialog == null)
        {
            editor = createEditorDialog();
        }
        return editor;
    }

    private Dialog createEditorDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.addDialogTitle);

        View content = getLayoutInflater().inflate(R.layout.editor,
            (ViewGroup) findViewById(R.id.editLayout));
        builder.setView(content);
        builder.setPositiveButton(R.string.addButtonLabel,
            new DialogInterface.OnClickListener()
            {

                public void onClick(DialogInterface dialog, int which)
                {
                    Dialog source = (Dialog) dialog;
                    EditText nameField = (EditText) source
                        .findViewById(R.id.itemField);
                    String name = nameField.getText().toString();

                    EditText timesField = (EditText) source
                        .findViewById(R.id.timesField);
                    Integer times = Integer.valueOf(timesField.getText()
                        .toString());

                    if ((name.length() > 0) && (times > 0))
                    {
                        for (int count = 0; count < times; count++)
                        {
                            dataAdapter.add(name);
                        }
                    }
                    dialog.dismiss();
                }
            });

        builder.setNegativeButton(R.string.cancelButtonLabel,
            new DialogInterface.OnClickListener()
            {

                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.dismiss();
                }
            });

        return builder.create();
    }
}