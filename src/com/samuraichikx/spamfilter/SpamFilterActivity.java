package com.samuraichikx.spamfilter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.NavUtils;

public class SpamFilterActivity extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spam_filter);
        Button newgame = (Button)findViewById(R.id.newgame);
        newgame.setOnClickListener(this);
    }
    
    public void onClick(View v)
    {
    	Intent i;
    	
    	switch (v.getId()){
    	case R.id.newgame:
    		i = new Intent (this, GameBoardActivity.class);
    		//Log.d("Whats' up", "I've been clicked");
    		startActivity(i);
    		break;
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_spam_filter, menu);
        return true;
    }

    
}
