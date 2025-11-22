package codeforces.round1059_div3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BeautifulString {

	public static String valid(int mask, String s) {
		int n = s.length();
		StringBuilder res = new StringBuilder();
		int last = '0';
		StringBuilder ans = new StringBuilder();
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (((1 << (n - i - 1)) & mask) > 0) {
				if (s.charAt(i) < last)
					return null;
				last = s.charAt(i);
				count++;
				ans.append((int) (i + 1) + " ");
			} else {
				res.append(s.charAt(i));
			}
		}
		int m = res.length();
		for (int i = 0; i < m / 2; i++) {
			if (res.charAt(i) != res.charAt(m - i - 1))
				return null;
		}
		return count + "\n" + ans.toString();
	}

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.nextInt();
		while (t-- > 0) {
			int n = in.nextInt();
			String s = in.next();
			boolean f = false;
			for (int mask = 0; mask < 1 << n; mask++) {
				String p = valid(mask, s);
				if (p != null) {
					out.println(p);
					f = true;
					break;
				}
			}
			if (!f)
				out.println(-1);

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

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}
	}

	static class OutputWriter {
		public PrintWriter writer;

		public OutputWriter(OutputStream stream) {
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
