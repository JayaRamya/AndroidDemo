package com.abhan.example.adapters;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.abhan.example.R;
import com.abhan.example.models.AbhanObject;
import com.abhan.example.sideindexers.StringMatcher;

public class AbhanAdapter extends BaseAdapter implements Filterable, SectionIndexer {

	private static final String TAG = AbhanAdapter.class.getSimpleName();
	private final LayoutInflater inflater;
	private ArrayList<AbhanObject> arrayList;
	private ArrayList<AbhanObject> displayArrList;
	
	private final String mSections = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public AbhanAdapter(Context context, ArrayList<AbhanObject> arrayList) {
		inflater = LayoutInflater.from(context);
		this.arrayList = arrayList;
		this.displayArrList = arrayList;
	}
	
	private static class ViewHolder {
		ImageView imgView;
		TextView txtName;
		TextView txtCount;
	}
	
	@Override
	public int getPositionForSection(int section) {
		for (int i = section; i >= 0; i--) {
			for (int j = 0; j < getCount(); j++) {
				if (i == 0) {
					for (int k = 0; k <= 9; k++) {
						if (StringMatcher.match(String.valueOf(getItem(j)
								.getName().charAt(0)), String.valueOf(k)))
							return j;
					}
				} else {
					if (StringMatcher
							.match(String.valueOf(getItem(j).getName()
									.charAt(0)), String.valueOf(mSections
									.charAt(i)))) return j;
				}
			}
		}
		return 0;
	}
	
	@Override
	public int getSectionForPosition(int position) {
		return 0;
	}
	
	@Override
	public Object[] getSections() {
		String[] sections = new String[mSections.length()];
		for (int i = 0; i < mSections.length(); i++)
			sections[i] = String.valueOf(mSections.charAt(i));
		return sections;
	}
	
	@Override
	public int getCount() {
		if(displayArrList != null && displayArrList.size() > 0) {
			return displayArrList.size();
		}
		return 0;
	}

	@Override
	public AbhanObject getItem(int position) {
		if(displayArrList != null && displayArrList.size() > 0) {
			return displayArrList.get(position);
		}
		return null;
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
		final ViewHolder holder;
		AbhanObject abhanObject = getItem(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.activity_rowitem, parent, false);
			holder = new ViewHolder();
			holder.imgView = (ImageView) convertView
					.findViewById(R.id.imgView);
			holder.txtName = (TextView) convertView
					.findViewById(R.id.txtName);
			holder.txtCount = (TextView) convertView
					.findViewById(R.id.txtCount);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(abhanObject != null) {
			holder.txtName.setText(abhanObject.getName());
			holder.txtCount.setText(String.valueOf(abhanObject.getCount()));
			holder.imgView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					android.util.Log.i(TAG, "ImageView Clicked. Do Your Stuff Here.");
				}
			});
			
			if(position % 2 == 1) {
				convertView.setBackgroundColor(Color.parseColor("#AA08A3F5"));
				holder.txtCount.setTextColor(Color.parseColor("#AAFFFFFF"));
			} else {
				convertView.setBackgroundColor(Color.parseColor("#AAE8E8E8"));
				holder.txtCount.setTextColor(Color.parseColor("#AA2E393F"));
			}
		}
		
		return convertView;
	}
	
	@Override
	public Filter getFilter() {
		Filter filter = new Filter() {
			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				displayArrList = (ArrayList<AbhanObject>) results.values;
				notifyDataSetChanged();
			}
			
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults results = new FilterResults();
				ArrayList<AbhanObject> filteredArrList = new ArrayList<AbhanObject>();
				if (arrayList == null) {
					arrayList = new ArrayList<AbhanObject>(displayArrList);
				}
				
				if (constraint == null || constraint.length() == 0) {
					results.count = arrayList.size();
					results.values = arrayList;
				} else {
					constraint = constraint.toString().toLowerCase().trim();
					final int arrayListSize = arrayList.size();
					for (int i = 0; i < arrayListSize; i++) {
						String data = arrayList.get(i).getName();
						if (data.toLowerCase()
								.startsWith(constraint.toString())) {
							filteredArrList.add(arrayList.get(i));
						}
					}
					results.count = filteredArrList.size();
					results.values = filteredArrList;
				}
				return results;
			}
		};
		return filter;
	}
}