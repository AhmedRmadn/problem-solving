package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class TwoTowers {
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while(t-- > 0) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			int d = in.nextInt();
			int count = 0;
			if(a > b) {
				count += c - a;
				count += Math.max(0, d - c);
				count += Math.min(a - b, d - b);
			}
			else {
				count += d - b;
				count += Math.max(0, c - d);
				count += Math.min(b - a, c - a);
			}
			out.println(count);
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
