package codeforces.selected;

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

public class NoCostTooGreatHardVersion {
	static int[] sieve;

	public static void build() {
		int max = sieve.length - 1;
		for (int p = 2; p <= max; p++) {
			if (sieve[p] != 0)
				continue;
			for (int mult = 1; mult * p <= max; mult++) {
				if (sieve[mult * p] == 0) {
					sieve[mult * p] = p;
				}
			}
		}
	}

	public static Set<Integer> getPrimeFactors(int x) {
		Set<Integer> primes = new HashSet<Integer>();
		while (x > 1) {
			primes.add(sieve[x]);
			x /= sieve[x];
		}
		return primes;
	}

	public static long getForMin(Map<Integer, Integer> countPrime, int[] a, long[] b, int idx) {
		int x = a[idx];
		long cost1 = Long.MAX_VALUE;
		boolean f1 = false;
		Set<Integer> primesToRemove = getPrimeFactors(x);
		for (int PN : countPrime.keySet()) {
			int countPrimesInMap = countPrime.get(PN);
			if (primesToRemove.contains(PN))
				countPrimesInMap--;
			if (countPrimesInMap < 1)
				continue;
			int m = (int) Math.ceil((x * 1.0) / PN);
			int k = m * PN - x;
//			System.out.println(m+" "+k+" "+PN+" "+x);
			////// x + k = m*PN
			Set<Integer> primesToAdd = getPrimeFactors(x + k);
			for (int prime : primesToAdd) {
				int countOccur = countPrime.getOrDefault(prime, 0);
				//// countOccur is 0 then no need countOccur is 1 then no need
				if (countOccur > 0 && !primesToRemove.contains(prime)) {
					f1 = true;
					cost1 = Math.min(k * b[idx], cost1);
				}
			}
		}
		return cost1;
	}

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		sieve = new int[1000000 + 1];
		build();
		int t = in.nextInt();
		while (t-- > 0) {
			int n = in.nextInt();
			int[] a = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = in.nextInt();
			long[] b = new long[n];
			for (int i = 0; i < n; i++)
				b[i] = in.nextLong();
			Map<Integer, Integer> countPrime = new HashMap<>();
			boolean f0 = false;
			for (int x : a) {
				Set<Integer> primes = getPrimeFactors(x);
				for (int prime : primes) {
					countPrime.put(prime, countPrime.getOrDefault(prime, 0) + 1);
					if (countPrime.get(prime) > 1)
						f0 = true;
				}
			}
			boolean f1 = false;
			long min1 = Math.min(b[0], b[1]); //// smallest
			int smallestIdx = b[0] > b[1] ? 1 : 0;
			long min2 = Math.max(b[0], b[1]); /// second smallest
			for (int i = 2; i < n; i++) {
				if (b[i] < min1) {
					min2 = min1;
					min1 = b[i];
					smallestIdx = i;
				} else if (b[i] < min2) {
					min2 = b[i];
				}
			}
			long cost2 = min1 + min2;
			long cost1 = getForMin(countPrime, a, b, smallestIdx);
//			 System.out.println(cost1 + " " + cost2);
			for (int i = 0; i < a.length; i++) {
				if (i == smallestIdx)
					continue;
				int x = a[i];
				Set<Integer> primesToRemove = getPrimeFactors(x);
				out: for (int add = 1; add * b[i] < cost2; add++) {
					long currCost = add * b[i];
					Set<Integer> primesToAdd = getPrimeFactors(x + add);
					for (int prime : primesToAdd) {
						int countOccur = countPrime.getOrDefault(prime, 0);
						//// countOccur is 0 then no need countOccur is 1 then no need
						if (countOccur > 0 && !primesToRemove.contains(prime)) {
							f1 = true;
							cost1 = Math.min(currCost, cost1);
							break out;
						}
					}
				}
			}

			if (f0)
				out.println(0);
			else
				out.println(Math.min(cost1, cost2));

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
