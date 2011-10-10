package items;

import champions.Champion;
import util.Effect;

public class Manamune extends Item {
	protected Manamune() {
		super("Manamune",2110);
		
		Effect effect = new Effect("Manamune") {
			public void once(Champion c) {
				c.maxmana += 350;
				c.manaregen += 7/5.0;
				c.damage += 20;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Manamune Passive",0,false) {
			public void once(Champion c) {
				c.maxmana += 1000;
				c.damage += c.maxmana*0.02;
			}
		};
		this.uniquePassive = unique;
	}
}
