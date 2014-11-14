package com.example.abbeyapp;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.R.*;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import slidingMenu.model.NavDrawerItem;
import slidingMenu.adapter.NavDrawerListAdapter;
import java.util.*;
import android.content.*;
import slidingMenu.model.*;
import android.app.Fragment;
import android.app.FragmentManager;

public class MainActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	
	//nav drawer title
	private CharSequence mDrawerTitle;
	
	//used to store app title
	private CharSequence mTitle;
	
	//slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	
	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mTitle = mDrawerTitle = getTitle();
        
        //load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        
        //nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        
        navDrawerItems = new ArrayList<NavDrawerItem>();
        
        // adding nav drawer items to array
        //Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        
       
        
        
        //-------Add others here---------------
        
        
        
        //Recycle Typed Array
        navMenuIcons.recycle();
        
        //setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);
        
        //enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
       
        
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, //nav menu toggle icon
        		R.string.app_name, //nav drawer open
        		R.string.app_name // Nav drawer close
        		){
        	public void onDrawerClosed(View view)
        	{
        		getActionBar().setTitle(mTitle);
        		
        		invalidateOptionsMenu();
        	}
        	public void onDrawerOpened(View drawerView)
        	{
        		getActionBar().setTitle(mDrawerTitle);
        		invalidateOptionsMenu();
        	}
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
/*        if(savedInstanceState == null)
        {
        	//on first time display view for first time nav item
        	displayView(0);
        }*/
        
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
    }

    private class SlideMenuClickListener implements ListView.OnItemClickListener
    {
    	
    	@Override
    	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    	{
    		displayView(position);
    	}
    	
    	private void displayView(int position)
    	{
    		Fragment fragment = null;
    		switch (position)
    		{
    		case 0:
    			fragment = new HomeFragment();
    			break;
    		case 1:
    			fragment = new MonasticCommunityFragment();
    			break;
    		case 2:
    			fragment = new News();
    			break;
    		case 3:
    			fragment = new UpcomingEvents();
    			break;
    		case 4:
    			fragment = new TwitterFeedFragment();
    			break;
    		case 5:
    			fragment = new MapFragment();
    			break;
    			default:
    				break;
    		}
    		if(fragment != null)
    		{
    			FragmentManager fragmentManager = getFragmentManager();
    			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
    			
    			mDrawerList.setItemChecked(position, true);
    			mDrawerList.setSelection(position);
    			setTitle(navMenuTitles[position]);
    			mDrawerLayout.closeDrawer(mDrawerList);
    		}
    		else
    		{
    			Log.e("MainActivity", "Error in creating fragment");
    			
    		}
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       if(mDrawerToggle.onOptionsItemSelected(item))
       {
    	   return true;
       }
       Log.i("click", item.getItemId() + "");
       switch (item.getItemId())
       {
    	   
       case R.id.action_settings:
    	   return true;
    	   default:
    		   return super.onOptionsItemSelected(item);
       }
    }
    
    //Called when invalidateOptionsMenu() is triggered
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
    	//if nav drawer is opened, hide the action items
    	boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
    	menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
    	return super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public void setTitle(CharSequence title)
    {
    	mTitle = title;
    	getActionBar().setTitle(mTitle);
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
    	super.onPostCreate(savedInstanceState);
    	mDrawerToggle.syncState();
    	
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
    	super.onConfigurationChanged(newConfig);
    	mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    
}
