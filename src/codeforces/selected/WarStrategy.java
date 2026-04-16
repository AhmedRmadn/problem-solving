package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class WarStrategy {
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while(t-- > 0) {
			long n = in.nextLong();
			long m = in.nextLong();
			long k = in.nextLong();
			long max = 0;
			for(int curr = 1 ; curr <= n ; curr++) {
				/////
				long d = Math.abs(k - curr);
				long otherSide = n - k;
				if(curr > k) {
					otherSide = k - 1;
				}
				long numDays = 2 * d -1;
				if(numDays > m)
					continue;
				long remDays = m - numDays;
				long dFromOtherSide =Math.min(Math.min(remDays,otherSide),d);
//				System.out.println(d+" "+otherSide+" "+numDays+" "+remDays+" "+dFromOtherSide);
				max = Math.max(max, dFromOtherSide +d + 1);
				
			}
			out.println(max);

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
