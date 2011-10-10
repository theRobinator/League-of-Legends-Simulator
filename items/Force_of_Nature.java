package items;

import champions.Champion;
import util.Effect;

public class Force_of_Nature extends Item {
	protected Force_of_Nature() {
		super("Force of Nature",2610);
		
		Effect effect = new Effect("Force of Nature") {
			public void once(Champion c) {
				c.mr += 76;
				c.hpregen += 8;
				c.movespeed *= 1.08;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Force of Nature Passive",0,false) {
			public void selfPerSecond(Champion c) {
				c.heal(c.maxhp*0.0035);
			}
		};
		this.uniquePassive = unique;
	}
}
