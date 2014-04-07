package ch.p1gu.spacelama;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.analytics.tracking.android.EasyTracker;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;

public class MainActivity extends AndroidApplication{
	

	
	private final String ADCODE = "ca-app-pub-6054400811554027/1341487998";
	protected AdView adView; //small ad
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        AdBuddiz.getInstance().cacheAds(this);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        RelativeLayout layout = new RelativeLayout(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //no title is needed
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        
        adView = new AdView(this, AdSize.SMART_BANNER, ADCODE); // Put in your secret key here
        
        View gameView = initializeForView(new SpaceLama(), cfg);
        AdRequest adRequest = new AdRequest();

        adView.loadAd(adRequest);
        
        layout.addView(gameView);
        
        RelativeLayout.LayoutParams adParams = 
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
        
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layout.addView(adView, adParams);
        setContentView(layout);
        adView.setVisibility(View.VISIBLE);
    }
    
    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
    	 AdBuddiz.getInstance().onStart(this);
    	 AdBuddiz.getInstance().showAd();
    	EasyTracker.getInstance(this).activityStart(this);  // Add this method.
    }
    
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop();

    	EasyTracker.getInstance(this).activityStop(this);  // Add this method.
    }

}
