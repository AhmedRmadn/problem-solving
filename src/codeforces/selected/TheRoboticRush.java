package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class TheRoboticRush {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while (t-- > 0) {
			int n = in.nextInt();
			int m = in.nextInt();
			int k = in.nextInt();
			long[] a = new long[n];
			long[] b = new long[m];
			for (int i = 0; i < n; i++)
				a[i] = in.nextInt();
			for (int i = 0; i < m; i++)
				b[i] = in.nextInt();
			Arrays.sort(a);
			Arrays.sort(b);
			String instructions = in.next();
			long[] leftSpike = new long[n];
			long[] rightSpike = new long[n];
			Arrays.fill(leftSpike, Long.MAX_VALUE);
			Arrays.fill(rightSpike, Long.MAX_VALUE);
			int spike = 0;
			for (int i = 0; i < n; i++) {
				while (spike < m - 1 && b[spike + 1] < a[i]) {
					spike++;
				}
				if (b[spike] > a[i]) {
					rightSpike[i] = b[spike] - a[i];

				} else {
					leftSpike[i] = a[i] - b[spike];
					if (spike < m - 1) {
						rightSpike[i] = b[spike + 1]  - a[i];
					}
				}
			}
//			System.out.println(Arrays.toString(leftSpike));
//			System.out.println(Arrays.toString(rightSpike));
			PriorityQueue<Integer> leftQ = new PriorityQueue<Integer>((i, j) -> Long.compare(leftSpike[i] , leftSpike[j]));
			PriorityQueue<Integer> rightQ = new PriorityQueue<Integer>((i, j) -> Long.compare(rightSpike[i] , rightSpike[j]));
			int currentAlive = n;
			for (int i = 0; i < n; i++) {
				leftQ.offer(i);
				rightQ.offer(i);
			}
			boolean[] alive = new boolean[n];
			Arrays.fill(alive, true);
			int leftMove = 0;
			int rightMove = 0;
			for (int i = 0; i < k; i++) {
				if (instructions.charAt(i) == 'L')
					leftMove++;
				else
					rightMove++;
				while (!leftQ.isEmpty() && leftSpike[leftQ.peek()] <= leftMove - rightMove) {
					int robotIndex = leftQ.poll();
					if (alive[robotIndex]) {
						currentAlive--;
						alive[robotIndex] = false;
					}
				}
				while (!rightQ.isEmpty() && rightSpike[rightQ.peek()] <= rightMove - leftMove) {
					int robotIndex = rightQ.poll();
					if (alive[robotIndex]) {
						currentAlive--;
						alive[robotIndex] = false;
					}
				}
				out.print(currentAlive + " ");
			}
			out.println("");
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
