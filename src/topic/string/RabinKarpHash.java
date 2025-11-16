package topic.string;

public class RabinKarpHash {
	private final int mod = 1000000007;
	private final int base = 31;
	private long[] prefixHash;
	private long[] power;
	private int n;

	public RabinKarpHash(String s) {
		this.n = s.length();
		prefixHash = new long[n];
		power = new long[n];
		prefixHash[0] = getIntOfChar(s.charAt(0));
		power[0] = 1;
		for (int i = 1; i < n; i++) {
			prefixHash[i] = (prefixHash[i] * base + getIntOfChar(s.charAt(i))) % mod;
			power[i] = (power[i - 1] * base) % mod;
		}
	}

	public long getSubHash(int l, int r) {
		long h = prefixHash[r];
		if (h > 0) {
			h = (((h - (prefixHash[l - 1] * power[r - l + 1])) % mod) + mod) % mod;
		}
		return h;
	}

	public int getIntOfChar(char c) {
		return c - 'a' + 1;
	}

}
