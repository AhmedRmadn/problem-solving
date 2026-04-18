package codeforces.selected;
//https://codeforces.com/problemset/problem/2154/C1
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;



public class NoCostTooGreatEasyVersion {
	static int[] sieve;
	public static void build() {
		int max = sieve.length - 1;
		for(int p = 2 ; p <= max ; p++) {
			if(sieve[p] != 0)
				continue;
			for(int mult = 1 ; mult * p <= max ; mult++) {
				if(sieve[mult * p] == 0) {
					sieve[mult * p] = p;
				}
			}
		}
	}
	public static Set<Integer> getPrimeFactors(int x){
		Set<Integer> primes = new HashSet<Integer>();
//		System.out.println(x+" "+sieve[x]);
		while(x > 1) {
			primes.add(sieve[x]);
			x /= sieve[x];
		}
		return primes;
	}
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		sieve = new int[1000000 + 1];
		build();
		int t = in.nextInt();
		while(t-- > 0) {
			int n = in.nextInt();
			int[] a = new int[n];
			for(int i = 0 ; i < n ; i++)
				a[i] = in.nextInt();
			for(int i = 0 ; i < n ; i++)
				in.next();
			Map<Integer,Integer> countPrime = new HashMap<>();
			boolean f0 = false;
			for(int x : a) {
				Set<Integer> primes = getPrimeFactors(x);
				for(int prime : primes) {
					countPrime.put(prime, countPrime.getOrDefault(prime, 0) + 1);
					if(countPrime.get(prime)>1)
						f0 = true;
				}
			}
			boolean f1 = false;
			for(int x :a) {
				Set<Integer> primesToRemove = getPrimeFactors(x);
				Set<Integer> primesToAdd = getPrimeFactors(x + 1);
				for(int prime : primesToAdd) {
					int countOccur = countPrime.getOrDefault(prime, 0);
					//// countOccur is 0 then no need 
					/// countOccur is 1 then no need 
					if(countOccur > 0 && !primesToRemove.contains(prime))
						f1 = true;
				}
			}
//			System.out.println(countPrime);
			if(f0)
				out.println(0);
			else if(f1)
				out.println(1);
			else 
				out.println(2);
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
