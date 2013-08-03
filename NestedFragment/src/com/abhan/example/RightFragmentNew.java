package com.abhan.example;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RightFragmentNew extends Fragment {
	
	private String passedValue;
	private TextView txtPassed;

	public RightFragmentNew() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_rightside_new,
				container, false);
		
		txtPassed = (TextView) rootView.findViewById(R.id.txtPassed);

		passedValue = getArguments().getString(AbhanActivity.ARG_NAME);
		
		if (passedValue.trim().length() > 0) {
			txtPassed.setText(passedValue);
		} else {
			txtPassed.setText("No Data");
		}
		return rootView;
	}
}