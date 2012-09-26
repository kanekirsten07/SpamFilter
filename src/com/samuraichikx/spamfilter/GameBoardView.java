package com.samuraichikx.spamfilter;






import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.support.v4.app.NavUtils;

public class GameBoardView extends View implements OnTouchListener{
	
	Paint background = new Paint();
	ShredderGuy s = new ShredderGuy(150, 500);
	private int maxx, maxy;
	private int score= 0;
	private int temphighscore = 0;
	public static final int PAUSE = 0;
    public static final int RUNNING = 1;
	ArrayList<Emails> drawemails;
	private int refresh = 100;
	private boolean paused = false;
	private Bitmap shredderguy;
	private GameBoardActivity gb;
	Canvas canvas;
    public GameBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		s.setHeight( 75);
		 s.setWidth( 100);
		 
		initGameBoardView();
	}
    
    public void setShredder(Bitmap bm)
    {
    	
    	shredderguy = bm;
    }
    
    public void setController(GameBoardActivity gameboard)
    {
    	this.gb=gameboard;
    }
    /*
    private void update() {
        
        _redrawHandler.sleep(refresh);
    }
    
    public void setMode(int mode) {
        if (mode == RUNNING) {
        	paused = false;
            update();
            return;
        }
        if (mode == PAUSE) {
        	paused = true;
            // TODO: implement.
        }
    }
    
	 private RefreshHandler _redrawHandler = new RefreshHandler();

	    class RefreshHandler extends Handler {

	        @Override
	        public void handleMessage(Message message) {
	            GameBoardView.this.update();
	            GameBoardView.this.invalidate();
	        }

	        public void sleep(long delayMillis) {
	            this.removeMessages(0);
	            sendMessageDelayed(obtainMessage(0), delayMillis);
	        }
	    };
	    
    */
    @Override
    protected void onDraw(Canvas canvas)
    {
    	
    	//gb.printToLog();
    	this.canvas = canvas;
    	if(gb.isgameover())
    	{
    		canvas.drawText("Game Over", getWidth()/3, getHeight()/3, background);
        	setFocusable(false);
    		return;
    	}
    	
    	 background.setColor(getResources().getColor(R.color.background3));
    	 maxx = getWidth()-125;
		 maxy = getHeight() -300;
    	 gb.spawnEmails(maxx, maxy);
    	 drawemails=gb.getEmails();
    	 canvas.drawRect(0, 0, getWidth(), getHeight(), background);
    	 for(int i = 0; i < drawemails.size(); i++)
         {
    		 if(drawemails.get(i).isflagged())
    		 {
    			 
    			 drawemails.get(i).getBitmap().recycle();
    			 updateScore(drawemails.get(i).getScoreValue());
    			 drawemails.remove(i);
    		 }else {
         	Bitmap bm = drawemails.get(i).getBitmap();
         	canvas.drawBitmap(bm, drawemails.get(i).x(), drawemails.get(i).y(), background);
    		 }
         }
    	 background.setStyle(Style.FILL);
    	 background.setColor(Color.BLACK);
    	 background.setTextSize(20);
    	 canvas.drawText("Score:  " + score, getWidth()-200, 30, background);
    	 canvas.drawText("High Score: " + gb.prefs.getInt("HighScore",0), getWidth()-350, 30, background);
    	 canvas.drawBitmap(shredderguy, s.getX(), s.getY(), background);
    	 
    	 gb.shredEmails(s);
    	 
    	 //Log.d("shredderx", Integer.toString(shredguyx));
    	 //Log.d("shreddery", Integer.toString(shredguyy));
    	 invalidate();
    }
    
    public int getScore()
    {
    	return this.temphighscore;
    }
    
    private void initGameBoardView(){
    	setFocusable(true);
    }
    
    private void updateScore(int scorevalue)
    {
    	this.score += scorevalue;
    	if (this.score > this.temphighscore)
    	{
    		temphighscore = score;
    	}
    	if(this.score <= 0)
    	{
    		gb.gameover();
    	}
    }

	@Override
	public boolean onTouchEvent(MotionEvent m) {
		// TODO Auto-generated method stub
		switch(m.getAction()){
		case MotionEvent.ACTION_DOWN:
			if(s.getX()<= m.getX() && (s.getX() + s.getWidth())>= m.getX() && s.getY()<= m.getY() && (s.getY() + s.getHeight())>= m.getY())
			{
				s.setX((int)m.getX()-50);
			}
			break;
			
		case MotionEvent.ACTION_MOVE:
			s.setX((int)m.getX()-50);
			//shredguyy = (int)m.getY();
			break;
		case MotionEvent.ACTION_UP:
			s.setX( (int)m.getX()-50);
		
		}
		
		
		invalidate();
		//Log.d("X", Float.toString(m.getX()));
		//Log.d("Y", Float.toString(m.getY()));
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		/* this doesn't work, kept it to keep the compiler from complaining*/
		return false;
	}

	

    
}
