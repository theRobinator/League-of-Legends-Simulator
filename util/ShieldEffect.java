package util;

import champions.Champion;

public class ShieldEffect extends Effect {
	final int type;
	double damage;
	
	public ShieldEffect(String name, final int type, final double damage, double duration, boolean stackable) {
		super(name, duration, stackable);
		
		this.type = type;
		this.damage = damage;
	}
	
	public double onDamage(Champion owner, Champion enemy, double damage, int type) {
		if (type == this.type) {
			if (damage <= this.damage) {
				this.damage -= damage;
				damage = -99999999;
			}else if (damage > this.damage) {
				this.damage = 0;
				damage -= this.damage;
			}
			if (this.damage <= 0) owner.removeEffect(this);
		}
		return damage;
	}
}
