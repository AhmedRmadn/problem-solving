package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class IdiotFirstSearch {
	final static int MOD = 1000000007;
	public static void dfs(int[]depth, int u, int[] l, int[] r) {
		if(l[u] == 0 && r[u] == 0)
			return;
		dfs(depth, l[u], l, r);
		dfs(depth, r[u], l, r);
		depth[u] = (4 + depth[l[u]] + depth[r[u]])%MOD;
	}
	public static void calcTotal(int[]depth, int[]total,int u, int p, int[] l, int[] r) {
		total[u] = (1 + depth[u] + total[p])%MOD;
		if(l[u] == 0 && r[u] == 0)
			return;
		calcTotal(depth, total, l[u], u, l, r);
		calcTotal(depth, total, r[u], u, l, r);
	}
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while(t-- > 0) {
			int n = in.nextInt() + 1;
			int[] l = new int[n];
			int[] r = new int[n];
			l[0] = 1;
			for(int i = 1 ; i < n ; i++) {
				l[i] = in.nextInt();
				r[i] = in.nextInt();
			}
			int[]depth = new int[n];
			dfs(depth, 1, l, r);
			int[] total = new int[n];
			calcTotal(depth, total, 1, 0, l, r);
			for(int i = 1 ; i < n ; i++) {
				out.print(total[i] + " ");
			}
//			System.out.println(Arrays.toString(l));
//			System.out.println(Arrays.toString(r));
//			System.out.println(Arrays.toString(depth));
			out.println("");
			out.writer.flush();
			
		}
		
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
