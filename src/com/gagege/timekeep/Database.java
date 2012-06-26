package com.gagege.timekeep;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "timekeep.db";
	private static final int DATABASE_VERSION = 1;
    public static final String ENTRIES_TABLE = "entries";
    public static final String ID_COLUMN = "id";
    public static final String HOURS_COLUMN = "hours";
    public static final String DATE_COLUMN = "date";
    public static final String PROJECT_COLUMN = "project";
    public static final String CLIENT_COLUMN = "client";
    public static final String NOTES_COLUMN = "notes";
    private static final String CREATE_ENTRIES_TABLE = 
    					"create table "
						+ ENTRIES_TABLE + "("
			    		+ ID_COLUMN + " integer primary key autoincrement, "
						+ HOURS_COLUMN + " real not null, "
						+ DATE_COLUMN + " integer not null, "
						+ PROJECT_COLUMN + " text, "
						+ CLIENT_COLUMN + " text, "
						+ NOTES_COLUMN + " text "
			    		+ ");";
    
	Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_ENTRIES_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(Database.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + ENTRIES_TABLE);
		onCreate(db);

	}

}
