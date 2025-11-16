package topic.numer_theory;

import java.util.Arrays;

public class SieveEratosthenes {

	public static int[] sieve(int n) {
		boolean[] prime = new boolean[n + 1];
		Arrays.fill(prime, true);
		prime[1] = false;
		int count = 0;
		for (int p = 2; p * p <= n; p++) {
			if (!prime[p])
				continue;
			for (int next = p * p; next <= n; next += p) {
				prime[next] = false;
			}
		}
		for(int p = 2 ; p<= n ;p++) {
			if(prime[p])
				count++;
		}
//		System.out.println(count+" "+Arrays.toString(prime));
		int[] res = new int[count];
		int i = 0;
		for (int p = 2; p <= n; p++) {
			if (prime[p])
				res[i++] = p;
		}
		return res;

	}
	public static void main(String[] args) {
		System.out.println(Arrays.toString(sieve(10)));
	}

}
