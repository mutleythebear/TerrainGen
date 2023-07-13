/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terrain_generator;

import java.util.Random;

public class Points {

	private int x,y,col;
	float height;
	private Random rand;

	public Points(float h)
	{

		rand = new Random();

		x = rand.nextInt(1000);
		y = rand.nextInt(1000);
		height = h;

		if(height <= -2)
		{
			//darker blue
			col = 0x0E54B7;
			
		}
		else if(height < 0 && height > -2)
		{
			//blue
			col = 0x1F66CB;
		}
		else if(height <= 0.5 && height >= 0)
		{
			//piss brown
			col = 0xE5E529;
		}
		else if(height <= 3 && height > 0.5)
		{
			//green
			col = 0x34BC10;
		}
		else
		{
			//brown
			col = 0xBC8010;
		}


	}
	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getColor()
	{
		return col;
	}

	public void setColor(int color)
	{
		col = color;
	}
}
