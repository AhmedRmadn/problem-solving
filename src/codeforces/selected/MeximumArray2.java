package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MeximumArray2 {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while (t-- > 0) {
			int n = in.nextInt();
			int k = in.nextInt();
			int q = in.nextInt();
			int[] min = new int[n+1];
			int[] mex = new int[n+1];
			while (q-- > 0) {
				int c = in.nextInt();
				int l = in.nextInt() - 1;
				int r = in.nextInt();
				if (c == 1) {
					min[l] += 1;
					min[r] += -1;
				} else {
					mex[l] += 1;
					mex[r] += -1;
				}
			}
			int count1 = 0;
			int count2 = 0;
			for(int i = 0; i< n; i++) {
				count1 += min[i];
				count2 += mex[i];
				if(count1 > 0 && count2>0) {
					out.print(k + 1 + " ");
				}
				else if(count1 > 0) {
					out.print(k + " ");
				}
				else if(count2 > 0) {
					out.print(i%k + " ");
				}
				else {
					out.print(0 + " ");
				}
			}
			out.println("");
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
