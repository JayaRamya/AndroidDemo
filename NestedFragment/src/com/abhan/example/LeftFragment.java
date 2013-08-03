package com.abhan.example;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class LeftFragment extends Fragment {
	
    private ListView list;
    private RadioGroup radioGrp;
    
	private ArrayList<Abhan> designationList = null;
    private LeftFragmentListAdapter leftFragmentListAdapter = null;
    
	private String SELECTED_MONTHS = "";
	private String SELECTED_DESIGNATION = "";
    
	private CallbackListener callbackListener;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
		prepareListData();
    	
    	if(designationList != null && designationList.size() > 0) {
			SELECTED_DESIGNATION = designationList.get(0).getDesignation();
			leftFragmentListAdapter = new LeftFragmentListAdapter(
					getActivity(), designationList);
    	}
    }
    
	private void prepareListData() {
		designationList = new ArrayList<Abhan>();
		designationList.clear();

		Abhan abhan = new Abhan();
		abhan.setName("Krutik");
		abhan.setAge(25);
		abhan.setDesignation("Developer");
		abhan.setCompany("No Company");
		designationList.add(abhan);

		abhan = new Abhan();
		abhan.setName("Vandit");
		abhan.setAge(26);
		abhan.setDesignation("Android Developer");
		abhan.setCompany("Godown");
		designationList.add(abhan);

		abhan = new Abhan();
		abhan.setName("Hardik");
		abhan.setAge(27);
		abhan.setDesignation("Web Developer");
		abhan.setCompany("Nescent");
		designationList.add(abhan);

		abhan = new Abhan();
		abhan.setName("Ketan");
		abhan.setAge(28);
		abhan.setDesignation("Software Developer");
		abhan.setCompany("VCS");
		designationList.add(abhan);
	}

    @Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	callbackListener = (CallbackListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_leftside, container,
				false);
        
		radioGrp = (RadioGroup) rootView.findViewById(R.id.monthsGrp);
        radioGrp.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (group.getCheckedRadioButtonId()) {
					case R.id.threeMonths:
						SELECTED_MONTHS = getString(R.string.three_months);
						break;
					case R.id.sixMonths:
						SELECTED_MONTHS = getString(R.string.six_months);
						break;
					default:
						SELECTED_MONTHS = getString(R.string.three_months);
						break;
				}
			}
		});
        
		list = (ListView) rootView.findViewById(R.id.list);
        if(designationList != null && designationList.size() > 0) {
        	list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        	list.setAdapter(leftFragmentListAdapter);
        	list.setItemChecked(0, true);
        	list.setOnItemClickListener(new DrawerListItemClickListener());
        }
        
        return rootView;
    }
    
	public void getSelectedValues() {
    	callbackListener.optionSelected(SELECTED_MONTHS, SELECTED_DESIGNATION);
    }
    
    private class DrawerListItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	((LeftFragmentListAdapter) parent.getAdapter()).setSelectedPosition(position);
			Abhan abhan = (Abhan) ((LeftFragmentListAdapter) parent
					.getAdapter()).getItem(position);
			SELECTED_DESIGNATION = abhan.getDesignation();
        	((LeftFragmentListAdapter) parent.getAdapter()).notifyDataSetChanged();
        }
    }
}