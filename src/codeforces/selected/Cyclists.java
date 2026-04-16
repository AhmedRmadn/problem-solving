package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;



public class Cyclists {
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t =in.nextInt();
		while(t-- > 0) {
			int n = in.nextInt();
			int k = in.nextInt();
			int p = in.nextInt() - 1;
			int m = in.nextInt();
			int[] a = new int[n];
			Queue<Integer> q = new LinkedList<Integer>();
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>((i,j)->a[i]-a[j]);
			boolean inK = p < k ;
			for(int i = 0 ; i < n ; i++) {
				a[i] = in.nextInt();
				if(i < k) {
					if(i!=p)
						pq.offer(i);
				}
				else {
					q.offer(i);
				}
			}
			int count = 0;
			while(m > 0) {
				int idxToRemove = inK ? p : pq.poll();
				m -= a[idxToRemove];
//				System.out.println(idxToRemove+" "+m);
				if(m < 0)
					break;
				count += inK ? 1 : 0;
				q.offer(idxToRemove);
				int next = q.poll();
				inK &= false;
				if(next == p) 
					inK = true;
				else 
					pq.offer(next);
				
			}
			out.println(count);
		}
		out.writer.flush();;
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
