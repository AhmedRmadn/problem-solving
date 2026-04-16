package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class AnnoyingGame {
	// 1. Corrected Standard Kadane's (Handles all-negative arrays perfectly)
	public static long findMaxSubArraySum(long[] a) {
	    long currentMax = a[0];
	    long globalMax = a[0];
	    for (int i = 1; i < a.length; i++) {
	        currentMax = Math.max(a[i], currentMax + a[i]);
	        globalMax = Math.max(globalMax, currentMax);
	    }
	    return globalMax;
	}

	// 2. The Prefix/Suffix Approach for K = Odd
	public static long findMaxSubArraySum(long[] a, long[] b) {
	    int n = a.length;
	    
	    // pref[i] stores the max subarray sum ENDING at index i
	    long[] pref = new long[n];
	    pref[0] = a[0];
	    for (int i = 1; i < n; i++) {
	        pref[i] = Math.max(a[i], pref[i - 1] + a[i]);
	    }

	    // suff[i] stores the max subarray sum STARTING at index i
	    long[] suff = new long[n];
	    suff[n - 1] = a[n - 1];
	    for (int i = n - 2; i >= 0; i--) {
	        suff[i] = Math.max(a[i], suff[i + 1] + a[i]);
	    }

	    long maxScore = Long.MIN_VALUE;

	    // Try giving Alice's "free" boost to every single index
	    for (int i = 0; i < n; i++) {
	        long currentScore = a[i] + b[i];
	        
	        // If the best prefix right before this is positive, attach it!
	        if (i > 0 && pref[i - 1] > 0) {
	            currentScore += pref[i - 1];
	        }
	        
	        // If the best suffix right after this is positive, attach it!
	        if (i < n - 1 && suff[i + 1] > 0) {
	            currentScore += suff[i + 1];
	        }
	        
	        maxScore = Math.max(maxScore, currentScore);
	    }

	    return maxScore;
	}

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while (t-- > 0) {
			int n = in.nextInt();
			int k = in.nextInt();
			long[] a = new long[n];
			for (int i = 0; i < n; i++)
				a[i] = in.nextLong();
			long[] b = new long[n];
			for (int i = 0; i < n; i++)
				b[i] = in.nextLong();
			if(k%2==0)
				out.println(findMaxSubArraySum(a));
			else
				out.println(findMaxSubArraySum(a,b));

		}
		out.writer.flush();
	}

	static class InputReader {
		public BufferedReader reader;
		public StringTokenizer tokenizer;

		public InputReader(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream), 32768);
			tokenizer = null;
		}

		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}
	}

	static class OutputWriter {
		public PrintWriter writer;

		OutputWriter(OutputStream stream) {
			writer = new PrintWriter(stream);
		}

		public void print(Object args) {
			writer.print(args);
		}

		public void println(Object args) {
			writer.println(args);
		}
	}

}
