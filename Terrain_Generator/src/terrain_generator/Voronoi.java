/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terrain_generator;

import java.awt.image.*;
import java.util.List;
import java.util.ArrayList;

public class Voronoi {

	private static BufferedImage box;
	private static List<Points> points;
	private static int SIZE = NewJFrame.SIZE;

	public Voronoi()
	{
		PinkNoise pn = new PinkNoise();

		pn.test();

		box = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);

		points = new ArrayList<Points>();

		for(int i = 0; i < NewJFrame.AMOUNT; i++)
		{			
			points.add(new Points(pn.GetH(i)));
		}




	}

	public void generate()
	{

		for(int i = 0; i < SIZE; i++)
		{
			for(int j = 0; j < SIZE; j++)
			{
				double distance = SIZE;

				for(int pcount = 0; pcount < NewJFrame.AMOUNT; pcount++)
				{
					double d = dist(points.get(pcount), i, j);
					if(d < distance)
					{
						int closest = pcount;
						distance = d;
						box.setRGB(i,  j, points.get(pcount).getColor());
					}
				}
			}
		}
	}

	public static void generateDots()
	{
		for(int i = 0 ; i < SIZE ; i++)
		{

			for(int j = 0 ; j < SIZE ; j++)

			{

				for(int k = 0 ; k < NewJFrame.AMOUNT ; k++)

				{

					if( (points.get(k).getX()- 3 < i && i < points.get(k).getX()+3) && (points.get(k).getY() -3 < j && j < points.get(k).getY() + 3) )

					{

						box.setRGB(i, j, 0x000000);

					} 
				}
			}
		}
	}

	public static BufferedImage getImage()
	{
		return box;
	}

	public double dist(Points p, int x, int y)
	{
		double dist = Math.abs(x - p.getX()) + Math.abs(y - p.getY());
		return dist;
	}

}


