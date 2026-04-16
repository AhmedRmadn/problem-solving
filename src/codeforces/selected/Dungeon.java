package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Dungeon {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while (t-- > 0) {
			int n = in.nextInt();
			int m = in.nextInt();
			TreeMap<Long, Integer> a = new TreeMap<>();
			for (int i = 0; i < n; i++) {
				long x = in.nextLong();
				a.put(x, a.getOrDefault(x, 0) + 1);
			}
			long[] b = new long[m];
			long[] c = new long[m];
			for (int i = 0; i < m; i++)
				b[i] = in.nextLong();
			for (int i = 0; i < m; i++)
				c[i] = in.nextLong();
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>((i, j) -> {
				long diffB = b[i] - b[j];
				if (c[i] == 0 && c[j] == 0)
					return Long.compare(b[i], b[j]);
				if (c[i] == 0 || c[j] == 0)
					return Long.compare(c[j], c[i]);
				if (diffB == 0)
					return Long.compare(c[j], c[i]);
				return Long.compare(b[i], b[j]);
			});
			for (int i = 0; i < m; i++)
				pq.offer(i);
			long count = 0;
			while (!pq.isEmpty()) {
				int idx = pq.poll();
				long monsterB = b[idx];
				long monsterC = c[idx];
				Long sword = a.ceilingKey(monsterB);
				if (sword == null)
					continue;
				a.put(sword, a.get(sword) - 1);
				if (a.get(sword) == 0)
					a.remove(sword);
				count++;
				if (monsterC > 0) {
					sword = Math.max(sword, monsterC);
					a.put(sword, a.getOrDefault(sword, 0) + 1);
				}
			}
			out.println(count);
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
