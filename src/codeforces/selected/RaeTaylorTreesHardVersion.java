package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class RaeTaylorTreesHardVersion {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while(t-- > 0) {
			int n = in.nextInt();
			int[] p = new int[n];
			int[] idx = new int[n];
			for(int i = 0 ; i < n ; i++) {
				p[i] = in.nextInt() - 1;
				idx[p[i]] = i;
			}
			int[] parent = new int[n];
			int lastReached = -1;
			Arrays.fill(parent, -1);
			parent[p[0]] = -2;
			boolean f = p[0] != n - 1;
			for(int i = 0 ; i < n ; i++) {
				int u = p[i];
				if(parent[u] >=0)
					continue;
				if(lastReached != -1) {
					if(lastReached < i)
					{
						f = false;
						break;
					}
					parent[u] = p[lastReached];
				}
				for(int v = u + 1; v < n && parent[v] == -1; v++) {
					
					parent[v] = u;
					lastReached = Math.max(lastReached, idx[v]);
				}
				
			}
			if(!f) {
				out.println("No");
			}
			else {
				out.println("Yes");
				for(int i = 0 ; i < n ; i++) {
					int u = i + 1 ;
					int v = parent[i] + 1;
					if(v == -1)
						continue;
					out.println(u+" "+v);
				}
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
