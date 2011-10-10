package util;

import champions.Champion;

public class SheenEffect extends Effect {
	final double bonusdamage;
	public SheenEffect(double bonusdamage) {
		super("Sheen Increase",0,false);
		this.bonusdamage = bonusdamage;
	}
	
	public void once(Champion c) {
		c.damage += bonusdamage;
	}
	public void remove(Champion c) {
		c.damage -= bonusdamage;
	}
}
