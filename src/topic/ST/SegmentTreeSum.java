package topic.ST;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

////https://codeforces.com/edu/course/2/lesson/4/1/practice/contest/273169/problem/A
public class SegmentTreeSum {

	static class SegmentTree {
		int n;
		int size;
		long[] tree;

		public SegmentTree(long[] arr) {
			int log = 1 + (int) Math.ceil(Math.log(arr.length) / Math.log(2));
			this.n = (int) Math.pow(2, log);
			this.size = arr.length;
			this.tree = new long[n];
			for (int i = 0; i < size; i++) {
				tree[(n / 2) + i] = arr[i];
			}
			for (int node = (n / 2) - 1; node > 0; node--)
				set(node);
		}

		public void set(int node) {
			tree[node] = tree[node * 2] + tree[(node * 2) + 1];
		}

		public void update(int idx,long value) {
			int curr = idx + (n / 2);
			tree[curr] = value;
			curr = curr / 2;
			while (curr > 0) {
				set(curr);
				curr = curr / 2;
			}
		}

		public long query(int l, int r) {
			return query(1, l, r, 0, (n / 2) - 1);
		}

		private long query(int node, int s, int e, int startNode, int endNode) {
			if (startNode >= s && endNode <= e) {
				return tree[node];
			}
			int mid = (endNode + startNode) / 2;
			int leftS = startNode;
			int leftE = mid;
			int rightS = mid + 1;
			int rightE = endNode;
			long left = 0;
			long right = 0;
			if (leftE >= s && leftS <= e) {
				left = query(node * 2, s, e, leftS, leftE);
			}
			if (rightE >= s && rightS <= e) {
				right = query((node * 2) + 1, s, e, rightS, rightE);
			}
			return left + right;
		}
	}
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int n = in.nextInt();
		int queries = in.nextInt();
		long[] arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		SegmentTree st = new SegmentTree(arr);
//		System.out.println(st);
		for (int q = 0; q < queries; q++) {
			int type = in.nextInt();
			if (type == 1) {
				int k = in.nextInt();
				long u = in.nextLong();
				st.update(k, u);
//				System.out.println(st);
			} else {
				int a = in.nextInt();
				int b = in.nextInt()-1;
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
