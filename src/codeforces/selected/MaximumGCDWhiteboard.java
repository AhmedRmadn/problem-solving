package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;



public class MaximumGCDWhiteboard {
	public static boolean validG(int n , int g , int k,  int []prefixCount, int[] count) {
		// count g + count3*g + count >= 4g
		int total = count[g];
		if(2*g<=n) {
			total += count[2 * g];
		}
		if(3*g<=n) {
			total += count[3 * g];
		}
		if(4*g<=n) {
			total += (prefixCount[n] - prefixCount[4*g - 1]);
		}
		return total >= n - k;
		
	}
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while(t-- > 0) {
			int n = in.nextInt();
			int k = in.nextInt();
			int[] count = new int[n + 1];
			for(int i = 0 ; i < n ; i++)
				count[in.nextInt()]++;
			int[] prefixCount = new int[n + 1];
			for(int i = 1 ; i <= n ; i++) {
				prefixCount[i] += prefixCount[i - 1];
				prefixCount[i] += count[i];
			}
			int max = 1;
			for(int g = n ; g>= 1 ; g--) {
				if(validG(n, g, k, prefixCount, count)) {
					max = g;
					break;
				}
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
