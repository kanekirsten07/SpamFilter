package com.samuraichikx.spamfilter;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class OptionsActivity extends PreferenceActivity {

	private static final String numEnvelopes = "Number_Of_Envelopes";
	private static final String numEnvelopes_Default="10";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        addPreferencesFromResource(R.xml.optionsstuff);
        
    }
    
    public static String getFrequency(Context context)
    {
    	return PreferenceManager.getDefaultSharedPreferences(context).
    			getString(numEnvelopes, numEnvelopes_Default);
    }

    
}
