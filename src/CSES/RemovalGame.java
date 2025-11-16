package CSES;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.*;
 
public class RemovalGame {
	/*
	 * 4 5 1 3
	 * 
	 * 
	 * 4 0 0 0 0 5 0 0 0 0 1 0 0 0 0 3
	 * 
	 * 
	 */
 
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int n = in.nextInt();
		long[] nums = new long[n];
		for (int i = 0; i < n; i++) {
			nums[i] = in.nextLong();
		}
		long[][] dp = new long[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][i] = nums[i];
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i + 1; j < n; j++) {
				dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
 
			}
		}
		
        long total = 0;
        for (int i = 0; i < n; i++) {
            total += nums[i];
        }
        for(long[] x : dp)
        		System.out.println(Arrays.toString(x));
 
        long firstPlayerScore = (total + dp[0][n-1]) / 2;
        out.println(firstPlayerScore);
 
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