package topic.ST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class SegmentTreeMinimum {

	static class SegmentTree {
		int n;
		int m;
		int st[];

		public SegmentTree(int[] arr) {
			this.m = arr.length;
			int log = 1 + (int) Math.ceil(Math.log(m) / Math.log(2));
			this.n = 1 << log;
			st = new int[n];
			Arrays.fill(st, 100);
			for (int i = 0; i < m; i++) {
				st[(n / 2) + i] = arr[i];
			}
			for (int node = (n / 2) - 1; node > 0; node--)
				set(node);

		}

		private void set(int node) {
			st[node] = Math.min(st[node << 1], st[(node << 1) + 1]);
		}

		public void update(int idx, int value) {
			int curr = (n / 2) + idx;
			st[curr] = value;
			curr /= 2;
			while (curr > 0) {
				set(curr);
				curr /= 2;
			}
		}

		public int query(int s, int e) {
			return query(1, 0, (n / 2) - 1, s, e);
		}

		public int query(int node, int sCurr, int eCurr, int sTarget, int eTarget) {

//			System.out.println(node + " " + sCurr + " " + eCurr);
			if (eTarget >= eCurr && sTarget <= sCurr) {
				return st[node];
			}
			if (sTarget > eCurr || eTarget < sCurr) {
				return Integer.MAX_VALUE;
			}
			int mid = (sCurr + eCurr) / 2;
			int left = query(node * 2, sCurr, mid, sTarget, eTarget);
			int right = query((node * 2) + 1, mid + 1, eCurr, sTarget, eTarget);
//			if(sTarget==0 && eTarget == 2) {
//				System.out.println(node + " "+sCurr + " " + eCurr+" "+left + " "+right);
//			}
			return Math.min(left, right);
		}

	}

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int n = in.nextInt();
		int queries = in.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = in.nextInt();
		SegmentTree st = new SegmentTree(arr);
		for (int q = 0; q < queries; q++) {
			int type = in.nextInt();
			if (type == 1) {
				int idx = in.nextInt();
				int value = in.nextInt();
				st.update(idx, value);
			} else {
				int a = in.nextInt();
				int b = in.nextInt() - 1;
				out.println(st.query(a, b));
			}
		}

		// Your logic here

		out.writer.close(); // Ensure output is flushed

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
