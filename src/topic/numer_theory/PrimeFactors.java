package topic.numer_theory;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimeFactors {
	class Solution1 {
		////O(N) if N is prime
	    public static ArrayList<Integer> primeFac(int n) {
	        ArrayList<Integer> res = new ArrayList<>();
	        for(int p = 2 ; p <= n ;p++){
	            if(n % p == 0){
	                res.add(p);
	                while(n % p == 0)
	                    n /= p;
	            }
	        }
	        return res;

	        
	    }
	}
	
	class Solution2 {
		/////O(sqrt(N))
	    public static ArrayList<Integer> primeFac(int n) {
	        ArrayList<Integer> res = new ArrayList<>();
	        for(int p = 2 ; p*p <= n ;p++){
	            if(n % p == 0){
	                res.add(p);
	                while(n % p == 0)
	                    n /= p;
	            }
	        }
	        if(n > 1)
	            res.add(n);
	        return res;

	        
	    }
	}
	
	class Solution {
		//// O(nlog(log(n))) building sieve
		/// O(log(n))getting factors
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
//			System.out.println(count+" "+Arrays.toString(prime));
			int[] res = new int[count];
			int i = 0;
			for (int p = 2; p <= n; p++) {
				if (prime[p])
					res[i++] = p;
			}
			return res;

		}
	}

}
