package com.abhan.example;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

public class LeftFragmentListAdapter extends BaseAdapter {
	private final LayoutInflater inflater;
	private ArrayList<Abhan> arrayList;
	private int selectedPosition = 0;
	
	public LeftFragmentListAdapter(Context context, ArrayList<Abhan> arrayList) {
		this.arrayList = arrayList;
		inflater = LayoutInflater.from(context);
	}
	
	public static class ViewHolder {
		RadioButton radio;
	}
	
	public void setSelectedPosition(int position){
	    selectedPosition = position;
	}
	
	public int getSelectedPosition() {
		return selectedPosition;
	}
	
	@Override
	public int getCount() {
		return arrayList.size() > 0 ? arrayList.size() : 0;
	}

	@Override
	public Abhan getItem(int position) {
		return arrayList.size() > 0 ? arrayList.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		final Abhan abhan = getItem(position);
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.activity_list_row, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.radio = (RadioButton) convertView.findViewById(R.id.radioBtnList);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		if (abhan != null) {
			viewHolder.radio.setText(abhan.getName());
			if (getSelectedPosition() == position) {
				viewHolder.radio.setChecked(true);
			} else {
				viewHolder.radio.setChecked(false);
			}
		}
		
		return convertView;
	}
}