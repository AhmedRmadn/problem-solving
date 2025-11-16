package topic.ST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class NumberMinimumsSegment {

	static class SegmentTree {
		int n;
		int m;
		int[][] st;

		public SegmentTree(int[] arr) {
			this.m = arr.length;
			int log = 1 + (int) Math.ceil(Math.log(m) / Math.log(2));
			this.n = 1 << log;
			st = new int[n][2];
			for (int[] x : st) {
				x[0] = Integer.MAX_VALUE;
				x[1] = 1;
			}
			for (int i = 0; i < m; i++) {
				st[(n / 2) + i][0] = arr[i];
				st[(n / 2) + i][1] = 1;
			}

			for (int node = (n / 2) - 1; node > 0; node--) {
				set(node);
			}

		}

		private void set(int node) {
			int leftVal = st[node * 2][0];
			int leftCount = st[node * 2][1];
			int rightVal = st[(node * 2) + 1][0];
			int rightCount = st[(node * 2) + 1][1];
			if (leftVal < rightVal) {
				st[node][0] = leftVal;
				st[node][1] = leftCount;
			} else if (rightVal < leftVal) {
				st[node][0] = rightVal;
				st[node][1] = rightCount;
			} else {
				st[node][0] = rightVal;
				st[node][1] = rightCount + leftCount;
			}
		}

		public void update(int idx, int value) {
			int curr = (n / 2) + idx;
			st[curr][0] = value;
			st[curr][1] = 1;
			curr /= 2;
			while (curr > 0) {
				set(curr);
				curr /= 2;
			}

		}

		public int[] query(int a, int b) {
			return query(1, 0, (n / 2) - 1, a, b);
		}

		public int[] query(int node, int s, int e, int a, int b) {
			if (s >= a && e <= b) {
				return new int[] { st[node][0], st[node][1] };
			}
			if (e < a || b < s) {
				return new int[] { Integer.MAX_VALUE, 0 };
			}
			int mid = (e + s) / 2;
			int[] left = query(node * 2, s, mid, a, b);
			int[] right = query((node * 2) + 1, mid + 1, e, a, b);
			int leftVal = left[0];
			int leftCount = left[1];
			int rightVal = right[0];
			int rightCount = right[1];
			if (leftVal < rightVal) {
				return new int[] { leftVal, leftCount };
			} else if (rightVal < leftVal) {
				return new int[] { rightVal, rightCount };
			} else {
				return new int[] { rightVal, leftCount + rightCount };
			}

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
				int[] res = st.query(a, b);
				out.println(res[0]+" "+res[1]);
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
