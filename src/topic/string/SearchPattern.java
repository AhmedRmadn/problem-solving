package topic.string;

import java.util.ArrayList;

////https://www.geeksforgeeks.org/problems/search-pattern0205/1
public class SearchPattern {
	ArrayList<Integer> search(String pat, String txt) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int[] s = calcPrefixFunction(pat+"$"+txt);
		for(int i = 2*pat.length() ; i<= pat.length() + txt.length() ; i++ ) {
			if(s[i] == pat.length())
				list.add(i - (2*pat.length()));
		}
		return list;

	}

	public static int[] calcPrefixFunction(String p) {
    		int n = p.length();
    		int[] s = new int[n];
    		int lastBorderSize = 0;
    		for(int i = 1 ; i < n ; i++) {
    			while(lastBorderSize > 0 && p.charAt(i) != p.charAt(lastBorderSize))
    				lastBorderSize = s[lastBorderSize - 1];
    			if(p.charAt(i) == p.charAt(lastBorderSize))
    				lastBorderSize++;
    			else
    				lastBorderSize = 0;
    			s[i] = lastBorderSize;
    		}
    		return s;
    }

}
