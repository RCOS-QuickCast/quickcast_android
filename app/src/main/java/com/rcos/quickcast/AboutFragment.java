package com.rcos.quickcast;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutFragment extends Fragment {

    public AboutFragment() {
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_about, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

//	@Override
//	public void onAttach(Activity activity) {
//		super.onAttach(activity);
//		((HubActivity) activity).onSectionAttached(3);
//	}
}