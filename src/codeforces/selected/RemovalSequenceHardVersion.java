package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class RemovalSequenceHardVersion {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while (t-- > 0) {
			long x = in.nextLong();
			long y = in.nextLong();
			long k = in.nextLong();
			if (y == 1) {
				out.println("-1");
				continue;
			}
			long W = y - 1;
			while (x > 0 && k <= 1000000000000l) {
				long Q = (k - 1) / W;
			    
			    // If Q is 0, K is before the first deletion. It will never move again!
			    if (Q == 0) {
			        break; 
			    }
			    
			    long R = (k - 1) % W;           // How far into the chunk we are
			    long room = W - 1 - R;          // Room left in the chunk
			    long steps = (room / Q) + 1;    // Operations until Q increases
			    
			    // Don't fast-forward past the remaining X operations
			    steps = Math.min(steps, x);
			    
			    // Fast-Forward!
			    k += steps * Q;
			    x -= steps;
			}
			out.println(k <= 1000000000000l ? k : -1);
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
