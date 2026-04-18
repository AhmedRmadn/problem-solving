package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

///https://codeforces.com/problemset/problem/2152/C
public class TripleRemoval {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while (t-- > 0) {
			int n = in.nextInt();
			int q = in.nextInt();
			int[] a = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = in.nextInt();
			int[] suffixAdjacent = new int[n + 1];
			int[] countOnes = new int[n + 1];
			countOnes[n - 1] = a[n - 1];
			for (int i = n - 2; i >= 0; i--) {
				suffixAdjacent[i] = suffixAdjacent[i + 1] + (a[i] == a[i + 1] ? 1 : 0);
				countOnes[i] = countOnes[i + 1] + a[i];
			}
			while (q-- > 0) {
				int l = in.nextInt() - 1;
				int r = in.nextInt() - 1;
				int ones = countOnes[l] - countOnes[r + 1];
				int adjacent = suffixAdjacent[l] - suffixAdjacent[r];
				int len = r - l + 1;
				int zeros = len - ones;
				//System.out.println(ones+" "+zeros+" "+adjacent+" "+len);
				if (ones % 3 != 0 || zeros % 3 != 0) {
					out.println(-1);
				} else if (adjacent > 0) {
					out.println(len / 3);
				} else {
					out.println(2 + (len - 3) / 3);
				}

			}
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
