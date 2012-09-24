package com.samuraichikx.spamfilter;





import java.util.ArrayList;
import java.util.Random;





import android.os.Bundle;
import android.app.Activity;
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
	Random  ycoordinate = new Random();
	private int envelope_size, envelope_size_x;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        
        emails = new ArrayList<Emails>();
        _gameboardView = (GameBoardView)findViewById(R.id.game_board_view); 
        _gameboardView.setController(this);
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
    	int ycoord = ycoordinate.nextInt(maxy);
    	int envelopenumber = envelope.nextInt(10);
    	
    	 email = BitmapFactory.decodeResource(getResources(), R.drawable.envelope);
    	 envelope_size = email.getHeight();
    	 envelope_size_x = email.getWidth();
		 Emails e = new Emails(10, xcoord, ycoord, email);
		 emails.add(e);
    	
    }
    /*
     * Shred emails if either they are lined up wih the Shredder Guy or if they reach the end of the screen. Update them to all fall otherwise.
     */
    public void shredEmails(ShredderGuy sg)
    {
    	for(Emails e: emails)
    	{
    		e.fall();
    		if(e.x()>= sg.getX() && e.y()>=sg.getY())
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
