package CSES;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class HighScore {

	public static boolean relax(int[] edge, long[] dist) {
		int u = edge[0];
		int v = edge[1];
		int w = edge[2];
		if (dist[u] != Long.MIN_VALUE && dist[u] + w > dist[v]) {
			dist[v] = dist[u] + w;
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int n = in.nextInt();
		int m = in.nextInt();
		int[][] edges = new int[m][3];
		List<Integer>[] graph = new List[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		long[] dist = new long[n];
		Arrays.fill(dist, Long.MIN_VALUE);
		for (int i = 0; i < m; i++) {
			edges[i][0] = in.nextInt() - 1;
			edges[i][1] = in.nextInt() - 1;
			edges[i][2] = in.nextInt();
			graph[edges[i][0]].add(edges[i][1]);

		}
		dist[0] = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int[] edge : edges) {
				relax(edge, dist);
			}
		}
		HashSet<Integer> nodes = new HashSet<Integer>();
		for (int[] edge : edges) {
			boolean f = relax(edge, dist);
			if (f) {
				nodes.add(edge[0]);
			}
		}
		boolean[] visited = new boolean[n];
		for (int node : nodes) {
			if (visited[node])
				continue;
			Queue<Integer> q = new LinkedList<Integer>();
			q.offer(node);
			visited[node] = true;
			while (!q.isEmpty()) {
				int u = q.poll();
				for (int v : graph[u]) {
					if (!visited[v]) {
						visited[v] = true;
						q.offer(v);
					}
				}

			}
		}
		out.println(visited[n - 1] ? -1 : dist[n - 1]);
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
