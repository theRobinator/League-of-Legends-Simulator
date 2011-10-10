package util;

import java.util.Comparator;

public class BuildComparator implements Comparator<Build> {

	public int compare(Build arg0, Build arg1) {
		int res = arg1.wins - arg0.wins;
		if (res != 0) return res;
		return (int)(arg1.avghp - arg0.avghp);
	}

}
