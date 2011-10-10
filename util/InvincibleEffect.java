package util;

import champions.Champion;

public class InvincibleEffect extends Effect {

	public InvincibleEffect(String name, double duration, boolean stackable) {
		super(name,duration,stackable);
	}
	
	public double onDamage(Champion owner, Champion enemy, double damage, int type) {
		return Double.NEGATIVE_INFINITY;
	}
}
