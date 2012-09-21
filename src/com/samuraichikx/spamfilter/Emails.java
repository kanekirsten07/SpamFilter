package com.samuraichikx.spamfilter;

import android.graphics.Bitmap;

public class Emails {
	
	private int value, x, y;
	private Bitmap emailtype;
	public Emails(int scorevalue, int xcoord, int ycoord, Bitmap etype)
	{
		this.emailtype = etype;
		this.value= scorevalue;
		this.x = xcoord;
		this.y = ycoord;
	}
	
	public int x()
	{
		return x;
	}
	public int y()
	{
		return y;
	}
	
	public void fall()
	{
		y+=10;
	}
	
	public Bitmap getBitmap()
	{
		return emailtype;
	}

}
