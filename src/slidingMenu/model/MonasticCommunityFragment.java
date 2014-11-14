package slidingMenu.model;

import com.example.abbeyapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MonasticCommunityFragment extends Fragment {
	public MonasticCommunityFragment(){}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
	{
		View rootView = inflater.inflate(R.layout.fragment_monasticcommunity, container, false);
		return rootView;
	}
}
