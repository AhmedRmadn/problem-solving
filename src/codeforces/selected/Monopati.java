package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Monopati {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while (t-- > 0) {
			int n = in.nextInt();
			int[] a0 = new int[n];
			int[] a1 = new int[n];
			for (int i = 0; i < n; i++)
				a0[i] = in.nextInt();
			for (int i = 0; i < n; i++)
				a1[i] = in.nextInt();
			int[] prefixMax = new int[n];
			prefixMax[0] = a0[0];
			int[] prefixMin = new int[n];
			prefixMin[0] = a0[0];
			for (int i = 1; i < n; i++) {
				prefixMax[i] = Math.max(prefixMax[i - 1], a0[i]);
				prefixMin[i] = Math.min(prefixMin[i - 1], a0[i]);

			}
			int[] suffixMax = new int[n];
			suffixMax[n - 1] = a1[n - 1];
			int[] suffixMin = new int[n];
			suffixMin[n - 1] = a1[n - 1];
			for (int i = n - 2; i >= 0; i--) {
				suffixMax[i] = Math.max(suffixMax[i + 1], a1[i]);
				suffixMin[i] = Math.min(suffixMin[i + 1], a1[i]);
			}
			
			int[] bestR = new int[2 * n + 2];
			Arrays.fill(bestR, Integer.MAX_VALUE);

			// 1. Calculate Li and Ri for every path, and record the minimum R for that exact L
			for (int i = 0; i < n; i++) {
			    int L_i = Math.min(prefixMin[i], suffixMin[i]);
			    int R_i = Math.max(prefixMax[i], suffixMax[i]);
			    bestR[L_i] = Math.min(bestR[L_i], R_i);
			}

			long totalPairs = 0;

			// 2. Sweep backwards to propagate the minimum R to smaller L values
			for (int l = 2 * n; l >= 1; l--) {
			    bestR[l] = Math.min(bestR[l], bestR[l + 1]);
			    
			    // If bestR[l] is not infinity, we found at least one valid path!
			    if (bestR[l] != Integer.MAX_VALUE) {
			        // Any 'r' from bestR[l] up to 2n is a valid pair
			        totalPairs += (2L * n - bestR[l] + 1);
			    }
			}

			out.println(totalPairs);


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
