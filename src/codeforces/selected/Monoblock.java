package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Monoblock {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int n = in.nextInt();
		int m = in.nextInt();
		long[] a = new long[n];
		long base = (((long)n)*((long)(n + 1)))/2;
		for(int i = 0 ; i < n ;i++)
			a[i] = in.nextLong();
		int[] group = new int[n];
		Arrays.fill(group, -1);
		long total = base;
		for(int i = 0 ; i < n - 1 ; i++) {
			if(a[i]!=a[i+1]) {
				long l = i + 1;
				long r = n - i - 1;
				long c = l * r;
				total += c;
			}
		}
		long temp = total;
		for(int i = 0 ; i < m ; i++) {
			int idx = in.nextInt() - 1;
			long x = in.nextLong();
			if(idx > 0) {
				long l = idx;
				long r = n - idx;
				long c = l * r;
				if(a[idx - 1] != a[idx]) {
					total -= c;
				}
				if(a[idx - 1] != x) {
					total += c;
				}
			}
			if(idx < n - 1) {
				long l = idx + 1;
				long r = n - idx - 1;
				long c = l * r;
				if(a[idx] != a[idx + 1]) {
					total -= c;
				}
				if(x != a[idx + 1]) {
					total += c;
				}
			}
			a[idx] = x;
			out.println(total);
			temp = total;
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
