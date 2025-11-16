
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Main {

	public static String encode(int u, int p, int n, int mask, List<Integer>[] tree) {
		List<String> children = new ArrayList<>();
		for (int v : tree[u]) {
			if (((1 << v) & mask) == (1 << v)) {
				if (v != p)
					children.add(encode(v, u, n, mask, tree));
			}
		}
		Collections.sort(children);
		StringBuilder sb = new StringBuilder("(");
		for (String s : children)
			sb.append(s);
		sb.append(")");
		return sb.toString();
	}

	public static void addTree(int mask, int n, List<Integer>[] tree, HashSet<String> strTree) {
		int[] deg = new int[n];
		int countOnes = 0;
		for (int u = 0; u < n; u++) {
			if (((1 << u) & mask) == (1 << u)) {
				for (int v : tree[u]) {
					if (((1 << v) & mask) == (1 << v)) {
						deg[u]++;
					}
				}
				countOnes++;
			}
		}

		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < n; i++) {
			if (deg[i] == 1)
				q.offer(i);
		}

		while (countOnes > 2) {
			Queue<Integer> temp = new LinkedList<Integer>();
			while (!q.isEmpty()) {
				int u = q.poll();
				deg[u] = -1;
				countOnes--;
				for (int v : tree[u]) {
					if (((1 << v) & mask) == (1 << v)) {
						deg[v]--;
						if (deg[v] == 1)
							temp.offer(v);
					}
				}
			}
			q = temp;
		}
//		if(mask == 7)
//			System.out.println(Arrays.toString(deg));
		String t1 = null;
		String t2 = null;
		for (int i = 0; i < n; i++) {
			if (deg[i] >= 0 && (((1 << i) & mask) == (1 << i))) {
				if (t1 == null)
					t1 = encode(i, -1, n, mask, tree);
				else
					t2 = encode(i, -1, n, mask, tree);
			}
		}
		if (t1 != null && t2 != null) {
			if (t1.compareTo(t2) < 0)
				strTree.add(t1);
			else
				strTree.add(t2);
		} else if (t2 == null) {
			strTree.add(t1);
		}

	}

	public static boolean formTree(int mask, int n, List<Integer>[] tree) {
		int firstBit = -1;
		for (int i = 0; i < n && firstBit == -1; i++) {
			if (((1 << i) & mask) == (1 << i)) {
				firstBit = i;
			}
		}
		Queue<Integer> q = new LinkedList<>();
		q.offer(firstBit);
		mask = mask ^ (1 << firstBit);

		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : tree[u]) {
				if (((1 << v) & mask) == (1 << v)) {
					q.offer(v);
					mask = mask ^ (1 << v);
				}
			}
		}
		return mask == 0;
	}

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);

		int t = in.nextInt();
		while (t-- > 0) {
			int n = in.nextInt();
			List<Integer>[] tree = new List[n];
			HashSet<String> strTree = new HashSet<String>();
			for (int i = 0; i < n; i++)
				tree[i] = new ArrayList<Integer>();
			for (int i = 0; i < n - 1; i++) {
				int u = in.nextInt() - 1;
				int v = in.nextInt() - 1;
				tree[u].add(v);
				tree[v].add(u);
			}
			for (int mask = 1; mask < 1 << n; mask++) {

				if (formTree(mask, n, tree)) {
					addTree(mask, n, tree, strTree);
				}
//				System.out.println(mask+" "+strTree);
			}
			out.println("Case #" + t + ": " + strTree.size());
		}
		out.writer.close();

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
