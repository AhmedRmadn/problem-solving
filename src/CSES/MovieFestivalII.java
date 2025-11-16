package CSES;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class MovieFestivalII {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int n = in.nextInt();
		int k = in.nextInt();
		int[][] movies = new int[n][2];
		for (int i = 0; i < n; i++) {
			movies[i][0] = in.nextInt();
			movies[i][1] = in.nextInt();
		}
		Arrays.sort(movies, (a, b) -> a[1] - b[1] == 0 ? a[0] - b[0] : a[1] - b[1]);

		TreeMap<Integer, Integer> available = new TreeMap<>();
		available.put(0, k);

		int count = 0;

		for (int[] movie : movies) {
			Integer member = available.floorKey(movie[0]); // latest member free ≤ start
			if (member != null) {
				count++;
				available.put(member, available.get(member) - 1);
				if (available.get(member) == 0)
					available.remove(member);
				available.put(movie[1], available.getOrDefault(movie[1], 0) + 1); // this member now busy until movie[1]
			}
		}
		out.println(count);
		 
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
