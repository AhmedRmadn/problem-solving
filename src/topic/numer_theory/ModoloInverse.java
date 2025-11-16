package topic.numer_theory;

public class ModoloInverse {
	////b^p
	public static long pow(long b, long p) {
		long res = 1;
		while (p > 0) {
			if ((p & 1) == 1) {
				res *= b;
			}
			b *= b;
			p >>= 1;
		}
		return res;
	}

	////b^p
	public static long pow(long b, long p, long mod) {
		long res = 1;
		while (p > 0) {
			if ((p & 1) == 1) {
				res = (res * b) % mod;
			}
			b = (b * b) % mod;
			p >>= 1;
		}
		return res;
	}

	public long gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	//// ax + by = gcd(a,b)
	/// gcd(a,b) = gcd(b,a % b)
	/// bx1 + (a % b)y1 = gcd(b,a % b)
	///bx1 + (a - (a/b)*b)y1  = gcd(b,a % b);
	///ay1 + b(x1 - (a/b)*y1) = gcd(b,a % b);
	public long[] extendedGCD(long a, long b) {
		if (b == 0) {
			return new long[] { 1, 0, a };
		}
		long[] temp = extendedGCD(b, a % b);
		long x1 = temp[0];
		long y1 = temp[1];
		long gcd1 = temp[2];
		long x = y1;
		long y = x1 - (a / b) * y1;
		return new long[] { x, y, gcd1 };
	}

	/// a^-1 mod m
	/// ax = 1 mod m
	///sol exists if gcd(a,m) = 1
	////// ax = 1 mod m 
	/// ax + my = gcd(a,m) if we have x then take mod m
	/// ax = 1 mod 5
	public long inverseMod(long a, long mod) {
	    long[] res = extendedGCD(a, mod);
	    long x = res[0];
	    long gcd = res[2];

	    if (gcd != 1) {
	        throw new RuntimeException("Inverse does not exist");
	    }

	    // Normalize to [0, mod-1]
	    x = (x % mod + mod) % mod;
	    return x;
	}
	///if mod is prime a^-1 mod m = a^(m - 2) mod m fermat's little theorem 
	public long modInversePrimeMod(long a , long m) {
		return pow(a, m - 2,m);
	}
	/// a/b mod m
	public long divMod(long a, long b, long m) {
		long div = a * modInversePrimeMod(b, m);
		return div % m;
	}

	public static void main(String[] args) {
		System.out.println(pow(5, 3));
	}

}
