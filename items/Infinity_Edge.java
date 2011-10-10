package items;

import champions.Champion;
import util.Effect;

public class Infinity_Edge extends Item {
	protected Infinity_Edge() {
		super("Infinity Edge",3830);
		
		Effect effect = new Effect("Infinity Edge") {
			public void once(Champion c) {
				c.damage += 80;
				c.changeCritChance(25);
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Infinity Edge Passive",0,false) {
			public void once(Champion c) {
				c.critdamage += 50;
			}
		};
		this.uniquePassive = unique;
	}
}
