package com.abhan.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private long enQueue;
	private DownloadManager downloadManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
		Uri resourceUri = Uri.parse("YOUR HTTP LINK TO DOWNLOAD");
		DownloadManager.Request request = new DownloadManager.Request(
				resourceUri);
		request.setAllowedNetworkTypes(Request.NETWORK_MOBILE
				| Request.NETWORK_WIFI);
		request.setAllowedOverRoaming(false);
		enQueue = downloadManager.enqueue(request);

		registerReceiver(receiver, new IntentFilter(
				DownloadManager.ACTION_DOWNLOAD_COMPLETE));
	}

	private final BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle extras = intent.getExtras();
			long doneDownloadId = extras
					.getLong(DownloadManager.EXTRA_DOWNLOAD_ID);
			downloadStatus(doneDownloadId);
		}
	};

	@Override
	public void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
		downloadManager = null;
	}

	private void downloadStatus(long doneDownloadId) {
		DownloadManager.Query query = new DownloadManager.Query();
		query.setFilterById(enQueue);
		Cursor cursor = downloadManager.query(query);
		if (cursor.moveToFirst()) {
			int status = cursor.getInt(cursor
					.getColumnIndex(DownloadManager.COLUMN_STATUS));
			Log.d(TAG, "Status Check: " + status);
			switch (status) {
				case DownloadManager.STATUS_PAUSED:
					Log.i(TAG, "Status Paused");
					break;
				case DownloadManager.STATUS_PENDING:
					Log.i(TAG, "Status Pending");
					break;
				case DownloadManager.STATUS_RUNNING:
					Log.i(TAG, "Status Running");
					break;
				case DownloadManager.STATUS_SUCCESSFUL:
					try {
						final String filePath = Environment
								.getExternalStorageDirectory()
								+ File.separator
								+ "Abhan";
						new File(filePath).mkdirs();
						ParcelFileDescriptor file = downloadManager
								.openDownloadedFile(enQueue);
						FileInputStream fileInputStream = new ParcelFileDescriptor.AutoCloseInputStream(
								file);
						File outputFile = new File(filePath, "YOUR_NEW_FILENAME");
						FileOutputStream fileOutputStream = new FileOutputStream(
								outputFile);
						byte[] buffer = new byte[1024];
						int length;
						while ((length = fileInputStream.read(buffer)) != -1) {
							fileOutputStream.write(buffer, 0, length);
						}
						fileOutputStream.flush();
						fileOutputStream.close();
						fileInputStream.close();
					} catch (Exception e) {
						Log.e(TAG, "Exception: " + e.toString());
					}
					Log.i(TAG, "IDs:enQueue " + enQueue + " == "
							+ doneDownloadId);
					if (enQueue == doneDownloadId) {
						Log.i(TAG, "Status Success");
					}
					break;
				case DownloadManager.STATUS_FAILED:
					downloadManager.remove(enQueue);
					break;
			}
		}
	}
}