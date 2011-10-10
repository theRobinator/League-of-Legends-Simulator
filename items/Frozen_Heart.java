package items;

import champions.Champion;
import util.Effect;

public class Frozen_Heart extends Item {
	protected Frozen_Heart() {
		super("Frozen Heart",3075);
		
		Effect effect = new Effect("Frozen Heart") {
			public void once(Champion c) {
				c.armor += 99;
				c.maxmana += 500;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Frozen Heart Passive",0,false) {
			public void once(Champion c) {
				c.changeCDR(20);
			}
			public void enemyOnce(Champion c, Champion e) {
				e.changeASPercent(-20);
			}
		};
		this.uniquePassive = unique;
	}
}
