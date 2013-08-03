package com.abhan.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

public class DBAdapter {
	private final Context mContext;
	private SQLiteDatabase mDb;
	private final DataBaseHelper mDbHelper;
	
	public DBAdapter(Context context) {
		this.mContext = context;
		mDbHelper = new DataBaseHelper(mContext);
	}

	public DBAdapter createDatabase() throws SQLException {
		try {
			mDbHelper.createDataBase();
		} catch (IOException mIOException) {
			throw new Error("UnableToCreateDatabase");
		}
		return this;
	}

	public DBAdapter open() throws SQLException {
		try {
			mDbHelper.openDataBase();
			mDbHelper.close();
			mDb = mDbHelper.getReadableDatabase();
		} catch (SQLException mSQLException) {
			Abhan.printLog("E", mSQLException.toString());
			throw mSQLException;
		}
		return this;
	}

	public void close() {
		mDbHelper.close();
	}
	
	private byte[] getBlob(final Bitmap mBitmap) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
		return byteArrayOutputStream.toByteArray();
	}
	
	public long insertValues(final AbhanObject abhanObject) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(DataBaseHelper.KEY_ID, abhanObject.getId());
		contentValues.put(DataBaseHelper.KEY_NAME, abhanObject.getName());
		contentValues.put(DataBaseHelper.KEY_EVENT, abhanObject.getNullOrString());
		contentValues.put(DataBaseHelper.KEY_MONTH, abhanObject.getEventMonth());
		contentValues.put(DataBaseHelper.KEY_YEAR, abhanObject.getEventYear());
		final Bitmap bitmap = abhanObject.getEventImage();
		if (bitmap != null) {
			contentValues.put(DataBaseHelper.KEY_IMAGE, getBlob(bitmap));
		}
		contentValues.put(DataBaseHelper.KEY_VERSION, abhanObject.getVersion());
		return mDb.insert(DataBaseHelper.TABLE_NAME, null, contentValues);
	}
	
	public int getDataCount() {
		int count = -1;
		Cursor cursor = mDb.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_NAME, null);
		if (cursor != null) {
			count = cursor.getCount();
		}
		cursor.close();
		return count;
	}

	public void readDataTypes(final int sdkVersion) {
		Abhan.printLog("W", "SDK Version: " + sdkVersion);
		if (sdkVersion < 11) {
			readDataTypePreHoneyComb();
		} else if (sdkVersion >= 11) {
			readDataTypeHoneyCombPost();
		}
	}

	public void readDataTypePreHoneyComb() {
		Cursor cursor = mDb.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_NAME, null);
		final int count = cursor.getCount();
		if (cursor != null && count > 0) {
			cursor.moveToFirst();
			for (int abhan = 0; abhan < count; abhan++) {
				String[] columnNames = cursor.getColumnNames();
				for (String name : columnNames) {
					Cursor typeCursor = mDb.rawQuery("SELECT TYPEOF (" + name + ") FROM "
							+ DataBaseHelper.TABLE_NAME, null);
					final int typeCount = typeCursor.getCount();
					if (typeCursor != null && typeCount > 0) {
						typeCursor.moveToFirst();
						final String columnTypeString = typeCursor.getString(0).toUpperCase();
						Abhan.printLog("I", name + " Is " + " TYPEOF " + columnTypeString);
						if (columnTypeString.equalsIgnoreCase("TEXT")) {
							Abhan.printLog("D",
									"Value " + cursor.getString(cursor.getColumnIndex(name)));
						} else if (columnTypeString.equalsIgnoreCase("INTEGER")) {
							Abhan.printLog("D",
									"Value " + cursor.getInt(cursor.getColumnIndex(name)));
						} else if (columnTypeString.equalsIgnoreCase("REAL")) {
							Abhan.printLog("D",
									"Value " + cursor.getDouble(cursor.getColumnIndex(name)));
						} else if (columnTypeString.equalsIgnoreCase("BLOB")) {
							Abhan.printLog("D",
									"Value " + cursor.getBlob(cursor.getColumnIndex(name)));
						}
					}
					typeCursor.close();
				}
				cursor.moveToNext();
				Abhan.printLog("V", "-------------------------------------------------");
			}
		}

		cursor.close();
	}
	
	public void readDataTypeHoneyCombPost() {
		Cursor cursor = mDb.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_NAME, null);
		final int count = cursor.getCount();
		if (cursor != null && count > 0) {
			cursor.moveToFirst();
			final int columnCount = cursor.getColumnCount();
			do {
				for (int abhan = 0; abhan < columnCount; abhan++) {
					final int columnType = cursor.getType(abhan);
					final String columnName = cursor.getColumnName(abhan);
					if (columnType == Cursor.FIELD_TYPE_NULL) {
						Abhan.printLog("D", columnName + " Is TYPEOF NULL " + " Value: NULL");
					} else if (columnType == Cursor.FIELD_TYPE_INTEGER) {
						Abhan.printLog("D", columnName + " Is TYPEOF INTEGER "
								+ " Value: " + cursor.getInt(abhan));
					} else if (columnType == Cursor.FIELD_TYPE_STRING) {
						Abhan.printLog("D", columnName + " Is TYPEOF STRING "
								+ " Value: " + cursor.getString(abhan));
					} else if (columnType == Cursor.FIELD_TYPE_BLOB) {
						Abhan.printLog("D", columnName + " Is TYPEOF BLOB "
								+ " Value: " + cursor.getBlob(abhan));
					} else if (columnType == Cursor.FIELD_TYPE_FLOAT) {
						Abhan.printLog("D", columnName + " Is TYPEOF REAL "
								+ " Value: " + cursor.getDouble(abhan));
					}
				}
				Abhan.printLog("V", "-------------------------------------------------");
			} while (cursor.moveToNext());
		}
		cursor.close();
	}
}