package com.abhan.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import com.abhan.example.adapters.AbhanAdapter;
import com.abhan.example.models.AbhanObject;
import com.abhan.example.sideindexers.IndexableListView;

public class AbhanActivity extends Activity {

	private static final String TAG = AbhanActivity.class.getSimpleName();
	private EditText edtSearch = null;
	private IndexableListView abhanListView = null;
	private AbhanAdapter abhanAdapter = null;
	private ArrayList<AbhanObject> arrayList = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abhan);
        
        prepareList();
        initComponent();
        
        if(arrayList != null && arrayList.size() > 0) {
        	abhanAdapter = new AbhanAdapter(AbhanActivity.this, arrayList);
        	abhanListView.setFastScrollEnabled(true);
        	abhanListView.setAdapter(abhanAdapter);
        	abhanListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					android.util.Log.w(TAG, "OnItemClickListener. Do Your Stuff Here.");
				}
			});
        	
        	edtSearch.addTextChangedListener(new TextWatcher() {
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					abhanAdapter.getFilter().filter(s.toString().trim());
				}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {}
				
				@Override
				public void afterTextChanged(Editable s) {}
			});
        }
    }
    
    private void prepareList() {
    	arrayList = new ArrayList<AbhanObject>();
    	arrayList.clear();
    	AbhanObject abhanObject = new AbhanObject();
    	abhanObject.setName("Abhan");
    	abhanObject.setCount(13);
    	arrayList.add(abhanObject);
    	abhanObject = new AbhanObject();
    	abhanObject.setName("Abhan A");
    	abhanObject.setCount(1);
    	arrayList.add(abhanObject);
    	abhanObject = new AbhanObject();
    	abhanObject.setName("Abhan C");
    	abhanObject.setCount(2);
    	arrayList.add(abhanObject);
    	abhanObject = new AbhanObject();
    	abhanObject.setName("Abhan F");
    	abhanObject.setCount(11);
    	arrayList.add(abhanObject);
    	abhanObject = new AbhanObject();
    	abhanObject.setName("Abhan B");
    	abhanObject.setCount(3);
    	arrayList.add(abhanObject);
    	abhanObject = new AbhanObject();
    	abhanObject.setName("Abhan D");
    	abhanObject.setCount(4);
    	arrayList.add(abhanObject);
    	abhanObject = new AbhanObject();
    	abhanObject.setName("Abhan G");
    	abhanObject.setCount(7);
    	arrayList.add(abhanObject);
    	abhanObject = new AbhanObject();
    	abhanObject.setName("Abhan E");
    	abhanObject.setCount(5);
    	arrayList.add(abhanObject);
    	abhanObject = new AbhanObject();
    	abhanObject.setName("Abhan H");
    	abhanObject.setCount(6);
    	arrayList.add(abhanObject);
    	abhanObject = new AbhanObject();
    	abhanObject.setName("Abhan I");
    	abhanObject.setCount(8);
    	arrayList.add(abhanObject);
    	abhanObject = new AbhanObject();
    	abhanObject.setName("B Abhan A");
    	abhanObject.setCount(21);
    	arrayList.add(abhanObject);
    	abhanObject = new AbhanObject();
    	abhanObject.setName("B Abhan AA");
    	abhanObject.setCount(22);
    	arrayList.add(abhanObject);
    	abhanObject = new AbhanObject();
    	abhanObject.setName("B Abhan B");
    	abhanObject.setCount(23);
    	arrayList.add(abhanObject);
    	abhanObject = new AbhanObject();
    	abhanObject.setName("D Abhan A");
    	abhanObject.setCount(31);
    	arrayList.add(abhanObject);
    	Collections.sort(arrayList, new AbhanComparator());
    }
    
    private class AbhanComparator implements Comparator<AbhanObject> {
		@Override
		public int compare(AbhanObject object1, AbhanObject object2) {
			return object1.getName().compareTo(object2.getName());
		}
	}
    
    private void initComponent() {
    	edtSearch = (EditText) findViewById(R.id.edtSearch);
    	abhanListView = (IndexableListView) findViewById(R.id.abhanList);
    }
}