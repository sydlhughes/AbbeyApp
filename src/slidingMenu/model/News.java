package slidingMenu.model;

import com.example.abbeyapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class News extends Fragment {
	public News(){}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
	{
		View rootView = inflater.inflate(R.layout.fragment_news, container, false);
		return rootView;
	}
}
