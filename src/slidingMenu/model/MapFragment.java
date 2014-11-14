package slidingMenu.model;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Activity {
	public MapFragment(){}
	
	private GoogleMap map;
	private final LatLng LOCATION_ABBEY = new LatLng(45.580071, -94.392226);
	private final LatLng LOCATION_COLLEGEVILLE= new LatLng(45.594138, -94.363932);
	private final LatLng LOCATION_STJOHNS = new LatLng(45.584434, -94.393931);
	
	//Comment out?
		@Override
		protected void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			//setContentView(android.R.layout.activity_main);
			
			map =((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
			map.addMarker(new MarkerOptions().position(LOCATION_ABBEY).title("Abbey"));
		}
	
	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
	{
		View rootView = inflater.inflate(R.layout.fragment_map, container, false);
		return rootView;
	}
*/
	public void onClick_StJohns(View v){
		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_STJOHNS, 20);
		map.animateCamera(update);
	}
	
	public void onClick_Abbey(View v){
		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_ABBEY, 20);
		map.animateCamera(update);
	}
	
	public void onClick_Collegeville(View v){
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_COLLEGEVILLE, 17);
		map.animateCamera(update);
	}
}
