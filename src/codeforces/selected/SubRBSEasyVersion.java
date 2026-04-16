package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class SubRBSEasyVersion {
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while(t-- > 0) {
			int n = in.nextInt();
			String s = in.next();
			boolean f = false;
			int count = 0;
			for(int i = 0 ; i < n - 1 && !f; i++) {
				if(s.charAt(i) == '(')
					count++;
				else
					count--;
				if(s.charAt(i)==')' && s.charAt(i + 1) == '(' && (count + 1) < (n - i - 2)) {
					f = true;
				}
					
			}
			if(f)
				out.println(n - 2);
			else
				out.println(-1);
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
