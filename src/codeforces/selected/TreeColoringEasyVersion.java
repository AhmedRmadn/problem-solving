package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class TreeColoringEasyVersion {

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while (t-- > 0) {
			int n = in.nextInt();
			List<Integer>[] tree = new List[n];
			for (int i = 0; i < n; i++)
				tree[i] = new ArrayList<Integer>();
			for (int i = 0; i < n - 1; i++) {
				int u = in.nextInt() - 1;
				int v = in.nextInt() - 1;
				tree[u].add(v);
				tree[v].add(u);
			}
			int max = 1;
			Queue<Integer> q = new LinkedList<Integer>();
			boolean[] visited = new boolean[n];
			visited[0] = true;
			q.offer(0);
			while(!q.isEmpty()) {
				int currSize = q.size();
				for(int i = 0 ; i < currSize ; i++) {
					int u = q.poll();
					max = Math.max(max, tree[u].size() + (u == 0 ? 1 : 0));
//					System.out.println(max + " case 1 " + u);
					for(int v : tree[u]) {
						if(visited[v])
							continue;
						visited[v] = true;
						q.offer(v);
					}
				}
				max = Math.max(max, q.size());
//				System.out.println(q);
//				System.out.println(max + " case 2 ");
			}
			out.println(max);
			
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
