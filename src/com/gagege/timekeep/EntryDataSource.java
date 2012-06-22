package com.gagege.timekeep;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class EntryDataSource {
	
	private SQLiteDatabase database;
	private Database dbHelper;
	private String[] allColumns = {
			Database.ID_COLUMN,
			Database.HOURS_COLUMN,
			Database.DATE_COLUMN,
			Database.PROJECT_COLUMN,
			Database.CLIENT_COLUMN,
			Database.NOTES_COLUMN};
	
	public EntryDataSource(Context context) {
		dbHelper = new Database(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public Entry createEntry(Entry entry) {
		ContentValues values = new ContentValues();
		values.put(Database.HOURS_COLUMN, entry.hours());
		values.put(Database.DATE_COLUMN, entry.date());
		values.put(Database.PROJECT_COLUMN, entry.project());
		values.put(Database.CLIENT_COLUMN, entry.client());
		values.put(Database.NOTES_COLUMN, entry.notes());
		long insertId = database.insert(Database.ENTRIES_TABLE, null,
				values);
		Cursor cursor = database.query(Database.ENTRIES_TABLE,
				allColumns, Database.ID_COLUMN + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Entry newEntry = cursorToEntry(cursor);
		cursor.close();
		return newEntry;
	}

	public void deleteComment(Entry entry) {
		long id = entry.id();
		System.out.println("Comment deleted with id: " + id);
		database.delete(Database.ENTRIES_TABLE, Database.ID_COLUMN
				+ " = " + id, null);
	}
	
	public List<Entry> getAllEntries() {
		List<Entry> entries = new ArrayList<Entry>();

		Cursor cursor = database.query(Database.ENTRIES_TABLE,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Entry entry = cursorToEntry(cursor);
			entries.add(entry);
			cursor.moveToNext();
		}
		cursor.close();
		return entries;
	}
	
	private Entry cursorToEntry(Cursor cursor) {
		Entry entry = new Entry();
		entry.id(cursor.getLong(0));
		entry.hours(cursor.getInt(1));
		entry.date(cursor.getLong(2));
		entry.project(cursor.getString(3));
		entry.client(cursor.getString(4));
		entry.notes(cursor.getString(5));
		return entry;
	}
}
