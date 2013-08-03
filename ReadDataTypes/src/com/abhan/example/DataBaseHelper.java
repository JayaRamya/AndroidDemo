package com.abhan.example;

import java.io.File;
import java.io.IOException;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
	private static String DB_PATH = "/data/data/YOUR_PACKAGE/databases/";
	private static String DB_NAME = "Demo";
	public static final String TABLE_NAME = "Android";
	public static final String KEY_ID = "_id"; // INTEGER
	public static final String KEY_NAME = "name"; // STRING / TEXT
	public static final String KEY_EVENT = "event";
	public static final String KEY_MONTH = "month"; // STRING / TEXT
	public static final String KEY_YEAR = "year"; // INTEGER
	public static final String KEY_IMAGE = "image"; // BLOB
	public static final String KEY_VERSION = "version"; //(REAL / DOUBLE / FLOAT) 
	private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
	private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
	private static final int mDbVersion = 1;
	private SQLiteDatabase mDataBase;

	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, mDbVersion);
		DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
	}

	public void createDataBase() throws IOException {
		boolean mDataBaseExist = checkDataBase();
		if (!mDataBaseExist) {
			this.getReadableDatabase();
		}
	}

	private boolean checkDataBase() {
		SQLiteDatabase mCheckDataBase = null;
		try {
			String mPath = DB_PATH + DB_NAME;
			File mPathFile = new File(mPath);
			if(mPathFile.exists()) {
				mCheckDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
			}
		} catch (SQLiteException mSQLiteException) {
			mCheckDataBase = null;
			Abhan.printLog("E", mSQLiteException.toString());
		}

		if (mCheckDataBase != null) {
			mCheckDataBase.close();
		}

		return mCheckDataBase != null;
	}
	
	public boolean openDataBase() throws SQLException {
		String mPath = DB_PATH + DB_NAME;
		mDataBase = SQLiteDatabase.openDatabase(mPath, null,
				SQLiteDatabase.NO_LOCALIZED_COLLATORS);
		return mDataBase != null;
	}

	@Override
	public synchronized void close() {
		if(mDataBase != null) {
			mDataBase.close();
		}
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DROP_TABLE + TABLE_NAME);
		db.execSQL(CREATE_TABLE + TABLE_NAME + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_NAME + " TEXT, "
				+ KEY_EVENT + " TEXT, "
				+ KEY_MONTH + " TEXT, "
				+ KEY_YEAR + " INTEGER, "
				+ KEY_IMAGE + " BLOB, "
				+ KEY_VERSION + " REAL" + ")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}