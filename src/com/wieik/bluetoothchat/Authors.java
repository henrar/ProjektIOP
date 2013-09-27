
package com.wieik.bluetoothchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.app.TabActivity;
import android.content.Intent;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class Authors extends TabActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup the window
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setTitle(R.string.authors);
        setContentView(R.layout.authors);
        
        TabHost tabHost = getTabHost();
        
        // Tab for Photos
        TabSpec photospec = tabHost.newTabSpec("Jakub Jachym");
        // setting Title and Icon for the Tab
        photospec.setIndicator("Jakub Jachym", getResources().getDrawable(R.drawable.ic_launcher));
        Intent photosIntent = new Intent(this, FirstTab.class);
        photospec.setContent(photosIntent);
         
        // Tab for Songs
        TabSpec songspec = tabHost.newTabSpec("Bart³omiej Kumor");        
        songspec.setIndicator("Bart³omiej Kumor", getResources().getDrawable(R.drawable.bticon));
        Intent songsIntent = new Intent(this, SecondTab.class);
        songspec.setContent(songsIntent);
         
        // Tab for Videos
        TabSpec videospec = tabHost.newTabSpec("Norbert Kozera");
        videospec.setIndicator("Norbert Kozera", getResources().getDrawable(R.drawable.ic_launcher));
        Intent videosIntent = new Intent(this, ThirdTab.class);
        videospec.setContent(videosIntent);
         
        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab
        
        

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }


}
