package com.abhan.example;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

public class AbhanActivity extends Activity {
	private TextView txtStatus = null;
	private Abhan mAbhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abhan);
		mAbhan = (Abhan) this.getApplicationContext();
		txtStatus = (TextView) findViewById(R.id.txtStatus);
		if (mAbhan.getDatabase().getDataCount() <= 0) {
			prepareData();
		}
		mAbhan.getDatabase().readDataTypes(mAbhan.getVersion());
		setStatus();
	}
	
	private void prepareData() {
		final Bitmap mBitmap = BitmapFactory.decodeResource(
				getResources(), R.drawable.ic_launcher);
		AbhanObject abhanObject = new AbhanObject();
		abhanObject.setId(1);
		abhanObject.setName("Cupcake");
		abhanObject.setNullOrString("");
		abhanObject.setEventMonth("January");
		abhanObject.setEventYear(2006);
		abhanObject.setEventImage(mBitmap);
		abhanObject.setVersion(1.5);
		mAbhan.getDatabase().insertValues(abhanObject);

		abhanObject = new AbhanObject();
		abhanObject.setId(2);
		abhanObject.setName("Donut");
		abhanObject.setNullOrString(null);
		abhanObject.setEventMonth("February");
		abhanObject.setEventYear(2007);
		abhanObject.setEventImage(null);
		abhanObject.setVersion(1.6);
		mAbhan.getDatabase().insertValues(abhanObject);

		abhanObject = new AbhanObject();
		abhanObject.setId(3);
		abhanObject.setName("Eclair");
		abhanObject.setNullOrString(null);
		abhanObject.setEventMonth("March");
		abhanObject.setEventYear(2008);
		abhanObject.setEventImage(mBitmap);
		abhanObject.setVersion(2.1);
		mAbhan.getDatabase().insertValues(abhanObject);

		abhanObject = new AbhanObject();
		abhanObject.setId(4);
		abhanObject.setName("Froyo");
		abhanObject.setNullOrString("");
		abhanObject.setEventMonth("April");
		abhanObject.setEventYear(2009);
		abhanObject.setEventImage(null);
		abhanObject.setVersion(2.2);
		mAbhan.getDatabase().insertValues(abhanObject);

		abhanObject = new AbhanObject();
		abhanObject.setId(5);
		abhanObject.setName("Gingerbread");
		abhanObject.setNullOrString("Abhan");
		abhanObject.setEventMonth("May");
		abhanObject.setEventYear(2010);
		abhanObject.setEventImage(mBitmap);
		abhanObject.setVersion(2.3);
		mAbhan.getDatabase().insertValues(abhanObject);

		abhanObject = new AbhanObject();
		abhanObject.setId(6);
		abhanObject.setName("HoneyComb");
		abhanObject.setNullOrString("Abhan");
		abhanObject.setEventMonth("June");
		abhanObject.setEventYear(2011);
		abhanObject.setEventImage(null);
		abhanObject.setVersion(3.0);
		mAbhan.getDatabase().insertValues(abhanObject);

		abhanObject = new AbhanObject();
		abhanObject.setId(7);
		abhanObject.setName("Ice Cream Sandwich");
		abhanObject.setNullOrString(null);
		abhanObject.setEventMonth("July");
		abhanObject.setEventYear(2012);
		abhanObject.setEventImage(mBitmap);
		abhanObject.setVersion(4.0);
		mAbhan.getDatabase().insertValues(abhanObject);

		abhanObject = new AbhanObject();
		abhanObject.setId(8);
		abhanObject.setName("Jelly Bean");
		abhanObject.setNullOrString("Abhan");
		abhanObject.setEventMonth("August");
		abhanObject.setEventYear(2013);
		abhanObject.setEventImage(null);
		abhanObject.setVersion(4.1);
		mAbhan.getDatabase().insertValues(abhanObject);
	}
	
	private void setStatus() {
		txtStatus.setText("Please Check Logcat Output");
    }
	
	@Override
	protected void onStop() {
		super.onStop();
		mAbhan.closeDataBase();
	}
}