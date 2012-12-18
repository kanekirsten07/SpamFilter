package com.samuraichikx.spamfilter;





import java.util.ArrayList;
import java.util.Random;





import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class GameBoardActivity extends Activity {
	private ArrayList<Emails> emails;
	private GameBoardView _gameboardView;
	private Bitmap email;
	Random envelope = new Random();
	Random  xcoordinate = new Random();
	
	private int envelope_size, envelope_size_x;
	private int timekeeper = 0, shredderanimation = 1;
	Context context;
	SharedPreferences prefs;
	Editor editor;
	private boolean gameover = false;
	
	DialogInterface.OnClickListener yesnobox = new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch(which){
			case DialogInterface.BUTTON_POSITIVE:
				_gameboardView.setScores(prefs.getInt("TempScore", 0), prefs.getInt("CurrentScore", 0));
				break;
			case DialogInterface.BUTTON_NEGATIVE:
				break;
			}
			
		}
	};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        emails = new ArrayList<Emails>();
        _gameboardView = (GameBoardView)findViewById(R.id.game_board_view); 
        _gameboardView.setController(this);
        context = _gameboardView.getContext();
        prefs = context.getSharedPreferences("MyPrefsKey", 0);
        editor = prefs.edit();
        if(prefs.getBoolean("HasSavedFile",false) == true)
        {
        	builder.setMessage("You have a saved game, would you like to continue?").setPositiveButton("Yes", yesnobox).setNegativeButton("No", yesnobox).show();
        	Log.d("save", "true");
        }else
        {
        	Log.d("save", "false");
        }
       // _gameboardView.setMode(GameBoardView.RUNNING);
    }
    
    /*public void printToLog(){
    	Log.d("pointer test", "success");
    }
    */
    
    public ArrayList<Emails> getEmails()
    {
    	return emails;
    }
    
   
    
    public void spawnEmails(int maxx, int maxy)
    {
    	int xcoord = xcoordinate.nextInt(maxx);
    	int ycoord = 30;
    	int envelopenumber = envelope.nextInt(7);
    	String spawn = OptionsActivity.getFrequency(context);
    	int spawnrate =Integer.parseInt( spawn);
    	Emails e = null;
    	switch(shredderanimation)
    	{
    	case(1):
    		_gameboardView.setShredder(BitmapFactory.decodeResource(getResources(), R.drawable.shredderguy1));
    		break;
    	case(2):
    		_gameboardView.setShredder(BitmapFactory.decodeResource(getResources(), R.drawable.shredderguy2));
    		break;
    	case(3):
    		_gameboardView.setShredder(BitmapFactory.decodeResource(getResources(), R.drawable.shredderguy3));
    		shredderanimation=0;
			break;
    	}
    	
    	if(timekeeper % (5*spawnrate) == 0){
    		//Log.d("option value",spawn );
    		
    		switch(envelopenumber){
    		case(0):
    			email = BitmapFactory.decodeResource(getResources(), R.drawable.envelope);
    			e = new Emails(-40, xcoord, ycoord, email);
    			break;
    		case(1):
    			email = BitmapFactory.decodeResource(getResources(), R.drawable.envelope2);
    			 e = new Emails(-50, xcoord, ycoord, email);
    			 break;
    		case(2):
    			email = BitmapFactory.decodeResource(getResources(), R.drawable.envelope3);
    			 e = new Emails(20, xcoord, ycoord, email);
    			 break;
    		case(3):
    			email = BitmapFactory.decodeResource(getResources(), R.drawable.envelope4);
    			 e = new Emails(-20, xcoord, ycoord, email);
    			 break;
    		case(4):
    			email = BitmapFactory.decodeResource(getResources(), R.drawable.envelope6);
    			 e = new Emails(50, xcoord, ycoord, email);
    			 break;
    		case(5):
    			email = BitmapFactory.decodeResource(getResources(), R.drawable.envelope5);
			 	e = new Emails(30, xcoord, ycoord, email);
			 	break;
    		case(6):
    			email = BitmapFactory.decodeResource(getResources(), R.drawable.envelope7);
			 	e = new Emails(60, xcoord, ycoord, email);
			 	break;
    		}
    		
			if(!(e== null)){
				envelope_size = email.getHeight();
				envelope_size_x = email.getWidth();
			emails.add(e);
			}
    		timekeeper++;
    	}
    	shredderanimation++;
    	timekeeper++;
    }
  
    public boolean isgameover()
    {
    	return gameover;
    }
    public void gameover()
    {
    	gameover=true;
    	for(int i = 0; i<emails.size();i++)
    	{
    		emails.get(i).flag();
    	}
    	int newscore=_gameboardView.getScore();
    	if(prefs.getInt("HighScore", 0)< newscore)
    	{
    		editor.putInt("HighScore", newscore);
    		editor.commit();
    	}
    	
    }
    /* Saves the players current score and overall high score for when the player returns to the game */
    @Override
    public void onBackPressed(){
    	Log.d("Back", "Pressed");
    	if(!this.gameover)
    	{
    		editor.putBoolean("HasSavedFile", true);
    		editor.putInt("TempScore", _gameboardView.getScore());
    		editor.putInt("CurrentScore", _gameboardView.getcurrentScore());
    		editor.commit();
    	}else
    	{
    		editor.putBoolean("HasSavedFile", false);
    		editor.commit();
    	}
    	finish();
    }
    /*
     * Shred emails if either they are lined up wih the Shredder Guy or if they reach the end of the screen. Update them to all fall otherwise.
     */
    public void shredEmails(ShredderGuy sg)
    {
    	for(Emails e: emails)
    	{
    		e.fall();
    		if(e.x()>= sg.getX() && e.x() <= sg.getX()+sg.getWidth() && e.y()>=sg.getY() && e.y() <= sg.getY()+sg.getHeight())
    		{
    			e.flag();
    			
    		}
    	}
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_game_board, menu);
        return true;
    }

    
}
