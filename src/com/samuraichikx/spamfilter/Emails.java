package com.samuraichikx.spamfilter;

import android.graphics.Bitmap;

public class Emails {
	
	private int value, x, y;
	private int height = 55, width = 30;
	private Bitmap emailtype;
	private boolean flagged = false;
	
	public Emails(int scorevalue, int xcoord, int ycoord, Bitmap etype)
	{
		this.emailtype = etype;
		this.value= scorevalue;
		this.x = xcoord;
		this.y = ycoord;
	}
	public void flag()
	{
		flagged = true;
	}
	public int getHeight()
	{
		return height;
	}
	public int getWidth(){
		return width;
	}
	
	
	public int x()
	{
		return x;
	}
	public int y()
	{
		return y;
	}
	
	public int getScoreValue()
	{
		return value;
	}
	
	public void fall()
	{
		y+=10;
	}
	
	public Bitmap getBitmap()
	{
		return emailtype;
	}
	
	public boolean isflagged(){
		return flagged;
	}

}
