package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;


public class KanadePerfectMultiples {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while(t-- > 0) {
			int n = in.nextInt();
			long k = in.nextLong();
			Map<Long, Boolean> map = new HashMap<>();
			Set<Long> b = new HashSet<>();
			PriorityQueue<Long> pq = new PriorityQueue<Long>();
			for(int i = 0 ; i < n ; i++) {
				long a = in.nextLong();
				pq.offer(a);
				map.put(a, false);
			}
			boolean f = true;
			while(!pq.isEmpty() && f) {
				long num = pq.poll();
				while(!pq.isEmpty() && map.get(num)) {
					num = pq.poll();
				}
				if(map.get(num)){
					break;
				}
				b.add(num);
				for(long mul = 1 ; num * mul <=k ;mul++) {
					long MultNum = num * mul;
					if(!map.containsKey(MultNum)) {
						f = false;
						break;
					}
					map.put(MultNum, true);
				}
			}
			if(!f)
				out.println(-1);
			else {
				out.println(b.size());
				for(long x : b) {
					out.print(x+" ");
				}
				out.println("");
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
