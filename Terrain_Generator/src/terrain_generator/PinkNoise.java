/*
 * PinkNoise.java  -  a pink noise generator
 *
 * Copyright (c) 2008, Sampo Niskanen <sampo.niskanen@iki.fi>
 * All rights reserved.
 * Source:  http://www.iki.fi/sampo.niskanen/PinkNoise/
 *
 * Distrubuted under the BSD license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 
 *  - Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution.
 *
 *  - Neither the name of the copyright owners nor contributors may be
 * used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package terrain_generator;



import java.util.Random;

public class PinkNoise {

	private final int poles;
	private final double[] multipliers;

	private final double[] values;
	private final Random rnd;
	private float[] H = new float[NewJFrame.AMOUNT];

	public PinkNoise() {
		this(1.0, 5, new Random());
	}

	public PinkNoise(double alpha, int poles) {
		this(alpha, poles, new Random());
	}



	public PinkNoise(double alpha, int poles, Random random) {
		if (alpha < 0 || alpha > 2) {
			throw new IllegalArgumentException("Invalid pink noise alpha = " +
					alpha);
		}

		this.rnd = random;
		this.poles = poles;
		this.multipliers = new double[poles];
		this.values = new double[poles];

		double a = 1;
		for (int i=0; i < poles; i++) {
			a = (i - alpha/2) * a / (i+1);
			multipliers[i] = a;
		}

		// Fill the history with random values
		for (int i=0; i < 5*poles; i++)
			this.nextValue();
	}



	public double nextValue() {
		double x = rnd.nextGaussian() - NewJFrame.SKEW;

		for (int i = 0; i < poles; i++) {
			x -= multipliers[i] * values[i];
		}
		System.arraycopy(values, 0, values, 1, values.length-1);
		values[0] = x;

		return x;
	}




	public void test() {
		PinkNoise source;

		int n;
		double alpha = 1.0;
		int poles = 5;
		n = NewJFrame.AMOUNT;
		float[] nutz = new float[NewJFrame.AMOUNT];
		
		
		// Generate values:
		source = new PinkNoise(alpha, poles);
		for (int i = 0; i < n; i++) {
			nutz[i] =  (float) source.nextValue();
			System.arraycopy(nutz, i, H, i, 1);
		}
		
	}

	public float GetH(int i)
	{
		return H[i];
	}
}
