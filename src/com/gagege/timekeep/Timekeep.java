package com.gagege.timekeep;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

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

        dataAdapter = new ArrayAdapter<String>(this, R.layout.item,R.id.itemName);
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

        builder.setNegativeButton(android.R.string.cancel,
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