package CSES;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class DistinctValuesSubarraysII {

	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int n = in.nextInt();
		int k = in.nextInt();
		int[] arr = new int[n];
		for(int i =0 ; i<n ;i++) {
			arr[i] = in.nextInt();
		}
		HashMap<Integer,Integer> countMap = new HashMap<>();
		int l = 0;
		int count=0;
		for(int r = 0 ; r < n ; r++) {
			countMap.put(arr[r], countMap.getOrDefault(arr[r], 0) + 1);
			while(countMap.size() > k) {
				countMap.put(arr[l], countMap.get(arr[l]) - 1);
				if(countMap.get(arr[l]) == 0)
					countMap.remove(arr[l]);
				l++;
			}
			count += r - l + 1;
		}
		System.out.println(count);
		
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
