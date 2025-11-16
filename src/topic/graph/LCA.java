package topic.graph;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class LCA {
	static class BL {
		List<int[]>[] tree;
		int n;
		int m;
		int[][][] up;
		int[] depth;

		public BL(List<int[]>[] tree) {
			this.tree = tree;
			this.n = tree.length;
			this.m = 1 + (int) Math.ceil(Math.log(n) / Math.log(2));
			up = new int[n][m][2];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					up[i][j][0] = -1;
				}
			}
			depth = new int[n];
			dfs(0, -1, 0);
//			for (int[][] x : up) {
//				for (int[] y : x) {
//					System.out.print(Arrays.toString(y) + " ");
//				}
//				System.out.println();
//			}

		}

		public void dfs(int node, int p, int w) {
			if (p != -1) {
				depth[node] = depth[p] + 1;
			}
//			System.out.println(node + " " + p + " " + w);
			up[node][0] = new int[] { p, w };
			for (int j = 1; j < m; j++) {
				int[] temp1 = up[node][j - 1];
				int v1 = temp1[0];
				int w1 = temp1[1];
				if (v1 != -1) {
					int[] temp2 = up[v1][j - 1];
					int w2 = temp2[1];
					int v2 = temp2[0];
					up[node][j] = new int[] { v2, w1 + w2 };
				}

			}

			for (int[] e : tree[node]) {
//				System.out.println(Arrays.toString(e));

				if (e[0] != p)
					dfs(e[0], node, e[1]);
			}
		}

		public int[] findKth(int u, int k) {
			int sum = 0;
			for (int j = 0; j < m; j++) {
				if (((k >> j) & 1) == 1) {
					int[] path = up[u][j];
					if (path == null) {
						return new int[] { -1, 0 };
					}
					sum += path[1];
					u = path[0];
				}
			}
			return new int[] { u, sum };
		}

		public int getDistance(int u, int v) {
			if (depth[u] < depth[v]) {
				int temp = u;
				u = v;
				v = temp;
			}
			int diff = depth[u] - depth[v];
			int[] kth = findKth(u, diff);
			u = kth[0];
			if (u == v) {
				return kth[1];
			}
			int total = kth[1];
			for (int j = m - 1; j >= 0; j--) {
				int[] vNew = up[v][j];
				int[] uNew = up[u][j];
				if (vNew[0] != uNew[0]) {
					total += vNew[1] + uNew[1];
					v = vNew[0];
					u = uNew[0];
				}
			}
			int[] vNew = up[v][0];
			int[] uNew = up[u][0];
			total += vNew[1] + uNew[1];
			return total;
		}
	}

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int n = in.nextInt();
		List<int[]>[] tree = new List[n];
		for (int i = 0; i < n; i++) {
			tree[i] = new ArrayList<>();
		}
		for (int i = 0; i < n - 1; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			int w = in.nextInt();
			tree[u].add(new int[] { v, w });
			tree[v].add(new int[] { u, w });
		}
		BL bl = new BL(tree);
		int t = in.nextInt();
		while (t-- > 0) {
			int u = in.nextInt();
			int v = in.nextInt();
			out.println(bl.getDistance(u, v));
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
