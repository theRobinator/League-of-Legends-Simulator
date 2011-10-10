package util;

import champions.Champion;

public class ActionChoice {
	public static final int PHYSICAL_ATTACK = 0,
							SPELL1 = 1,
							SPELL2 = 2,
							SPELL3 = 3,
							SPELL4 = 4,
							SUMM1 = 5,
							SUMM2 = 6,
							ITEM1 = 7,
							ITEM2 = 8,
							ITEM3 = 9,
							ITEM4 = 10,
							ITEM5 = 11,
							ITEM6 = 12
	;
	
	public final int tag;
	public final Champion target;
	public final double timestamp;
	
	public ActionChoice(int tag, Champion target, double timestamp) {
		this.tag = tag;
		this.target = target;
		this.timestamp = timestamp;
	}
}
