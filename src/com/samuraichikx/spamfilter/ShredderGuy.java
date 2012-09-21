package com.samuraichikx.spamfilter;

public class ShredderGuy {
	
	private int x, y;
	private int height, width;
	public ShredderGuy(int xcoord, int ycoord)
	{
		this.x = xcoord;
		this.y= ycoord;
	}
	
	public void setX(int newx)
	{
		x =newx;
	}
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void setHeight(int h)
	{
		this.height = h;
	}
	
	public void setWidth(int w)
	{
		this.width = w;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}

}
