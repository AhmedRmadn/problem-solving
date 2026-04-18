package codeforces.selected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TheAncientWizardsCapes {
	public static boolean valid(int[] a, int start, int end, int d, int[] allD) {
		// d = 0 L d = 1 R
		for (int i = start; i <= end; i++) {
			if (Math.abs(a[i] - a[i - 1]) > 1)
				return false;
			if (a[i] > a[i - 1]) {
				if (d == 1) {
					return false;
				}
			} else if (a[i] < a[i - 1]) {
				if (d == 0) {
					return false;
				}
			} else {
				d += 1;
				d %= 2;
			}
			allD[i] = d;
		}
		return true;
	}

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while (t-- > 0) {
			int n = in.nextInt();
			int[] a = new int[n];
			int idx = -1;
			for (int i = 0; i < n; i++) {
				a[i] = in.nextInt();
				if (i > 0 && idx == -1 && a[i] != a[i - 1])
					idx = i - 1;
			}
//			System.out.println(idx);
			int count = 0;
			if (n == 1) {
				count += a[0] == 1 ? 2 : 0;
			} else if (idx == -1) {
				int v1 = 1 + ((n - 1) / 2);
				int v2 = 2 + ((n - 2) / 2);
				count = (v1 == a[0] ? 1 : 0) + (v2 == a[0] ? 1 : 0);
			} else {
				int d = a[idx] > a[idx + 1] ? 1 : 0; // 0 L 1 R
//				System.out.println(d);
				int[] allD = new int[n];
				allD[idx] = d;
				for (int i = idx - 1; i >= 0; i--) {
					allD[i] = (allD[i + 1] + 1) % 2;
				}
//				System.out.println(Arrays.toString(allD));
				boolean rightSide = valid(a, idx + 1, n - 1, d, allD);
				int suffixSum = 0;
				boolean f = rightSide;

				for (int i = 0; i < n; i++)
					suffixSum += allD[i];
				int prefixSum = 0;
//				System.out.println(Arrays.toString(allD) + " " + suffixSum);
				for (int i = 0; i < n && f; i++) {
					suffixSum -= allD[i];
					int seen = suffixSum + i - prefixSum + 1;
					if (seen != a[i])
						f = false;
					prefixSum += allD[i];
				}
				count = f ? 1 : 0;
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
