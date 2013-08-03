package com.abhan.example;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class RightFragment extends Fragment {
	
	private String months, desg;
	private TextView txtName, txtDesignation;
	private Button btnStart;
	private ActivityChangeCallback activityChangeCallback;

	public RightFragment() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		activityChangeCallback = (ActivityChangeCallback) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_rightside,
				container, false);
		
		txtName = (TextView) rootView.findViewById(R.id.txtName);
		txtDesignation = (TextView) rootView.findViewById(R.id.txtDesignation);
		btnStart = (Button) rootView.findViewById(R.id.btnStart);

		months = getArguments().getString(AbhanActivity.ARG_NAME);
		desg = getArguments().getString(AbhanActivity.ARG_DESG);
		
		if (months.trim().length() > 0 && desg.trim().length() > 0) {
			txtDesignation.setVisibility(View.VISIBLE);
			btnStart.setBackgroundColor(getActivity().getResources().getColor(
					R.color.ablue));
			btnStart.setEnabled(true);
			txtName.setText(months);
			txtDesignation.setText(desg);
			btnStart.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					activityChangeCallback.changeActivity(desg);
				}
			});
		} else {
			txtName.setText("No Data");
			txtDesignation.setVisibility(View.INVISIBLE);
			btnStart.setBackgroundColor(getActivity().getResources().getColor(
					R.color.darkslate));
			btnStart.setEnabled(false);
		}
		return rootView;
	}
}