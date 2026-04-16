package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class RenakoAmaoriXORGameHardVersion {
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while(t-- > 0) {
			int n = in.nextInt();
			int[] a = new int[n];
			int[] b = new int[n];
			int xor = 0;
			for(int i = 0 ; i < n ; i++) {
				a[i] = in.nextInt();
				xor ^= a[i];
			}
			for(int i = 0 ; i < n ; i++) {
				b[i] = in.nextInt();
				xor ^=b[i];
			}
			if(xor == 0) {
				out.println("Tie");
				continue;
			}
			int mask = xor;
			int max = 0;
			for(int i = 0 ; mask > 0 ; i++) {
				if((mask&1) == 1)
					max = i;
				mask>>=1;
			}
			int last = -1;
			for(int i = n - 1 ; i >=0 && last == -1 ; i--) {
				int bitA = a[i] &(1<<max);
				int bitB = b[i] &(1<<max);
				if(bitA != bitB) 
					last = i;
			}
			if(last%2==0) {
				out.println("Ajisai");
			}
			else {
				out.println("Mai");
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
