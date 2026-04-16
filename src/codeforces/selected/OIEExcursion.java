package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class OIEExcursion {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while (t-- > 0) {
			int n = in.nextInt();
			long m = in.nextLong();
			int[] a = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = in.nextInt();
			int count = 1;
			int max = 0;
			for (int i = 1; i < n; i++) {
				if (a[i] != a[i - 1]) {
					max = Math.max(max, count);
					count = 0;
				}
				count++;
			}
			max = Math.max(max, count);
			if (max < m) {
				out.println("YES");
			} else {
				out.println("NO");
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
