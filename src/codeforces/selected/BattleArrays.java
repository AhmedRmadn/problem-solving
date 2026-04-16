package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BattleArrays {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while(t-- > 0) {
			int n = in.nextInt();
			int m = in.nextInt();
			PriorityQueue<Long> alice = new PriorityQueue<Long>((a,b) -> Long.compare(b, a));
			PriorityQueue<Long> bob = new PriorityQueue<Long>((a,b) -> Long.compare(b, a));
			for(int i = 0 ; i < n ; i++)
				alice.offer(in.nextLong());
			for(int i = 0 ; i < m ; i++)
				bob.offer(in.nextLong());
			while(!bob.isEmpty() && !alice.isEmpty()) {
				///// alice
				long x = alice.peek();
				long y = bob.poll();
				if(x < y) {
					bob.offer(y - x);
				}
				///bob
				if(!bob.isEmpty()) {
					x = bob.peek();
					y = alice.poll();
					if(x < y) {
						alice.offer(y - x);
					}
				}
			}
			if(bob.isEmpty()) {
				out.println("Alice");
			}
			else {
				out.println("Bob");
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
