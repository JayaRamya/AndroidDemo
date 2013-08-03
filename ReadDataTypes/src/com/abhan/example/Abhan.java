package com.abhan.example;

import android.app.Application;

public class Abhan extends Application {
	public static final String TAG = "Abhan";
	public static final boolean DEBUG = true;
	private int mVersion = -1;
	private DBAdapter mDbAdapter = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		setVersion(android.os.Build.VERSION.SDK_INT);
		if (mDbAdapter == null) {
			mDbAdapter = new DBAdapter(getBaseContext());
			mDbAdapter.createDatabase();
		}
	}
	
	public int getVersion() {
		return mVersion;
	}
	
	public void setVersion(int mVersion) {
		this.mVersion = mVersion;
	}
	
	public DBAdapter getDatabase() {
		mDbAdapter.open();
		return mDbAdapter;
	}
	
	public void closeDataBase() {
		mDbAdapter.close();
	}
	
	public static void printLog(final String type, final String message) {
		if (DEBUG) {
			if (type.equalsIgnoreCase("V")) {
				android.util.Log.v(TAG, message);
			} else if (type.equalsIgnoreCase("D")) {
				android.util.Log.d(TAG, message);
			} else if (type.equalsIgnoreCase("I")) {
				android.util.Log.i(TAG, message);
			} else if (type.equalsIgnoreCase("W")) {
				android.util.Log.w(TAG, message);
			} else if (type.equalsIgnoreCase("E")) {
				android.util.Log.e(TAG, message);
			}
		}
	}

}